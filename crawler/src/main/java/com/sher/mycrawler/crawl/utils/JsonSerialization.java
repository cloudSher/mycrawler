package com.sher.mycrawler.crawl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/4.
 */
public class JsonSerialization {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static <T> T serialized(String json,Class<T> clazz) {
        try {
            return mapper.readValue(json,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String asString(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }




}
