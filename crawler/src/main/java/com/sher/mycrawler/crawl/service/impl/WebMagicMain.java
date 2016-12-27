package com.sher.mycrawler.crawl.service.impl;

import us.codecraft.webmagic.Spider;

/**
 * Created by cloudsher on 2016/6/8.
 */
public class WebMagicMain {

    /***
     *
     * 请求的参数json:
     *
     *   {
            "category": "文本",
            "type": "2",
            "category_type": "1",
            "dest": "f://crawl",
            "threadNum": 1,
            "url": "http://blog.csdn.net/kunkun378263/article/details/25644727",
            "fields": [{
                "name": "image",
                "value": "//p",
                "type": "2"
            }]
        }
     *
     * @param args
     */
    public static void main(String args[]){
        if(args!=null && args.length >0){
            String data = args[0];
//            String dir = args[1];
           data = "{\n" +
                   "    \"category\": \"文本\",\n" +
                   "    \"type\": \"2\",\n" +
                   "    \"category_type\": \"1\",\n" +
                   "    \"dest\": \"f://crawl\",\n" +
                   "    \"threadNum\": 1,\n" +
                   "    \"url\": \"http://blog.csdn.net/kunkun378263/article/details/25644727\",\n" +
                   "    \"fields\": [\n" +
                   "        {\n" +
                   "            \"name\": \"image\",\n" +
                   "            \"value\": \"//p\",\n" +
                   "            \"type\": \"2\"\n" +
                   "        }\n" +
                   "    ]\n" +
                   "}";
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
