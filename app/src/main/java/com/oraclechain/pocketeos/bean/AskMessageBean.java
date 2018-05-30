package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/5/11.
 */

public class AskMessageBean {

    /**
     * id : 1
     * from : askera
     * quantity : 2.0000 OCT
     * createtime : 0
     * endtime : 200
     * optionanswerscnt : 3
     * asktitle : what is you name
     * optionanswers : {"A":"成吉思汗","B":"毛泽东","C":"拿破仑"}
     */

    private String id;
    private String from;
    private String quantity;
    private String createtime;
    private String endtime;
    private String optionanswerscnt;
    private String asktitle;
    private String optionanswers;

    public AskMessageBean(String id, String from, String quantity, String createtime, String endtime, String optionanswerscnt, String asktitle, String optionanswers) {
        this.id = id;
        this.from = from;
        this.quantity = quantity;
        this.createtime = createtime;
        this.endtime = endtime;
        this.optionanswerscnt = optionanswerscnt;
        this.asktitle = asktitle;
        this.optionanswers = optionanswers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getOptionanswerscnt() {
        return optionanswerscnt;
    }

    public void setOptionanswerscnt(String optionanswerscnt) {
        this.optionanswerscnt = optionanswerscnt;
    }

    public String getAsktitle() {
        return asktitle;
    }

    public void setAsktitle(String asktitle) {
        this.asktitle = asktitle;
    }

    public String getOptionanswers() {
        return optionanswers;
    }

    public void setOptionanswers(String optionanswers) {
        this.optionanswers = optionanswers;
    }
}
