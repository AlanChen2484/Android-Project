package com.example.quxing.quxing.Tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by 陈若韬 on 2018/4/21.
 */

public class StreamTools {
    //把一个InputStream转化成String
    public static String readStream(InputStream in) throws Exception {

        //定义一个内存输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;
        byte[] buffer = new byte[1024];//1kb
        while ((len=in.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        in.close();

        return new String(baos.toByteArray());
    }

}

