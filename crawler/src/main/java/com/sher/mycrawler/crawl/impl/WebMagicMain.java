package com.sher.mycrawler.crawl.impl;

import us.codecraft.webmagic.Spider;

/**
 * Created by cloudsher on 2016/6/8.
 */
public class WebMagicMain {


    public static void main(String args[]){
        if(args!=null && args.length >0){
            String data = args[0];
//            String dir = args[1];
           data = "{\"category\":\"文\n" +
                   "本\",\"type\":\"2\",\"category_type\":\"1\",\"dest\":\"f://crawl\",\"threadNum\":1,\"url\":\"http://blog.csdn.net/kunkun378263/article/details/25644727\",\"fields\":[{\"name\":\"image\",\"value\":\"//p\",\"type\":\"2\"}]}";
            WebMagicImpl impl = new WebMagicImpl();
            impl.crawl(data);
        }
    }


    public void back(String url,String dir){
        System.out.println(url+"========="+dir);
        String domain = url.substring(url.indexOf(".")+1,url.lastIndexOf("."));
        System.out.println(domain);
        Spider.create(new WebMagicPageProcessor(domain)).addUrl(url)
                .addPipeline(new ImagePiple(dir))
                .thread(2)
                .run();
    }

}
