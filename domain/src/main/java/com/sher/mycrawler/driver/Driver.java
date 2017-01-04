package com.sher.mycrawler.driver;

import java.net.UnknownHostException;

/**
 * Created by Administrator on 2017/1/3.
 */
public interface Driver<T> {

    void init();


    T getClient();


    void destory();

}
