package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/5/25.
 */

public class AnswerMessageBean {

    /**
     * from : answerb
     * askid : 1
     * choosedanswer : 1
     */

    private String from;
    private String askid;
    private String choosedanswer;

    public AnswerMessageBean(String from, String askid, String choosedanswer) {
        this.from = from;
        this.askid = askid;
        this.choosedanswer = choosedanswer;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAskid() {
        return askid;
    }

    public void setAskid(String askid) {
        this.askid = askid;
    }

    public String getChoosedanswer() {
        return choosedanswer;
    }

    public void setChoosedanswer(String choosedanswer) {
        this.choosedanswer = choosedanswer;
    }
}
