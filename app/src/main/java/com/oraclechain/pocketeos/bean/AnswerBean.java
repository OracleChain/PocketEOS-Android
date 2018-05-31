package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/1/11.
 */

public class AnswerBean {
    private String answer_option;
    private String answer_details;

    public String getAnswer_option() {
        return answer_option == null ? "" : answer_option;
    }

    public void setAnswer_option(String answer_option) {
        this.answer_option = answer_option;
    }

    public String getAnswer_details() {
        return answer_details == null ? "" : answer_details;
    }

    public void setAnswer_details(String answer_details) {
        this.answer_details = answer_details;
    }
}
