package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/6/12.
 */

public class DelegatebwMessageBean {

    /**
     * from : aaaaaavotera
     * receiver : aaaaaavotera
     * stake_net_quantity : 10.0000 SYS
     * stake_cpu_quantity : 10.0000 SYS
     * transfer : 0
     */

    private String from;
    private String receiver;
    private String stake_net_quantity;
    private String stake_cpu_quantity;
    private String transfer = "0";

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

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }
}
