package com.sher.mycrawler.crawl.pipeline;

import com.sher.mycrawler.crawl.model.Document;
import com.sher.mycrawler.crawl.processor.Processor;
import com.sher.mycrawler.crawl.processor.ResultItemProcessor;
import com.sher.mycrawler.crawl.utils.JsonSerialization;
import com.sher.mycrawler.crawl.utils.ScriptConvertUtils;
import com.sher.mycrawler.driver.Driver;
import com.sher.mycrawler.driver.es.ElasticSearchDriver;
import com.sher.mycrawler.driver.es.ElasticSearchOperation;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.elasticsearch.action.index.IndexResponse;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */
public class ElasticSearchPipeline implements Pipeline {

    private ElasticSearchOperation operation;

    private Processor processor;

    public ElasticSearchPipeline(){
        if(operation == null){
            operation = new ElasticSearchOperation();
        }

        if(processor == null){
            processor = new ResultItemProcessor();
        }
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> map = resultItems.getAll();
        ScriptObjectMirror  document = (ScriptObjectMirror) map.get("document");
        Map asMap = ScriptConvertUtils.asMap(document);
        if(processor.isIntact(asMap,"index","type")){
            IndexResponse index = operation.index(asMap.get("index").toString(),asMap.get("type").toString(), asMap.get("id") == null? null:asMap.get("id").toString(), asMap.get("data").toString());
            System.out.println("==============================================");
            System.out.println(resultItems.getRequest().getUrl() +" 已存入es中，index："+index.getIndex()+",type:"+index.getType()+",id:"+index.getId()+",version:"+index.getVersion());
            System.out.println("==============================================");
        }
    }


}
