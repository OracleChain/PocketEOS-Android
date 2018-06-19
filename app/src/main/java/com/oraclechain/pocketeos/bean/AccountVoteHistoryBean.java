package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/6/9.
 */

public class AccountVoteHistoryBean {
    String producers;
    String number ;

    public String getProducers() {
        return producers == null ? "" : producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getNumber() {
        return number == null ? "" : number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
