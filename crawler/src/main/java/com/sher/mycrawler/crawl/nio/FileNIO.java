package com.sher.mycrawler.crawl.nio;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * Created by sher on 6/19/16.
 *
 *  文件通道
 */
public class FileNIO {


    public static void read(){
        try {
            RandomAccessFile file = new RandomAccessFile("/Users/sher/aa.jpg","rw");
            FileChannel fileChannel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(47);
            int byteRead;

            //创建一个selector
            Selector selector = Selector.open();

            FileInputStream in = new FileInputStream("");
            FileChannel channel = in.getChannel();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]){
        down("https://www.jianxun.io/ad/20151101/ifeve-bottombanner.jpg",new File(""));
    }


    public static void down(String path,File dest){
        try {
            URL url = new URL(path);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            byte[] buff = new byte[1024];
            RandomAccessFile file = new RandomAccessFile(dest,"rw");
            FileChannel fileChannel = file.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = 0;
            while((len = inputStream.read(buff))!=-1){
                buffer.clear();
                buffer.put(buff,0,len);
                buffer.flip();
                fileChannel.write(buffer);
            }
            fileChannel.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



//    public static Object proxy(Object obj){
//        return Proxy.newProxyInstance(FileIODemo.class.getClassLoader(), FileIODemo.class.getInterfaces(), new InvocationHandler() {
//
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                long start = System.currentTimeMillis();
//                method.invoke(obj, args[0], args[1]);
//                System.out.println("time is : " + (System.currentTimeMillis() - start) + " ms");
//                return obj;
//            }
//        });
//    }

}
