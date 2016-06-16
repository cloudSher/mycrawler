package com.sher.mycrawler.crawl.impl;

import com.sher.mycrawler.domain.Field;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by sher on 6/7/16.
 */
public class WebMagicPageProcessor implements PageProcessor {

    private String domain;
    private List<Field> fields;

    WebMagicPageProcessor(String domain){
        this.domain = domain;
    }


    WebMagicPageProcessor(String domain,List<Field> fields){
        this.domain = domain;
        this.fields = fields;
    }
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
//        page.addTargetRequest();
        page.addTargetRequests(page.getHtml().links().regex("(http://"+domain+"/\\w+/\\w+)").all());
        if(fields != null){
            for( int i = 0,len = fields.size() ; i < len; i++ ){
                Field field = fields.get(i);
                if(field.isXpath())
                    page.putField(field.getName(), page.getHtml().xpath(field.getValue()).all());
                else if(field.isRegular()){
                    page.putField(field.getName(),page.getHtml().regex(field.getValue()).all());
                }else{
                    page.putField(field.getName(), page.getHtml().xpath(field.getValue()).all());
                }
            }
        }

    }

    public Site getSite() {
        return site;
    }

}
