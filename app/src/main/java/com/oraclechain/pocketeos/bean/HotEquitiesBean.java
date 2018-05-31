package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/5/26.
 */

public class HotEquitiesBean {


    /**
     * code : 0
     * data : [{"id":"65fd4b7e66614aceb0e38fd711248e7b","title":"兑换eos","description":"这个可以兑换eos","avatar":"0","weight":1,"exchangeTimes":0,"scoreValue":10,"tokenValue":0,"tokenType":"EOS","status":1,"exchangeUrl":"0","createTime":"2018-05-26 10:05:20","updateTime":"2018-05-26 10:05:20"},{"id":"013efe83beff4ca2b031c2714ac0737a","title":"第2个权益","description":"权益描述","avatar":"ip地址","weight":7,"exchangeTimes":0,"scoreValue":10,"tokenValue":1.05,"tokenType":"EOS","status":1,"exchangeUrl":"0","createTime":"2018-05-26 10:06:33","updateTime":"2018-05-26 10:06:33"},{"id":"3d8a2c3f465f4c368c6561469821f5e0","title":"第7个权益","description":"权益描述","avatar":"ip地址","weight":7,"exchangeTimes":0,"scoreValue":10,"tokenValue":1.05,"tokenType":"EOS","status":1,"exchangeUrl":"0","createTime":"2018-05-26 10:06:21","updateTime":"2018-05-26 10:06:21"},{"id":"5649d1047ea240eeb479d7aaf2518aff","title":"第6个权益","description":"权益描述","avatar":"ip地址","weight":7,"exchangeTimes":0,"scoreValue":10,"tokenValue":1.05,"tokenType":"EOS","status":1,"exchangeUrl":"0","createTime":"2018-05-26 10:06:53","updateTime":"2018-05-26 10:06:53"},{"id":"82af1a2aa2e94ffca7d3b8002ac1d7ac","title":"第4个权益","description":"权益描述","avatar":"ip地址","weight":7,"exchangeTimes":0,"scoreValue":10,"tokenValue":1.05,"tokenType":"EOS","status":1,"exchangeUrl":"0","createTime":"2018-05-26 10:06:44","updateTime":"2018-05-26 10:06:44"},{"id":"845892bcfd554798ae9233247701079c","title":"第3个权益","description":"权益描述","avatar":"ip地址","weight":7,"exchangeTimes":0,"scoreValue":10,"tokenValue":1.05,"tokenType":"EOS","status":1,"exchangeUrl":"0","createTime":"2018-05-26 10:06:36","updateTime":"2018-05-26 10:06:36"},{"id":"9caa8b311ce7476c9b08d37c90cff4f6","title":"第5个权益","description":"权益描述","avatar":"ip地址","weight":7,"exchangeTimes":0,"scoreValue":10,"tokenValue":1.05,"tokenType":"EOS","status":1,"exchangeUrl":"0","createTime":"2018-05-26 10:06:47","updateTime":"2018-05-26 10:06:47"},{"id":"eb09a8892feb4e37b82855fd34d37a79","title":"第1个权益","description":"权益描述","avatar":"ip地址","weight":7,"exchangeTimes":0,"scoreValue":10,"tokenValue":1.05,"tokenType":"EOS","status":1,"exchangeUrl":"0","createTime":"2018-05-26 10:06:30","updateTime":"2018-05-26 10:06:30"}]
     * message : SUCCEED
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
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 65fd4b7e66614aceb0e38fd711248e7b
         * title : 兑换eos
         * description : 这个可以兑换eos
         * avatar : 0
         * weight : 1
         * exchangeTimes : 0
         * scoreValue : 10
         * tokenValue : 0.0
         * tokenType : EOS
         * status : 1
         * exchangeUrl : 0
         * createTime : 2018-05-26 10:05:20
         * updateTime : 2018-05-26 10:05:20
         */

        private String id;
        private String title;
        private String description;
        private String avatar;
        private int weight;
        private int exchangeTimes;
        private int scoreValue;
        private double tokenValue;
        private String tokenType;
        private int status;
        private String exchangeUrl;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getExchangeTimes() {
            return exchangeTimes;
        }

        public void setExchangeTimes(int exchangeTimes) {
            this.exchangeTimes = exchangeTimes;
        }

        public int getScoreValue() {
            return scoreValue;
        }

        public void setScoreValue(int scoreValue) {
            this.scoreValue = scoreValue;
        }

        public double getTokenValue() {
            return tokenValue;
        }

        public void setTokenValue(double tokenValue) {
            this.tokenValue = tokenValue;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getExchangeUrl() {
            return exchangeUrl;
        }

        public void setExchangeUrl(String exchangeUrl) {
            this.exchangeUrl = exchangeUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
