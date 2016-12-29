package com.sher.mycrawler.crawl.pipeline;

import com.sher.mycrawler.crawl.nio.FileNIO;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/29.
 */
public class FilePipeline extends FilePersistentBase implements Pipeline {

    private static Logger logger = LoggerFactory.getLogger(FilePipeline.class);
    private String path;
    private final static String SUFFER_FILE = ".html";

    FilePipeline(String path){
        this.path = path;
    }

    public void process(ResultItems resultItems, Task task) {
        if (resultItems == null){
            logger.info("In filePipeline , resultItems not have item");
        }
        String path = this.path + File.separator+task.getUUID()+File.separator;
        File file = getFile(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + SUFFER_FILE);
        Map<String, Object> result = resultItems.getAll();
        StringBuilder builder = new StringBuilder("url:"+resultItems.getRequest().getUrl());
        for(Map.Entry<String,Object> entry : result.entrySet()){
            builder.append(entry.getKey()+":" + entry.getValue());
        }
        FileNIO.transferToFileByString(builder.toString(),file);
    }
}
