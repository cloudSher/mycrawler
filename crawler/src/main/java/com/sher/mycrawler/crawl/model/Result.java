package com.sher.mycrawler.crawl.model;

/**
 * Created by cloudsher on 2016/6/15.
 */
public class Result<T> {

    private String code;
    private String msg;
    private T t;

    Result(String code,String msg,T t){
        this.code = code;
        this.msg = msg;
        this.t = t;
    }

    public static Result success(String msg){
        return new Result("200",msg,null);
    }

    public static Result failed(String msg){
        return new Result("400",msg,null);
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", t=" + t +
                '}';
    }
}
