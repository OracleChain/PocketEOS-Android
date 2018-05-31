package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/2/1.
 */

public class QrCodeWalletBean {
    private String wallet_name = null;
    private String wallet_uid = null;
    private String wallet_img = null;
    private String type = null;

    public String getWallet_name() {
        return wallet_name == null ? "" : wallet_name;
    }

    public void setWallet_name(String wallet_name) {
        this.wallet_name = wallet_name;
    }

    public String getWallet_uid() {
        return wallet_uid == null ? "" : wallet_uid;
    }

    public void setWallet_uid(String wallet_uid) {
        this.wallet_uid = wallet_uid;
    }

    public String getWallet_img() {
        return wallet_img == null ? "" : wallet_img;
    }

    public void setWallet_img(String wallet_img) {
        this.wallet_img = wallet_img;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
