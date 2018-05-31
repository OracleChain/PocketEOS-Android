package com.oraclechain.pocketeos.utils;

import com.oraclechain.pocketeos.app.MyApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pocketEos on 2018/1/19.
 * 加密工具类
 */
public class PasswordToKeyUtils {
    /**
     * 密码校验
     *
     * @param strSrc 明文
     * @return 加密之后的密文 string
     */
    public static String shaCheck(String strSrc) {
        if (MyApplication.getInstance().getUserBean().getWallet_shapwd() == null) {
            return null;
        }
        String salt = MyApplication.getInstance().getUserBean().getWallet_shapwd().substring(0, 32);//盐
        MessageDigest md = null;
        String strDes = null;
        String checkPwd = salt + strSrc;
        byte[] bt = checkPwd.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        String pwdStrDes = salt + strDes;
        return pwdStrDes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts 数据源
     * @return 16进制字符串 string
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * SHA加密
     *
     * @param strSrc 明文
     * @return 加密之后的密文 string
     */
    public static String shaEncrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        String salt = strSrc.substring(0, 32);//盐
        String pwdStrDes = salt + strDes;
        return pwdStrDes;
    }
}
