package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/2/6.
 */

public class ChainAsktitleBean {
    @Override
    public String toString() {
        return "ChainAsktitleBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    private String title;
    private String content;

    public ChainAsktitleBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
