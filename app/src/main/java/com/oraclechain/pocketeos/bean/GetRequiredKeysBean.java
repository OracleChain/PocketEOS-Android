package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/1/27.
 */

public class GetRequiredKeysBean {


    /**
     * code : 0
     * message : ok
     * data : {"required_keys":["EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV"]}
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
        private List<String> required_keys;

        public List<String> getRequired_keys() {
            if (required_keys == null) {
                return new ArrayList<>();
            }
            return required_keys;
        }

        public void setRequired_keys(List<String> required_keys) {
            this.required_keys = required_keys;
        }
    }
}
