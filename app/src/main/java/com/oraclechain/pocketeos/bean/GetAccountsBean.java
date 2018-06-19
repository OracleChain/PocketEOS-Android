package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/6/13.
 */

public class GetAccountsBean {

    /**
     * code : 0
     * msg : SUCCESS
     * data : {"account_names":["oraclechain4"]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> account_names;

        public List<String> getAccount_names() {
            return account_names;
        }

        public void setAccount_names(List<String> account_names) {
            this.account_names = account_names;
        }
    }
}
