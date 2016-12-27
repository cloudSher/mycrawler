package com.sher.mycrawler.crawl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sher.mycrawler.common.constant.StoreType;
import com.sher.mycrawler.crawl.service.Crawl;
import com.sher.mycrawler.crawl.model.*;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sher on 6/6/16.
 *
 * webmigc impl for crawl
 */
@Service
public class WebMagicImpl implements Crawl {



    public String crawl(String json) {
        Crawler crawler = new Crawler();
        if(json!=null){
            JSONObject object = JSONObject.parseObject(json);
            String category = object.getString("category");
            String category_type = object.getString("category_type");
            String type = object.getString("type");
            String destPath = object.getString("dest");
            String url = object.getString("url");

            JSONArray fields = object.getJSONArray("fields");
            List<Field> lists = new ArrayList<Field>(fields.size());
            for(int i = 0,len = fields.size() ; i < len; i++ ){
                JSONObject obj = fields.getJSONObject(i);
                lists.add(new Field(obj.getString("name"),obj.getString("value"),obj.getString("type")));
            }
            crawler.setCategory(new Category(category,category_type));
            crawler.setUrl(new Url(url,destPath));
            crawler.setField(lists);
            crawler.setType(type);
            crawler.setThreadNum(object.getInteger("threadNum"));
        }

        try{
            spider(crawler);
        }catch (Exception e){
            return Result.failed("失败").toString();
        }
        return Result.success("成功").toString();
    }


    public void spider(Crawler crawler){
        String url = crawler.getUrl().getUrl();
        int index = url.indexOf(".");
        int index_suff = url.indexOf("/", index + 1);
        int index_df = url.indexOf(".",index+1);
        String domain;
        if(index_df > index_suff && index_suff > 0){
            domain = url.substring(index + 1,index_suff);
        }else{
            domain = url.substring(index + 1);
        }
        //todo domain parse issue
        WebMagicPageProcessor pageProcessor = new WebMagicPageProcessor(domain,crawler.getField());
        long currTime = System.currentTimeMillis();
        Spider spider = Spider.create(pageProcessor)
                .addUrl(crawler.getUrl().getUrl());
        String destPath = crawler.getUrl().getDestPath();
        if(crawler.getType().equals(StoreType.TEXT)){
            spider.addPipeline(new JsonFilePipeline(destPath));
        }else if(crawler.getType().equals(StoreType.IMAGE)){
            spider.addPipeline(new ImagePiple(destPath));
        }else {
            spider.addPipeline(new JsonFilePipeline(destPath));
        }
        spider.thread(crawler.getThreadNum()).run();
        System.out.println("总共运行的时间：" + (System.currentTimeMillis() - currTime) + "毫秒");

    }

}
