package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pocketEos on 2017/12/27.
 * 账号信息
 */

public class AccountInfoBean implements Parcelable {
    private String account_name;
    private String account_img;
    private String account_active_private_key;
    private String account_active_public_key;
    private String account_owner_private_key;
    private String account_owner_public_key;
    private String is_privacy_policy = "0";//0代表不保护1代表保护
    private String is_main_account = "0";//0代表不是主账号1代表是主账号

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

    public String getAccount_active_private_key() {
        return account_active_private_key == null ? "" : account_active_private_key;
    }

    public void setAccount_active_private_key(String account_active_private_key) {
        this.account_active_private_key = account_active_private_key;
    }

    public String getAccount_active_public_key() {
        return account_active_public_key == null ? "" : account_active_public_key;
    }

    public void setAccount_active_public_key(String account_active_public_key) {
        this.account_active_public_key = account_active_public_key;
    }

    public String getAccount_owner_private_key() {
        return account_owner_private_key == null ? "" : account_owner_private_key;
    }

    public void setAccount_owner_private_key(String account_owner_private_key) {
        this.account_owner_private_key = account_owner_private_key;
    }

    public String getAccount_owner_public_key() {
        return account_owner_public_key == null ? "" : account_owner_public_key;
    }

    public void setAccount_owner_public_key(String account_owner_public_key) {
        this.account_owner_public_key = account_owner_public_key;
    }

    public String getIs_privacy_policy() {
        return is_privacy_policy == null ? "" : is_privacy_policy;
    }

    public void setIs_privacy_policy(String is_privacy_policy) {
        this.is_privacy_policy = is_privacy_policy;
    }

    public String getIs_main_account() {
        return is_main_account == null ? "" : is_main_account;
    }

    public void setIs_main_account(String is_main_account) {
        this.is_main_account = is_main_account;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.account_name);
        dest.writeString(this.account_img);
        dest.writeString(this.account_active_private_key);
        dest.writeString(this.account_active_public_key);
        dest.writeString(this.account_owner_private_key);
        dest.writeString(this.account_owner_public_key);
        dest.writeString(this.is_privacy_policy);
        dest.writeString(this.is_main_account);
    }

    public AccountInfoBean() {
    }

    protected AccountInfoBean(Parcel in) {
        this.account_name = in.readString();
        this.account_img = in.readString();
        this.account_active_private_key = in.readString();
        this.account_active_public_key = in.readString();
        this.account_owner_private_key = in.readString();
        this.account_owner_public_key = in.readString();
        this.is_privacy_policy = in.readString();
        this.is_main_account = in.readString();
    }

    public static final Parcelable.Creator<AccountInfoBean> CREATOR = new Parcelable.Creator<AccountInfoBean>() {
        @Override
        public AccountInfoBean createFromParcel(Parcel source) {
            return new AccountInfoBean(source);
        }

        @Override
        public AccountInfoBean[] newArray(int size) {
            return new AccountInfoBean[size];
        }
    };
}
