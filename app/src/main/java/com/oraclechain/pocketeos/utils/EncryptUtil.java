package com.oraclechain.pocketeos.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.util.encoders.Hex;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by pocketEos on 2018/5/26.
 * 加密工具类
 */
public class EncryptUtil {


    /**
     * The constant KEY_ALGORITHM.
     */
    static final String KEY_ALGORITHM = "AES";
    /**
     * The constant algorithmStr.
     */
    static final String algorithmStr = "AES/CBC/PKCS7Padding";
    /**
     * The Iv.
     */
    static byte[] iv = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    //
    private static Key key;
    private static Cipher cipher;

    /**
     * Gets encrypt string.
     *
     * @param content  the content
     * @param password the password
     * @return the encrypt string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    public static String getEncryptString(String content, String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String salt = getRandomString(32);//盐
        char[] chars = password.toCharArray();//加密密码明文

        byte[] encryPassword = pbkdf2(chars, salt.getBytes(), 1000, 32);
        // 加密
        byte[] enc = encrypt(content.getBytes(), encryPassword);
        return salt + new String(Hex.encode(enc));
    }

    /**
     * Gets decrypt string.
     *
     * @param content  the content
     * @param password the password
     * @return the decrypt string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    public static String getDecryptString(String content, String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String  salt = content.substring(0,32);//盐
        char[] chars = password.toCharArray();//加密密码明文

        byte[] encryPassword = pbkdf2(chars, salt.getBytes(), 1000, 32);
        // 解密
        String dncstr =content.substring(32 , content.length());
        byte[] dec = decrypt(Hex.decode(dncstr), encryPassword);
        return new String(dec);
    }

    /**
     * Gets random string.
     *
     * @param length the length
     * @return random string
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * Computes the PBKDF2 hash of a password.
     *
     * @param password   the password to hash.   需要加密的明文密码
     * @param salt       the salt                盐增加调味 增加密码破解难度
     * @param iterations the iteration count (slowness factor)  迭代次数
     * @param bytes      the length of the hash to compute in bytes  计算密码后的 哈希长度
     * @return the PBDKF2 hash of the password
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    public static byte[] pbkdf2(char[] password, byte[] salt,
                                int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * 加密方法
     *
     * @param content  要加密的字符串
     * @param keyBytes 加密密钥
     * @return byte [ ]
     */
    public static byte[] encrypt(byte[] content, byte[] keyBytes) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            encryptedText = cipher.doFinal(content);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedText;
    }

    /**
     * Init.
     *
     * @param keyBytes the key bytes
     */
    public static void init(byte[] keyBytes) {

        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @return byte [ ]
     */
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedText;
    }
}
