package com.oraclechain.pocketeos.modules.otherloginorshare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pocketEos on 2018/3/23.
 */

public class WechatInfoBean implements Parcelable {
    private int errCode;

    private String openid;

    private int sex;

    private String nickname;

    private String headimgurl;

    private String province;

    private String language;

    private String country;

    private String unionid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSex() {
        return (sex == 0) ? "男" : "女";
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    @Override
    public String toString() {
        return "WeChatInfo{" +
                "errCode='" + errCode + '\'' +
                ", openid='" + openid + '\'' +
                ", sex=" + sex +
                ", nickname='" + nickname + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", province='" + province + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.errCode);
        dest.writeString(this.openid);
        dest.writeInt(this.sex);
        dest.writeString(this.nickname);
        dest.writeString(this.headimgurl);
        dest.writeString(this.province);
        dest.writeString(this.language);
        dest.writeString(this.country);
        dest.writeString(this.unionid);
    }

    public WechatInfoBean() {
    }

    protected WechatInfoBean(Parcel in) {
        this.errCode = in.readInt();
        this.openid = in.readString();
        this.sex = in.readInt();
        this.nickname = in.readString();
        this.headimgurl = in.readString();
        this.province = in.readString();
        this.language = in.readString();
        this.country = in.readString();
        this.unionid = in.readString();
    }

    public static final Parcelable.Creator<WechatInfoBean> CREATOR = new Parcelable.Creator<WechatInfoBean>() {
        @Override
        public WechatInfoBean createFromParcel(Parcel source) {
            return new WechatInfoBean(source);
        }

        @Override
        public WechatInfoBean[] newArray(int size) {
            return new WechatInfoBean[size];
        }
    };
}
