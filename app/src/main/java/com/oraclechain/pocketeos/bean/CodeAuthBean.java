package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/1/18.
 */

public class CodeAuthBean {

    /**
     * code : 0
     * message : 验证码验证成功
     * data : {"uid":"d553e2e94b9b888341fe1d572b6720b5"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : d553e2e94b9b888341fe1d572b6720b5
         */

        private String uid;

        public String getUid() {
            return uid == null ? "" : uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
