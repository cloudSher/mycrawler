package com.sher.mycrawler.crawl.scripts;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ScriptEnginePool {

    private ScriptEngineManager manager;
    private AtomicInteger count = new AtomicInteger();
    private LinkedBlockingQueue<ScriptEngine> queue = new LinkedBlockingQueue();

    ScriptEnginePool(ScriptLang lang){
        this.manager = new ScriptEngineManager();
        if(lang.equals(ScriptLang.JAVASCRIPT)) {
            ScriptEngine engine = manager.getEngineByName(lang.getDesc());
            queue.add(engine);
        }
    }


    public ScriptEngine getEngine(){
        count.decrementAndGet();
        return queue.poll();
    }

    public void release(ScriptEngine engine){
        queue.add(engine);
        count.incrementAndGet();
    }

}
