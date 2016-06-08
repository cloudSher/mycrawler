package com.sher.mycrawler.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by sher on 6/6/16.
 *
 * boot bootstrap
 */

@EnableWebMvc
@SpringBootApplication
@ComponentScan("com.sher.mycrawler")
public class Application {

    public static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]){
        logger.info("================== app starting===============");
        SpringApplication.run(Application.class, args);
        logger.info("===================app start success=============");
    }
}
