package com.oraclechain.pocketeos.bean;


/**
 * Created by pocketEos on 2017/11/30.
 */
public class User {
    private String uid;
    private String name;
    private String avatar;
    private String letter;//索引字母
    private String friend_type;//1代表钱包2代表账号

    public String getUid() {
        return uid == null ? "" : uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar == null ? "" : avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLetter() {
        return letter == null ? "" : letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getFriend_type() {
        return friend_type == null ? "" : friend_type;
    }

    public void setFriend_type(String friend_type) {
        this.friend_type = friend_type;
    }
}
