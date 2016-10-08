package com.sher.mycrawler.crawl.model;

/**
 * Created by Administrator on 2016/9/9.
 */
public class Category {

    private String id;
    private String name;
    private String type;


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
