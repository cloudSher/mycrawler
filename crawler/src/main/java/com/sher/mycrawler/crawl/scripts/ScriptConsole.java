package com.sher.mycrawler.crawl.scripts;

import com.sher.mycrawler.crawl.core.constant.StoreType;
import com.sher.mycrawler.crawl.pipeline.DefaultPipelineSelector;
import org.apache.commons.cli.*;
import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 *
 *  java -jar -Dfile.encoding='utf-8' webmagic-console.jar -f github.js -t 2 -s 0 https://github.com/code4craft
 *
 *  默认采用js执行引擎
 */
public class ScriptConsole {

    private static Logger logger = LoggerFactory.getLogger(ScriptConsole.class);

    private static class Param{
        private ScriptLang lang = ScriptLang.JAVASCRIPT;
        private String fileName;
        private int threadNum;
        private int sleepTime;
        private List<String> url;

        public ScriptLang getLang() {
            return lang;
        }

        public void setLang(ScriptLang lang) {
            this.lang = lang;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setThreadNum(int threadNum) {
            this.threadNum = threadNum;
        }

        public void setSleepTime(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        public List<String> getUrl() {
            return url;
        }

        public void setUrl(List<String> url) {
            this.url = url;
        }

        public String getFileName() {
            return fileName;
        }

        public int getThreadNum() {
            return threadNum;
        }

        public int getSleepTime() {
            return sleepTime;
        }
    }

    public static void main(String args[]) {
        try {
            run(parseCommand(args));
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("脚本运行出错，",e);
            exit();
        }
    }


    public static Param parseCommand(String args[]) throws ParseException {
        Options options = new Options();
        options.addOption(new Option("t","thread",true,"thread"));
        options.addOption(new Option("f","file",true,"file"));
        options.addOption(new Option("s","sleep",true,"sleep time"));
        options.addOption(new Option("l","language",true,"language"));

        CommandLineParser parser = new PosixParser();
        CommandLine line = parser.parse(options, args);

        return createParam(line);

    }

    public static void exit(){
        logger.error("command argument has error" );
        System.out.println("need least one argument");
        System.out.println("Usage: java -jar webmagic.jar [-l language] -f script file [-t threadnum] [-s sleep time] url1 [url2 url3]");
        System.exit(-1);
    }

    private static Param createParam(CommandLine line) {
        Param param = new Param();
        if(line.hasOption("t")){
            param.setThreadNum(Integer.valueOf(line.getOptionValue("t")));
        }
        if(line.hasOption("f")){
            param.setFileName(line.getOptionValue("f"));
            System.out.println(line.getOptionValue("f"));
        }else{
            exit();
        }
        if(line.hasOption("s")){
            param.setSleepTime(Integer.valueOf(line.getOptionValue("s")));
        }
        param.setUrl(line.getArgList());
        return param;
    }


    public static PageProcessor buildProcessor(Param param){
        ScriptProcessor processor = new ScriptProcessor.Builder().addLang(param.getLang())
                .buildScript(param.getFileName()).build();
        processor.getSite().setSleepTime(param.getSleepTime());
        return processor;
    }

    public static String read(){
        System.out.println("*******************************************");
        System.out.println("请输入下载类型： 1:文件");
        String dest = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line;
            String type;
            boolean isType = false;
            int time = 0;
            while((line = reader.readLine()) != null){
                if(line.equals("1")){
                    type = "1";
                    System.out.println("请输入下载目录：");
                    isType = true;
                }
                else if(!isType){
                    isType = false;
                    System.out.println("输入的类型不正确，请重新选择类型：");
                }
                else if(isType){
                    dest = line;
                    break;
                }

                if(time == 3){
                    System.exit(-1);
                }

                time++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

    private static void run(Param param){
        String[] urls = new String[param.getUrl().size()];
        param.getUrl().toArray(urls);
        String dest = read();
        DefaultPipelineSelector selector = new DefaultPipelineSelector();
        FilePipeline filePipeline = (FilePipeline) selector.select(StoreType.FILE);
        filePipeline.setPath(dest);
        Spider.create(buildProcessor(param))
              .thread(param.getThreadNum())
              .addPipeline(filePipeline)
              .addUrl(urls)
              .run();
    }




}
