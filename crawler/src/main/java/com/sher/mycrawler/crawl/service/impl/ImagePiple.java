package com.sher.mycrawler.crawl.service.impl;

import com.sher.mycrawler.crawl.nio.FileNIO;
import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by cloudsher on 2016/6/8.
 */
public class ImagePiple extends FilePersistentBase implements Pipeline {

        ImagePiple(String dir){
             this.setPath(dir);
        }

        public void process(ResultItems resultItems, Task task) {
            String path = this.path + PATH_SEPERATOR  + PATH_SEPERATOR;
            List<String> list = resultItems.get("image");
            for (String url : list) {
                System.out.println("图片路径:=="+url);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FileNIO.down(url,getFile(path + DigestUtils.md5Hex(url) + ".jpg"));
            }
        }

        public void out(File path,String url){
            InputStream in = null;
            try {
                FileOutputStream out = new FileOutputStream(path);
                URL ul = new URL(url);
                in = ul.openConnection().getInputStream();
                if(in != null){
                    int len;
                    while((len = in.read()) != -1){
                        out.write(len);
                    }
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (in !=null)
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
}
