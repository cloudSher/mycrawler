package com.sher.mycrawler.driver.es;

import com.sher.mycrawler.driver.Driver;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
public class ElasticSearchOperation {

    private Driver driver;
    private TransportClient client;

    public  ElasticSearchOperation(Driver driver){
        this.driver = driver;
        this.client = ((ElasticSearchDriver) driver).getClient();
    }

    public ElasticSearchOperation(){
        if(driver == null){
            driver = new ElasticSearchDriver();
            driver.init();
            this.client = (TransportClient) driver.getClient();
        }
    }


    public IndexResponse index(String index, String type, String id, String source){
        if(id == null){
          return  index(index,type,source);
        }else{
            IndexRequestBuilder prepareIndex = client.prepareIndex(index, type, id);
            return prepareIndex.setSource(source).get();
        }
    }

    public IndexResponse index(String index,String type,String source){
        return client.prepareIndex(index,type).setSource(source).get();
    }


    public IndexResponse index(String index,String type,String id,Map source){
        return client.prepareIndex(index,type).setId(id).setSource(source).get();
    }


    public GetResponse get(String index,String type,String id){
        return client.prepareGet(index,type,id).get();
    }

    public DeleteResponse delete(String index,String type,String id){
        return client.prepareDelete(index,type,id).get();
    }

    public UpdateResponse update(String index,String type,String id,String doc){
        return client.prepareUpdate(index,type,id).setDoc(doc).get();
    }

    public UpdateResponse update(String index,String type,String id,Map doc){
        return client.prepareUpdate(index,type,id).setDoc(doc).get();
    }

    public static void main(String args[]){
        ElasticSearchOperation operation = new ElasticSearchOperation();
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        operation.index("test","test","1",json);
    }


}
