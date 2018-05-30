package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/2/1.
 */

public class QrCodeAccountBean {
    private String account_name = null;
    private String account_img = null;
    private String type = null;

    public String getAccount_name() {
        return account_name == null ? "" : account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_img() {
        return account_img == null ? "" : account_img;
    }

    public void setAccount_img(String account_img) {
        this.account_img = account_img;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
