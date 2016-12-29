package com.sher.mycrawler.crawl.pipeline;

import com.sher.mycrawler.crawl.core.constant.StoreType;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by Administrator on 2016/12/29.
 */
public interface PipelineSelector {


    Pipeline select(StoreType type);

}
