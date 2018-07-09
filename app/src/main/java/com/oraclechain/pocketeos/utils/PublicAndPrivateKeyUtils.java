package com.oraclechain.pocketeos.utils;

import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.blockchain.cypto.ec.EosPrivateKey;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/2/3.
 */

public class PublicAndPrivateKeyUtils {


    //获取钱包下所有账号activepublickey
    public static List<String> getActivePublicKey() {
        List<String> keyList = new ArrayList<>();
        List<AccountInfoBean> mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);//遍历本地所有账号信息
        for (int i = 0; i < mAccountInfoBeanList.size(); i++) {
            keyList.add(mAccountInfoBeanList.get(i).getAccount_active_public_key());
        }
        return keyList;
    }

    //通过公钥获取私钥
    public static String getPrivateKey(String publicKey, String password) {
        String activePrivateKey = null;
        List<AccountInfoBean> mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);//遍历本地所有账号信息
        for (int i = 0; i < mAccountInfoBeanList.size(); i++) {
            if (mAccountInfoBeanList.get(i).getAccount_active_public_key().equals(publicKey)) {
                activePrivateKey = mAccountInfoBeanList.get(i).getAccount_active_private_key();
            }
        }
        if (activePrivateKey != null) {
            String key =null;
            try {
                key =  EncryptUtil.getDecryptString(activePrivateKey, password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            return key;
        } else {
            return null;
        }
    }

    public static EosPrivateKey[] getPrivateKey(int count) {
        EosPrivateKey[] retKeys = new EosPrivateKey[count];
        for (int i = 0; i < count; i++) {
            retKeys[i] = new EosPrivateKey();
        }

        return retKeys;
    }

}
