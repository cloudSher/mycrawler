package com.sher.mycrawler.serivce.crawler.impl;

import com.sher.mycrawler.crawl.Crawl;
import com.sher.mycrawler.domain.Result;
import com.sher.mycrawler.serivce.crawler.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cloudsher on 2016/6/15.
 */
public class CrawlerServiceImpl implements CrawlerService {

    @Autowired
    private Crawl crawl;

    @Autowired
//    private CrawlMapper mapper;

    @Override
    public Result crawl(String data) {
        if(data != null){

        }
        return null;
    }
}
