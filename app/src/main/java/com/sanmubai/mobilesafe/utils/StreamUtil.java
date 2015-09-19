package com.sanmubai.mobilesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * Created by bb on 2015/9/17.
 */
public class StreamUtil {

    public static String readFromStream(InputStream ins) throws IOException {
        ByteArrayOutputStream out=new ByteArrayOutputStream();

        int len=0;
        byte[] buffer=new byte[1024];

        while ((len=ins.read(buffer))!=-1){
            out.write(buffer,0,len);
        }
        String res=out.toString();

        ins.close();
        out.close();

        return res;
    }
}

