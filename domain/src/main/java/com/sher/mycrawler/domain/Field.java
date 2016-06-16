package com.sher.mycrawler.domain;

/**
 * Created by cloudsher on 2016/6/15.
 */
public class Field {

    private String id;
    private String name;            //字段名称
    private String value;           //值
    private String type;            //1.正则  2.xpath

    public Field(String name, String value,String type) {
        this.name = name;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    static class Type{
        public static String REGULAR = "1";
        public static String XPATH = "2";
    }

    public boolean isRegular(){
        return this.type.equals(Type.REGULAR) ? true : false;
    }

    public boolean isXpath(){
        return this.type.equals(Type.XPATH) ? true : false;
    }
}
