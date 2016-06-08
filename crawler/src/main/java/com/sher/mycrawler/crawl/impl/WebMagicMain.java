package com.sher.mycrawler.crawl.impl;

import us.codecraft.webmagic.Spider;

/**
 * Created by cloudsher on 2016/6/8.
 */
public class WebMagicMain {


    public static void main(String args[]){
        if(args!=null && args.length >0){
            String url = args[0];
            String dir = args[1];

            System.out.println(url+"========="+dir);
            String domain = url.substring(url.indexOf(".")+1,url.lastIndexOf("."));
            System.out.println(domain);
//            String url = "http://www.meizitu.com";
//            String dir = "/Users/sher/";
            Spider.create(new WebMagicPageProcessor(domain)).addUrl(url)
                    .addPipeline(new ImagePiple(dir))
                    .thread(1)
                    .run();
        }
    }
}
