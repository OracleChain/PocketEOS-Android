package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/2/1.
 */

public class QrCodeAccountPrivateKeyBean {
    private String owner_private_key = null;
    private String active_private_key = null;
    private String account_name = null;
    private String type = null;

    public String getOwner_private_key() {
        return owner_private_key == null ? "" : owner_private_key;
    }

    public void setOwner_private_key(String owner_private_key) {
        this.owner_private_key = owner_private_key;
    }

    public String getActive_private_key() {
        return active_private_key == null ? "" : active_private_key;
    }

    public void setActive_private_key(String active_private_key) {
        this.active_private_key = active_private_key;
    }

    public String getAccount_name() {
        return account_name == null ? "" : account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
