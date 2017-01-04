package com.sher.mycrawler.crawl.pipeline;

import com.sher.mycrawler.crawl.core.constant.StoreType;
import com.sher.mycrawler.crawl.scripts.HandlerResult;
import us.codecraft.webmagic.pipeline.*;
import us.codecraft.webmagic.pipeline.FilePipeline;

/**
 * Created by Administrator on 2016/12/29.
 */
public class DefaultPipelineSelector implements PipelineSelector {

    @Override
    public Pipeline select(HandlerResult result) {
        if(result == null){
            System.out.println("存储类型不能为空");
        }
        StoreType type = result.getType();
        switch (type){
            case FILE:
                return new FilePipeline(result.getDest());
            case ES:
                return new ElasticSearchPipeline();
        }
        return new FilePipeline("");
    }
}
