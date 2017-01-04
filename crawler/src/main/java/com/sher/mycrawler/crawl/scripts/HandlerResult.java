package com.sher.mycrawler.crawl.scripts;

import com.sher.mycrawler.crawl.core.constant.StoreType;

/**
 * Created by Administrator on 2017/1/4.
 */
public class HandlerResult {

    private String dest;            //文件类型目录
    private StoreType type;

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public StoreType getType() {
        return type;
    }

    public void setType(StoreType type) {
        this.type = type;
    }
}
