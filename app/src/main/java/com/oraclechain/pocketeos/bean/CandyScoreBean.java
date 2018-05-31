package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/5/25.
 */

public class CandyScoreBean {

    /**
     * code : 0
     * data : {"id":7,"uid":"996a970e8a879d0919f06886cd3508a9","scoreNum":0,"createTime":"2018-05-24 09:53:32","updateTime":"2018-05-24 09:53:32"}
     * message : SUCCEED
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * id : 7
         * uid : 996a970e8a879d0919f06886cd3508a9
         * scoreNum : 0
         * createTime : 2018-05-24 09:53:32
         * updateTime : 2018-05-24 09:53:32
         */

        private int id;
        private String uid;
        private int scoreNum;
        private String createTime;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getScoreNum() {
            return scoreNum;
        }

        public void setScoreNum(int scoreNum) {
            this.scoreNum = scoreNum;
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
