package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pocketEos on 2018/1/22.
 * 账号里面的代币bean
 */

public class AccountWithCoinBean implements Parcelable {
    private String coinName;
    private String coinImg;
    private String coinNumber;
    private String coinForCny;
    private String coinUpsAndDowns;
    private String eos_market_cap_usd;
    private String eos_market_cap_cny;
    private String oct_market_cap_usd;
    private String oct_market_cap_cny;
    private String oct_price_cny;
    private String eos_price_cny;

    public String getCoinName() {
        return coinName == null ? "" : coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinImg() {
        return coinImg == null ? "" : coinImg;
    }

    public void setCoinImg(String coinImg) {
        this.coinImg = coinImg;
    }

    public String getCoinNumber() {
        return coinNumber == null ? "" : coinNumber;
    }

    public void setCoinNumber(String coinNumber) {
        this.coinNumber = coinNumber;
    }

    public String getCoinForCny() {
        return coinForCny == null ? "" : coinForCny;
    }

    public void setCoinForCny(String coinForCny) {
        this.coinForCny = coinForCny;
    }

    public String getCoinUpsAndDowns() {
        return coinUpsAndDowns == null ? "" : coinUpsAndDowns;
    }

    public void setCoinUpsAndDowns(String coinUpsAndDowns) {
        this.coinUpsAndDowns = coinUpsAndDowns;
    }

    public String getEos_market_cap_usd() {
        return eos_market_cap_usd == null ? "" : eos_market_cap_usd;
    }

    public void setEos_market_cap_usd(String eos_market_cap_usd) {
        this.eos_market_cap_usd = eos_market_cap_usd;
    }

    public String getEos_market_cap_cny() {
        return eos_market_cap_cny == null ? "" : eos_market_cap_cny;
    }

    public void setEos_market_cap_cny(String eos_market_cap_cny) {
        this.eos_market_cap_cny = eos_market_cap_cny;
    }

    public String getOct_market_cap_usd() {
        return oct_market_cap_usd == null ? "" : oct_market_cap_usd;
    }

    public void setOct_market_cap_usd(String oct_market_cap_usd) {
        this.oct_market_cap_usd = oct_market_cap_usd;
    }

    public String getOct_market_cap_cny() {
        return oct_market_cap_cny == null ? "" : oct_market_cap_cny;
    }

    public void setOct_market_cap_cny(String oct_market_cap_cny) {
        this.oct_market_cap_cny = oct_market_cap_cny;
    }

    public String getOct_price_cny() {
        return oct_price_cny == null ? "" : oct_price_cny;
    }

    public void setOct_price_cny(String oct_price_cny) {
        this.oct_price_cny = oct_price_cny;
    }

    public String getEos_price_cny() {
        return eos_price_cny == null ? "" : eos_price_cny;
    }

    public void setEos_price_cny(String eos_price_cny) {
        this.eos_price_cny = eos_price_cny;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.coinName);
        dest.writeString(this.coinImg);
        dest.writeString(this.coinNumber);
        dest.writeString(this.coinForCny);
        dest.writeString(this.coinUpsAndDowns);
        dest.writeString(this.eos_market_cap_usd);
        dest.writeString(this.eos_market_cap_cny);
        dest.writeString(this.oct_market_cap_usd);
        dest.writeString(this.oct_market_cap_cny);
        dest.writeString(this.oct_price_cny);
        dest.writeString(this.eos_price_cny);
    }

    public AccountWithCoinBean() {
    }

    protected AccountWithCoinBean(Parcel in) {
        this.coinName = in.readString();
        this.coinImg = in.readString();
        this.coinNumber = in.readString();
        this.coinForCny = in.readString();
        this.coinUpsAndDowns = in.readString();
        this.eos_market_cap_usd = in.readString();
        this.eos_market_cap_cny = in.readString();
        this.oct_market_cap_usd = in.readString();
        this.oct_market_cap_cny = in.readString();
        this.oct_price_cny = in.readString();
        this.eos_price_cny = in.readString();
    }

    public static final Parcelable.Creator<AccountWithCoinBean> CREATOR = new Parcelable.Creator<AccountWithCoinBean>() {
        @Override
        public AccountWithCoinBean createFromParcel(Parcel source) {
            return new AccountWithCoinBean(source);
        }

        @Override
        public AccountWithCoinBean[] newArray(int size) {
            return new AccountWithCoinBean[size];
        }
    };
}
