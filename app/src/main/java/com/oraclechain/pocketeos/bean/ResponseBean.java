package com.oraclechain.pocketeos.bean;

import java.io.Serializable;

/**
 * Created by pocketEos on 2018/4/2.
 */


public class ResponseBean<T> implements Serializable {

    public int code;
    public String message;
    public T data = null;

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}