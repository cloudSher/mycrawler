package com.sher.mycrawler.crawl.processor;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */
public interface Processor<K,V> {

   List<K> process(V item);


   boolean isIntact(Object o,String ... keys);
}
