package com.sher.mycrawler.crawl.scripts;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ScriptProcessor implements PageProcessor {

    private static Logger logger = LoggerFactory.getLogger(ScriptProcessor.class);
    private ScriptEnginePool pool;
    private ScriptLang lang;
    private Site site = Site.me();
    private String scripts;

    ScriptProcessor(ScriptLang lang , String scripts){
        this.lang = lang;
        this.scripts = scripts;
        this.pool = new ScriptEnginePool(lang);
    }

    public void process(Page page) {
        ScriptEngine engine = pool.getEngine();
        ScriptContext context = engine.getContext();
        context.setAttribute("page",page,ScriptContext.ENGINE_SCOPE);
        context.setAttribute("config",site,ScriptContext.ENGINE_SCOPE);
        try{
            switch (lang){
                case JAVASCRIPT:
                    engine.eval(scripts);
            }
        }catch (Exception e){
            logger.error("scriptProcessor process() error info", e);
        }

    }

    public Site getSite() {
        return site;
    }

    static class Builder{
        private final static ScriptLang DEFAULT = ScriptLang.JAVASCRIPT;
        private ScriptLang lang = DEFAULT;
        private String scripts;

        public void addLang(ScriptLang lang){
            this.lang = lang;
        }

        public void buildScript(String fileName){
            try {
                InputStream stream = new FileInputStream(fileName);
                String script = IOUtils.toString(stream);
                this.scripts = script;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public ScriptProcessor build(){
            return new ScriptProcessor(lang,scripts);
        }
    }
}
