package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/6/12.
 */

public class RegproxyMessageBean {

    /**
     * proxy : producerc
     * isproxy : 0
     */

    private String proxy;
    private String isproxy = "1";

    public RegproxyMessageBean(String proxy, String isproxy) {
        this.proxy = proxy;
        this.isproxy = isproxy;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getIsproxy() {
        return isproxy;
    }

    public void setIsproxy(String isproxy) {
        this.isproxy = isproxy;
    }
}
