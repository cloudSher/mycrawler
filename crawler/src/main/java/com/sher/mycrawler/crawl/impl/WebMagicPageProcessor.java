package com.sher.mycrawler.crawl.impl;

import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

/**
 * Created by sher on 6/7/16.
 */
public class WebMagicPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
//        page.addTargetRequest();
        page.addTargetRequests(page.getHtml().links().regex("(http://meizitu\\.com/\\w+/\\w+)").all());
        page.putField("images", page.getHtml().xpath("//img").links().all());
    }

    public Site getSite() {
        return site;
    }


    public static void main(String args[]){
//        if(args!=null && args.length >0){
//            String url = args[0];
//            String dir = args[1];

//            System.out.println(url+"========="+dir);

            String url = "http://www.meizitu.com";
            String dir = "/Users/sher/";
            Spider.create(new WebMagicPageProcessor()).addUrl(url)
                    .addPipeline(new ImagePipe(dir))
                    .thread(1)
                    .run();
//        }
    }

    static class ImagePipe extends FilePersistentBase implements Pipeline{


        ImagePipe(String dir){
            this.setPath(dir);
        }

        public void process(ResultItems resultItems, Task task) {
            String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
            String[] urls = resultItems.get("images");
            for (String url : urls) {
                System.out.println("图片路径:=="+url);
                out(path + new Random(47).nextInt(10000000)+".jpg",url);
            }
        }

        public void out(String path,String url){
            InputStream in = null;
            try {
                FileOutputStream out = new FileOutputStream(path);
                URL ul = new URL(url);
                in = ul.openConnection().getInputStream();
                if(in != null){
                    byte[] bytes = new byte[8192];
                    int len;
                    int i = 0;
                    while((len = in.read(bytes))!=-1){
                        if(len == bytes.length){
                            i++;
                        }
                        if(i > 0)
                            out.write(bytes);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            }
        }
    }
}
