package com.sher.mycrawler.crawl.scripts;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/12/27.
 */
public class ScriptConsole {

    private static Logger logger = LoggerFactory.getLogger(ScriptConsole.class);


    public static void main(String args[]) throws ParseException {
        parseCommand(args);
    }


    public static void parseCommand(String args[]) throws ParseException {
        Options options = new Options();
        options.addOption(new Option("t","thread",true,"thread"));
        options.addOption(new Option("f","file",true,"file"));
        options.addOption(new Option("s","sleep",true,"sleep time"));

        CommandLineParser parser = new PosixParser();
        CommandLine line = parser.parse(options, args);


    }






}
