package com.sher.mycrawler.config;

import java.io.*;
import java.util.Properties;

/**
 * Created by Administrator on 2017/1/6.
 */
public class Configuration {

    private Properties prop = new Properties();
    private final String PATH="config.properties";

    public Properties load(String path){
         if(path == null || path.trim().length() == 0){
            return null;
         }
        try {
            InputStream stream = new FileInputStream(checkPath(path)?path:PATH);
            if(stream == null){
                stream = loadPathFromClassPath();
            }
            prop.load(stream);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public InputStream loadPathFromClassPath(){
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        return stream;
    }

    public boolean checkPath(String path){
        File file = new File(path);
        if(!file.exists()){
            return false;
        }
        return true;
    }


}
