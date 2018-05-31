package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/1/24.
 */

public class PelistBean {

    /**
     * code : 0
     * message : ok
     * data : [{"uid":"1","displayName":"www","avatar":"http://b.hiphotos.baidu.com/image/h%3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg"},{"uid":"1","displayName":"www","avatar":"http://b.hiphotos.baidu.com/image/h%3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 1
         * displayName : www
         * avatar : http://b.hiphotos.baidu.com/image/h%3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg
         */

        private String uid;
        private String displayName;
        private String avatar;

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
    }
}
