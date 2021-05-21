package com.example.demo;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * 将密码进行MD5加密处理
 */

public class PassWordDigester {
    private static Logger logger = Logger.getLogger(PassWordDigester.class);

    private static final String ALGORITHM_MD5 = "MD5";

    /**
     * Encrypt the password with MD5
     *
     * @param pass the password to encryption
     * @return encryption string
     */

    public static String getPassMD5(String pass) {
        String keys = null;

        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);

            if (pass == null) {
                pass = "";

            }

            byte[] bPass = pass.getBytes("UTF-8");

            md.update(bPass);// 通过使用 update 方法处理数据,使指定的 byte数组更新摘要

            keys = bytesToHexString(md.digest());//// 获得密文完成哈希计算,产生128 位的长整数

        } catch (NoSuchAlgorithmException aex) {
            logger.error("there is no " + ALGORITHM_MD5 + " Algorithm!");

        } catch (java.io.UnsupportedEncodingException uex) {
            logger.error("can not encode the password - " + uex.getMessage());

        }

        return keys;

    }

    /**
     * 将beye[]转换为十六进制字符串
     *
     * @param bArray
     * @return
     */

    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);

        String sTemp;

        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);

            if (sTemp.length() < 2) {
                sb.append(0);

            }

            sb.append(sTemp.toUpperCase());

        }

        return sb.toString();

    }

    public static void main(String[] args) {
        String a = "ddddddd";

        System.out.println(getPassMD5(a));

    }

}
