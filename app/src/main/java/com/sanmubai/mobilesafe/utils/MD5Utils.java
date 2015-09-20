package com.sanmubai.mobilesafe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bb on 15-9-20.
 */
public class MD5Utils {

    public static String encode(String passwd){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] passArr = md.digest(passwd.getBytes());
            StringBuffer sb=new StringBuffer();

            for (byte b:passArr){
                int i=b & 0xff;
                String j = Integer.toHexString(i);
                if (j.length()<2){
                    j="0"+j;
                }
                sb.append(j);

            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
