package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/1/25.
 */

public class MessageCenterBean {

    /**
     * code : 0
     * message : ok
     * data : [{"id":1,"title":"女宽松","scope":2,"assetCategoryId":2,"summary":null,"createTime":"01-16","updateTime":"01-16"},{"id":1,"title":"女宽松","scope":2,"assetCategoryId":2,"summary":null,"createTime":"01-16","updateTime":"01-16"},{"id":1,"title":"女宽松","scope":2,"assetCategoryId":2,"summary":null,"createTime":"01-16","updateTime":"01-16"},{"id":1,"title":"女宽松","scope":2,"assetCategoryId":2,"summary":null,"createTime":"01-16","updateTime":"01-16"},{"id":1,"title":"女宽松","scope":2,"assetCategoryId":2,"summary":null,"createTime":"01-16","updateTime":"01-16"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
         * id : 1
         * title : 女宽松
         * scope : 2
         * assetCategoryId : 2
         * summary : null
         * createTime : 01-16
         * updateTime : 01-16
         */

        private int id;
        private String title;
        private int scope;
        private int assetCategoryId;
        private String summary;
        private String createTime;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getScope() {
            return scope;
        }

        public void setScope(int scope) {
            this.scope = scope;
        }

        public int getAssetCategoryId() {
            return assetCategoryId;
        }

        public void setAssetCategoryId(int assetCategoryId) {
            this.assetCategoryId = assetCategoryId;
        }

        public String getSummary() {
            return summary == null ? "" : summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
