package com.sher.mycrawler.serivce.admin.impl;

import com.sher.mycrawler.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cloudsher on 2016/6/23.
 */
@Service
public class CategoryServiceImpl {


    @Autowired
    CategoryMapper categoryMapper;

}
