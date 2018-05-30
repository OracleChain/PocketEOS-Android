package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/1/27.
 */

public class GetChainJsonBean {


    /**
     * code : 0
     * message : ok
     * data : {"binargs":"000000008093dd74000000000094dd74e803000000000000","required_scope":[],"required_auth":[]}
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
         * binargs : 000000008093dd74000000000094dd74e803000000000000
         * required_scope : []
         * required_auth : []
         */

        private String binargs;
        private List<?> required_scope;
        private List<?> required_auth;

        public String getBinargs() {
            return binargs == null ? "" : binargs;
        }

        public void setBinargs(String binargs) {
            this.binargs = binargs;
        }

        public List<?> getRequired_scope() {
            if (required_scope == null) {
                return new ArrayList<>();
            }
            return required_scope;
        }

        public void setRequired_scope(List<?> required_scope) {
            this.required_scope = required_scope;
        }

        public List<?> getRequired_auth() {
            if (required_auth == null) {
                return new ArrayList<>();
            }
            return required_auth;
        }

        public void setRequired_auth(List<?> required_auth) {
            this.required_auth = required_auth;
        }
    }
}
