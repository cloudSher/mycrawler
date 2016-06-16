package com.sher.mycrawler.domain;

/**
 * Created by cloudsher on 2016/6/15.
 */
public class Category {

    private String id;
    private String name;
    private String type;        //1.文本  2.图片  3.视频

    public Category(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
