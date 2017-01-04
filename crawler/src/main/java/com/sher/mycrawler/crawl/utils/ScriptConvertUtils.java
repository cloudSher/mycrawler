package com.sher.mycrawler.crawl.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/4.
 */
public class ScriptConvertUtils {



    public static Map asMap(Object obj){
        Map<String,Object> map = new HashMap<>();
        if(obj instanceof ScriptObjectMirror){
            ScriptObjectMirror mirror = (ScriptObjectMirror) obj;
            Set<Map.Entry<String, Object>> entrySet = mirror.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Object> next = iterator.next();
                String key = next.getKey();
                Object value = next.getValue();
                if(value instanceof ScriptObjectMirror){
                    value = asMap(value);
                }
                map.put(key,value);
            }
        }

        return map;
    }
}
