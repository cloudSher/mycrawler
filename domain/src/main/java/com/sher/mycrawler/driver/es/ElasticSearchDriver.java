package com.sher.mycrawler.driver.es;

import com.sher.mycrawler.driver.Driver;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Created by Administrator on 2017/1/3.
 *
 *  规范和技术实现要区分，JDBC中的Driver，Connection 是所有驱动的规范，既然是规范，就是高抽象，
 *  技术实现方法上： 可以采用配置文件加载所需要的驱动
 */
public class ElasticSearchDriver implements Driver<TransportClient>  {


    private final static String HOST = "localhost";
    private final static int PORT = 9300;

    private TransportClient client;

    @Override
    public void init(){
        if(client == null){
            try {
                client = new PreBuiltTransportClient(Settings.EMPTY)
                            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TransportClient getClient() {
        return client;
    }


    @Override
    public void destory() {
        client.close();
    }
}
