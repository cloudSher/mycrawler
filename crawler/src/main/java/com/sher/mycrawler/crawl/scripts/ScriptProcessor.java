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
    private String script;
    private String defines;

    ScriptProcessor(ScriptLang lang , String script){
        this.lang = lang;
        this.script = script;
        this.pool = new ScriptEnginePool(lang);
        loadDefinesJs(lang.getDefines());
    }


    public void loadDefinesJs(String defines){
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(defines);
        try {
            this.defines = IOUtils.toString(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(Page page) {
        ScriptEngine engine = pool.getEngine();
        try{
            ScriptContext context = engine.getContext();
            context.setAttribute("page",page,ScriptContext.ENGINE_SCOPE);
            context.setAttribute("config",site,ScriptContext.ENGINE_SCOPE);
            switch (lang){
                case JAVASCRIPT:
                    engine.eval(defines + "\n" + script ,context);
                    break;
            }
        }catch (Exception e){
            logger.error("scriptProcessor process() error info,page {}",page,e);
        }finally {
            pool.release(engine);
        }

    }

    public Site getSite() {
        return site;
    }

    public static class Builder{
        private final static ScriptLang DEFAULT = ScriptLang.JAVASCRIPT;
        private ScriptLang lang = DEFAULT;
        private String script;

        public Builder addLang(ScriptLang lang){
            this.lang = lang;
            return this;
        }

        public Builder buildScript(String fileName){
            try {
                InputStream stream = new FileInputStream(fileName);
                String script = IOUtils.toString(stream);
                this.script = script;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }

        public Builder scriptFromClassPath(String fileName){
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            try {
                this.script = IOUtils.toString(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }

        public ScriptProcessor build(){
            return new ScriptProcessor(lang,script);
        }
    }
}
