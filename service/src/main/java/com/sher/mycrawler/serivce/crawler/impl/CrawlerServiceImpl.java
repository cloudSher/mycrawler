package com.sher.mycrawler.serivce.crawler.impl;

import com.sher.mycrawler.crawl.service.Crawl;
import com.sher.mycrawler.domain.Result;
import com.sher.mycrawler.serivce.crawler.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cloudsher on 2016/6/15.
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Autowired
    private Crawl crawl;

    @Override
    public Result crawl(String data) {
        if(data != null){

        }
        return null;
    }
}
