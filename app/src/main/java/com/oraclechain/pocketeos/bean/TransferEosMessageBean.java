package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/5/3.
 */

public class TransferEosMessageBean {

    /**
     * memo :
     * to :
     * quantity :
     * from :
     */

    private String memo;
    private String to;
    private String quantity;
    private String from;

    public TransferEosMessageBean(String memo, String to, String quantity, String from) {
        this.memo = memo;
        this.to = to;
        this.quantity = quantity;
        this.from = from;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
