package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/1/15.
 */

public class BaseBean {
    private String code ;
    private String message ;

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
