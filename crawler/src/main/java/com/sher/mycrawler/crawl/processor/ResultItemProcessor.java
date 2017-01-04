package com.sher.mycrawler.crawl.processor;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */
public class ResultItemProcessor implements Processor{


    @Override
    public List process(Object item) {
        return null;
    }

    @Override
    public boolean isIntact(Object o,String... keys) {
        if(o instanceof Map){
            Map map = (Map) o;
            if(keys.length == 0){
                return true;
            }
            for(String key : keys){
                Object k = map.get(key);
                if(k == null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
