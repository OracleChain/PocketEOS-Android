package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2017/12/27.
 * 关注列表Bean
 */

public class FriendsListInfoBean {


    /**
     * uid : 1
     * dispalyName : shoayuanbin
     * avatar : http://b.hiphotos.baidu.com/image/h%3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg
     * followType : 1
     */

    private String uid;
    private String displayName;
    private String avatar;
    private String followType;

    public String getUid() {
        return uid == null ? "" : uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName == null ? "" : displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar == null ? "" : avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFollowType() {
        return followType == null ? "" : followType;
    }

    public void setFollowType(String followType) {
        this.followType = followType;
    }
}

