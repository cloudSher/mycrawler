package com.sher.mycrawler.crawl.scripts;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ScriptEnginePool {

    private ScriptEngineManager manager;
    private AtomicInteger count = new AtomicInteger();
    private LinkedBlockingQueue<ScriptEngine> queue = new LinkedBlockingQueue();

    ScriptEnginePool(ScriptLang lang,int num){
        this.manager = new ScriptEngineManager();
        IntStream.of(num).forEach(n -> {
            if(lang.equals(ScriptLang.JAVASCRIPT)) {
                ScriptEngine engine = manager.getEngineByName(lang.getDesc());
                queue.add(engine);
            }
        });

    }


    public ScriptEngine getEngine() throws InterruptedException {
        count.decrementAndGet();
        return queue.take();
    }

    public void release(ScriptEngine engine){
        queue.add(engine);
        count.incrementAndGet();
    }

}
