package com.sher.mycrawler.config;

import java.io.*;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by Administrator on 2017/1/6.
 */
public class Configuration {

    private Properties prop = new Properties();
    private final String PATH="config.properties";

    public Properties load(String path){
        InputStream stream = null;
        try {
            String configPath = currPath() + File.separator + PATH;
            File file = new File(PATH);
            System.out.println("================  config.properties file path :" + file.toString() + ",current path :" + currPath());
            if(file.exists())
                stream = new FileInputStream(file);
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
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(PATH);
        return stream;
    }

    public boolean checkPath(String path){
        if(path == null || path.trim().length() == 0){
            return false;
        }
        return true;
    }

    public String currPath(){
        return System.getProperty("user.dir");
    }

}
