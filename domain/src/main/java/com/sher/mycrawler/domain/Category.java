package com.sher.mycrawler.domain;

import java.io.Serializable;

/**
 * 
 *
 * @author Administrator
 * @date 2016-06-23
 *
 */
public class Category implements Serializable {
    /**  */
    private Integer id;

    /**  */
    private String name;

    /**  */
    private String type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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