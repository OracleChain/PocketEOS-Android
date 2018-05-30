package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/5/7.
 */

public class GetAnswerListBean {

    /**
     * askid : -1
     * page : {"pageNum":0,"pageSize":100}
     * releasedLable : 0
     */

    private String askid;
    private PageBean page;
    private String releasedLable;

    public GetAnswerListBean(String askid, PageBean page, String releasedLable) {
        this.askid = askid;
        this.page = page;
        this.releasedLable = releasedLable;
    }

    public String getAskid() {
        return askid;
    }

    public void setAskid(String askid) {
        this.askid = askid;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public String getReleasedLable() {
        return releasedLable;
    }

    public void setReleasedLable(String releasedLable) {
        this.releasedLable = releasedLable;
    }

    public static class PageBean {
        /**
         * pageNum : 0
         * pageSize : 100
         */

        private int pageNum;
        private int pageSize;

        public PageBean(int pageNum, int pageSize) {
            this.pageNum = pageNum;
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }
}
