package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/6/11.
 */

public class RequestNodeListBean {


    /**
     * pageNum : 0
     * pageSize : 10
     */

    private String pageNum;
    private String pageSize;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
