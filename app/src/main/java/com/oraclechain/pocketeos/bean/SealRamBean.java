package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/6/22.
 */

public class SealRamBean {

    /**
     * account : aaa
     * bytes : 100
     */

    private String account;
    private int bytes;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }
}
