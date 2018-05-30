package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pocketEos on 2018/2/1.
 * 资产收款二维码包含信息
 */

public class QrCodeMakeCollectionBean implements Parcelable {
    private String account_name = null;
    private String coin = null;
    private String money = null;
    private String type = null;

    public String getAccount_name() {
        return account_name == null ? "" : account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getCoin() {
        return coin == null ? "" : coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getMoney() {
        return money == null ? "" : money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.account_name);
        dest.writeString(this.coin);
        dest.writeString(this.money);
        dest.writeString(this.type);
    }

    public QrCodeMakeCollectionBean() {
    }

    protected QrCodeMakeCollectionBean(Parcel in) {
        this.account_name = in.readString();
        this.coin = in.readString();
        this.money = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<QrCodeMakeCollectionBean> CREATOR = new Parcelable.Creator<QrCodeMakeCollectionBean>() {
        @Override
        public QrCodeMakeCollectionBean createFromParcel(Parcel source) {
            return new QrCodeMakeCollectionBean(source);
        }

        @Override
        public QrCodeMakeCollectionBean[] newArray(int size) {
            return new QrCodeMakeCollectionBean[size];
        }
    };
}
