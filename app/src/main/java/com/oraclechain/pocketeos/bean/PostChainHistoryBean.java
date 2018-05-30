package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/2/1.
 * 获取json序列化 发送的json实体类
 */
public class PostChainHistoryBean {


    /**
     * account_name : inita
     * skip_seq : 2
     * num_seq : 1
     */

    private String account_name;
    private int skip_seq;
    private int num_seq;

    public String getAccount_name() {
        return account_name == null ? "" : account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public int getSkip_seq() {
        return skip_seq;
    }

    public void setSkip_seq(int skip_seq) {
        this.skip_seq = skip_seq;
    }

    public int getNum_seq() {
        return num_seq;
    }

    public void setNum_seq(int num_seq) {
        this.num_seq = num_seq;
    }
}
