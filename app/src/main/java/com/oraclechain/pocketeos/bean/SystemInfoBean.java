package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/1/31.
 */

public class SystemInfoBean {

    /**
     * code : 0
     * message : ok
     * data : {"content":"456"}
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
        return message == null ? "" : message;
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
         * content : 456
         */

        private String content;

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
