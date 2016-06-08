package com.sher.mycrawler.crawl.impl;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by sher on 6/7/16.
 */
public class WebMagicPageProcessor implements PageProcessor {

    private String domain;

    WebMagicPageProcessor(String domain){
        this.domain = domain;
    }
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
//        page.addTargetRequest();
        page.addTargetRequests(page.getHtml().links().regex("(http://"+domain+"\\.com/\\w+/\\w+)").all());
        page.putField("images", page.getHtml().xpath("//img/@src").all());
    }

    public Site getSite() {
        return site;
    }

}
