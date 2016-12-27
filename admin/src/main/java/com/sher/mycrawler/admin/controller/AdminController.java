package com.sher.mycrawler.admin.controller;

import com.sher.mycrawler.crawl.service.Crawl;
import com.sher.mycrawler.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sher on 6/6/16.
 *
 * 后台管理
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    Crawl crawl;

    @RequestMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }



    @RequestMapping("/crawl")
    @ResponseBody
    public String crawl(String json){
        if(json != null){
            String result = crawl.crawl(json);
            if(result!=null){
                return Result.success("已经完成").toString();
            }
        }
        return Result.failed("操作失败，请查看日志").toString();
    }



    @RequestMapping("/index")
    @ResponseBody
    public String index(){

        return null;
    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(){

        return null;
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(){

        return null;
    }

}
