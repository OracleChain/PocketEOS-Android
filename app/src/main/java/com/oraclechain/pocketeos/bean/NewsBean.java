package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2017/12/28.
 */

public class NewsBean {

    /**
     * code : 0
     * message : ok
     * data : [{"id":12,"title":"它风石着志里得话传维写安系看","summary":"计风行两但下响三存明问点即下属情权往电最","scope":0,"assetCategoryId":1,"imageUrl":"http://---2--P.y-.3X-.w.-.-ao","newsUrl":"http://---2--P.y-.3X-.w.-.-ao","publisher":"数先十该离代验能海图","status":null,"releaseTime":"2023-10-17 21:22:51","createTime":"2018-01-09 10:48:13","updateTime":"2018-01-09 10:48:13"},{"id":7,"title":"才照百从已常真转将提业者专器","summary":"间家学林装近将果听该干装酸提毛究确已龙安点处列没问车前才直九","scope":0,"assetCategoryId":1,"imageUrl":"http://----.MZ.---n-.w-h-","newsUrl":"http://--W.--1H-L-.--W--YF.DF--.yn--CpH.H5-.jes-8","publisher":"那己温与特军百越完收实式","status":null,"releaseTime":"2042-12-30 21:23:53","createTime":"2018-01-09 10:43:31","updateTime":"2018-01-09 10:43:31"},{"id":1,"title":"空维属少华点是少它两近求式四书强书分没又加件际基常","summary":"结近切了所变特开认别手织许共消很结青回育共她上热米叫机","scope":2,"assetCategoryId":1,"imageUrl":"http://-G5h0.AZ.--?:~","newsUrl":"值省位把小斗革根整发连","publisher":"值省位把小斗革根整发连","status":null,"releaseTime":"2025-05-14 19:57:46","createTime":"2018-01-09 10:34:22","updateTime":"2018-01-09 10:39:22"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 12
         * title : 它风石着志里得话传维写安系看
         * summary : 计风行两但下响三存明问点即下属情权往电最
         * scope : 0
         * assetCategoryId : 1
         * imageUrl : http://---2--P.y-.3X-.w.-.-ao
         * newsUrl : http://---2--P.y-.3X-.w.-.-ao
         * publisher : 数先十该离代验能海图
         * status : null
         * releaseTime : 2023-10-17 21:22:51
         * createTime : 2018-01-09 10:48:13
         * updateTime : 2018-01-09 10:48:13
         */

        private String id;
        private String title;
        private String summary;
        private String scope;
        private String assetCategoryId;
        private String imageUrl;
        private String newsUrl;
        private String publisher;
        private Object status;
        private String releaseTime;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary == null ? "" : summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getScope() {
            return scope == null ? "" : scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getAssetCategoryId() {
            return assetCategoryId == null ? "" : assetCategoryId;
        }

        public void setAssetCategoryId(String assetCategoryId) {
            this.assetCategoryId = assetCategoryId;
        }

        public String getImageUrl() {
            return imageUrl == null ? "" : imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getNewsUrl() {
            return newsUrl == null ? "" : newsUrl;
        }

        public void setNewsUrl(String newsUrl) {
            this.newsUrl = newsUrl;
        }

        public String getPublisher() {
            return publisher == null ? "" : publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getReleaseTime() {
            return releaseTime == null ? "" : releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
