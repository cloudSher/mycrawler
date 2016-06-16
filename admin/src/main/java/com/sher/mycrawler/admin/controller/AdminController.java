package com.sher.mycrawler.admin.controller;

import com.sher.mycrawler.crawl.Crawl;
import com.sher.mycrawler.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sher on 6/6/16.
 *
 * 后台管理
 */

@RestController
@EnableAutoConfiguration
public class AdminController {

    @Autowired
    Crawl crawlerService;

    @RequestMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }



    @RequestMapping("/crawl")
    public String crawl(String json){
        if(json != null){
            String result = crawlerService.crawl(json);
            if(result!=null){
                return Result.success("已经完成").toString();
            }
        }
        return Result.failed("操作失败，请查看日志").toString();
    }


}
