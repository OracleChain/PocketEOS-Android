package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/1/26.
 */

public class DappBean {

    /**
     * code : 0
     * message : ok
     * data : [{"id":1,"applyDetails":"算了","applyName":"吃烧烤","applyIcon":"2","weight":5,"introReason":"fegr"},{"id":1,"applyDetails":"算了","applyName":"吃烧烤","applyIcon":"2","weight":5,"introReason":"fegr"}]
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
         * applyDetails : 算了
         * applyName : 吃烧烤
         * applyIcon : 2
         * weight : 5
         * introReason : fegr
         *
         */

        private int id;
        private String applyDetails;
        private String applyName;
        private String applyIcon;
        private int weight;
        private String introReason;
        private String url;

        public String getUrl() {
            return url == null ? "" : url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getApplyDetails() {
            return applyDetails == null ? "" : applyDetails;
        }

        public void setApplyDetails(String applyDetails) {
            this.applyDetails = applyDetails;
        }

        public String getApplyName() {
            return applyName == null ? "" : applyName;
        }

        public void setApplyName(String applyName) {
            this.applyName = applyName;
        }

        public String getApplyIcon() {
            return applyIcon == null ? "" : applyIcon;
        }

        public void setApplyIcon(String applyIcon) {
            this.applyIcon = applyIcon;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getIntroReason() {
            return introReason == null ? "" : introReason;
        }

        public void setIntroReason(String introReason) {
            this.introReason = introReason;
        }
    }
}
