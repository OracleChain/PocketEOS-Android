package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/6/15.
 */

public class UnstakeBean {

    /**
     * from : aaaaaavotera
     * receiver : aaaaaavotera
     * unstake_net_quantity : 10.0000 SYS
     * unstake_cpu_quantity : 10.0000 SYS
     */

    private String from;
    private String receiver;
    private String unstake_net_quantity;
    private String unstake_cpu_quantity;

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

    public String getUnstake_net_quantity() {
        return unstake_net_quantity;
    }

    public void setUnstake_net_quantity(String unstake_net_quantity) {
        this.unstake_net_quantity = unstake_net_quantity;
    }

    public String getUnstake_cpu_quantity() {
        return unstake_cpu_quantity;
    }

    public void setUnstake_cpu_quantity(String unstake_cpu_quantity) {
        this.unstake_cpu_quantity = unstake_cpu_quantity;
    }
}
