package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/4/24.
 */

public class AuthRedPacketBean {

    /**
     * msg : SUCCEED
     * code : 0
     * data : {"id":"9c865d2f31424ec0b44674f2da8b6f2b","verifyString":"9c865d2f31424ec0b44674f2da8b6f2b","endTime":"2018-04-16"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 9c865d2f31424ec0b44674f2da8b6f2b
         * verifyString : 9c865d2f31424ec0b44674f2da8b6f2b
         * endTime : 2018-04-16
         */

        private String id;
        private String verifyString;
        private String endTime;

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVerifyString() {
            return verifyString == null ? "" : verifyString;
        }

        public void setVerifyString(String verifyString) {
            this.verifyString = verifyString;
        }

        public String getEndTime() {
            return endTime == null ? "" : endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
