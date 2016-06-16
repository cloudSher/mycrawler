package com.sher.mycrawler.domain;

/**
 * Created by cloudsher on 2016/6/15.
 */
public class Url {

    private String id;
    private String url;
    private String destPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public Url(String url, String destPath) {
        this.url = url;
        this.destPath = destPath;
    }
}
