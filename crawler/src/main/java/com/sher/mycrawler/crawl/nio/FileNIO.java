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
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * Created by sher on 6/19/16.
 *
 *  文件通道
 */
public class FileNIO {


    public static void read(String url){
        try {
            RandomAccessFile file = new RandomAccessFile(url,"rw");
            FileChannel fileChannel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
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


    /**
     * channel 传输文件
     * @param src
     * @param dest
     */
    public static void transferTo(String src,String dest){
        RandomAccessFile srcFile;
        RandomAccessFile destFile;
        try {
            srcFile = new RandomAccessFile(src, "rw");
            destFile = new RandomAccessFile(dest, "rw");
            srcFile.getChannel().transferTo(0,srcFile.length(),destFile.getChannel());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }


    public static void transferToFileByString(String data,File dest){
        RandomAccessFile destFile = null;
        try {
            destFile = new RandomAccessFile(dest, "rw");
            FileChannel channel = destFile.getChannel();
            ByteBuffer wrap = ByteBuffer.wrap(data.getBytes());
            wrap.flip();
            channel.write(wrap);
            wrap.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                destFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
