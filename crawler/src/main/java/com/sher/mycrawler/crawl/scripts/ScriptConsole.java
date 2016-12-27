package com.sher.mycrawler.crawl.scripts;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    }

    public static void main(String args[]) throws ParseException {
        parseCommand(args);
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
            param.setThreadNum(Integer.valueOf(line.getOptionValue("f")));
        }
        if(line.hasOption("f")){
            param.setFileName(line.getOptionValue("f"));
        }else{
            exit();
        }
        if(line.hasOption("s")){
            param.setSleepTime(Integer.valueOf(line.getOptionValue("s")));
        }
        return param;
    }


}
