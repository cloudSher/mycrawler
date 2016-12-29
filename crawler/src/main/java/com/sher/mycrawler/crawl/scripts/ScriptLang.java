package com.sher.mycrawler.crawl.scripts;

/**
 * Created by Administrator on 2016/12/27.
 */
public enum ScriptLang {

    JAVASCRIPT("javascript", "js/defines.js", "javascript");

    private String lang;
    private String defines;
    private String desc;

    ScriptLang(String lang, String defines, String desc) {
        this.lang = lang;
        this.defines = defines;
        this.desc = desc;
    }

    public String getLang() {
        return lang;
    }

    public String getDefines() {
        return defines;
    }

    public String getDesc() {
        return desc;
    }
}
