package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/2/7.
 */

public class PostChainAnswerJsonBean {

    /**
     * action : answer
     * args : {"from":"zhaobo","askid":"300","choosedanswer":"1"}
     * code : oct
     */

    private String action;
    private ArgsBean args;
    private String code;

    public String getAction() {
        return action == null ? "" : action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArgsBean getArgs() {
        return args;
    }

    public void setArgs(ArgsBean args) {
        this.args = args;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class ArgsBean {
        /**
         * from : zhaobo
         * askid : 300
         * choosedanswer : 1
         */

        private String from;
        private String askid;
        private String choosedanswer;

        public String getFrom() {
            return from == null ? "" : from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getAskid() {
            return askid == null ? "" : askid;
        }

        public void setAskid(String askid) {
            this.askid = askid;
        }

        public String getChoosedanswer() {
            return choosedanswer == null ? "" : choosedanswer;
        }

        public void setChoosedanswer(String choosedanswer) {
            this.choosedanswer = choosedanswer;
        }
    }
}
