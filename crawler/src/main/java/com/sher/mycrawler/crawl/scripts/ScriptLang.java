package com.sher.mycrawler.crawl.scripts;

/**
 * Created by Administrator on 2016/12/27.
 */
public enum ScriptLang {

    JAVASCRIPT("JS",0,"javascript");

    private String lang;
    private int code;
    private String desc;

    ScriptLang(String lang,int code,String desc) {
        this.lang = lang;
        this.code = code;
        this.desc = desc;
    }

    public String getLang() {
        return lang;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
