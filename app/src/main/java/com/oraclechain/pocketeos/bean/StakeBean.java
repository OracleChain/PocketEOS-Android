package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/6/22.
 */

public class StakeBean {

    /**
     * from : aaa
     * receiver : aaa
     * stake_net_quantity : 0.0001 SYS
     * stake_cpu_quantity : 0.0001 SYS
     * transfer : false
     */

    private String from;
    private String receiver;
    private String stake_net_quantity;
    private String stake_cpu_quantity;
    private boolean transfer = false;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStake_net_quantity() {
        return stake_net_quantity;
    }

    public void setStake_net_quantity(String stake_net_quantity) {
        this.stake_net_quantity = stake_net_quantity;
    }

    public String getStake_cpu_quantity() {
        return stake_cpu_quantity;
    }

    public void setStake_cpu_quantity(String stake_cpu_quantity) {
        this.stake_cpu_quantity = stake_cpu_quantity;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }
}
