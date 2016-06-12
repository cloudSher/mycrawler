package com.sher.mycrawler.admin.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sher on 6/6/16.
 *
 * 后台管理
 */

@RestController
@EnableAutoConfiguration
public class AdminController {



    @RequestMapping("/")
    @ResponseBody
    public String home(){

        return "hello world";
    }





}
