package com.sher.mycrawler.crawl.pipeline;

import com.sher.mycrawler.crawl.core.constant.StoreType;
import us.codecraft.webmagic.pipeline.*;
import us.codecraft.webmagic.pipeline.FilePipeline;

/**
 * Created by Administrator on 2016/12/29.
 */
public class DefaultPipelineSelector implements PipelineSelector {

    @Override
    public Pipeline select(StoreType type) {
        return new FilePipeline("");
    }
}
