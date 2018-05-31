package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/3/26.
 */

public class WXAccessTokenBean {

    /**
     * access_token : 8_QVTiEND-ZaLlfaxtjxXBIlNV636RwrCYhOwAcsjPqTIBF0vmbqtJ3mzn8u4Bk_QUpvAnW7XzwkgKH6jDVRhj6PXPLd1ywk5DJunp7lhU1hU
     * expires_in : 7200
     * refresh_token : 8_ObgBLbB5lrHEMtJfcyznScLFswobaSIfLgNMkkNPjA3bT9BPHSxKZcVhQpl7OaGPmDBoYa3tNH3VaaxhutSMMh90JEw9XMQ_fcp1o7_1QMY
     * openid : o-GNX1AMjTEMIT-qkfBxfES7Wkxo
     * scope : snsapi_userinfo
     * unionid : oZKTr0_JfnUdpXlm9ZSOXk8gbEu0
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token == null ? "" : access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token == null ? "" : refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid == null ? "" : openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope == null ? "" : scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid == null ? "" : unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
