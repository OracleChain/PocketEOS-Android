package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/2/2.
 */

public class PaidAnswerBean {


    /**
     * code : 0
     * msg : SUCCESS
     * data : {"currentPage":0,"rowsPerPage":10,"totalPages":0,"totalRows":35,"data":[{"id":1,"endtime":1517561797,"from":"oct","oct":100,"releasedLable":0,"createtime":1517551797,"optionanswerscnt":3,"asktitle":"what is you name","optionanswers":"{\"A\":\"成吉思汗\",\"B\":\"毛泽东\",\"C\":\"拿破仑\"}"},{"id":2,"endtime":1517561797,"from":"oct","oct":100,"releasedLable":0,"createtime":1517551797,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":3,"endtime":1517561797,"from":"oct","oct":100,"releasedLable":0,"createtime":1517551797,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":5,"endtime":1517561798,"from":"oct","oct":100,"releasedLable":0,"createtime":1517551798,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":6,"endtime":1517562442,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"what is you name","optionanswers":"{\"A\":\"成吉思汗\",\"B\":\"毛泽东\",\"C\":\"拿破仑\"}"},{"id":7,"endtime":1517562442,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":8,"endtime":1517562442,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":9,"endtime":1517552542,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":10,"endtime":1517562442,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":11,"endtime":1517562443,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552443,"optionanswerscnt":3,"asktitle":"what is you name","optionanswers":"{\"A\":\"成吉思汗\",\"B\":\"毛泽东\",\"C\":\"拿破仑\"}"}]}
     */

    private String code;
    private String msg;
    private DataBeanX data;

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * currentPage : 0
         * rowsPerPage : 10
         * totalPages : 0
         * totalRows : 35
         * data : [{"id":1,"endtime":1517561797,"from":"oct","oct":100,"releasedLable":0,"createtime":1517551797,"optionanswerscnt":3,"asktitle":"what is you name","optionanswers":"{\"A\":\"成吉思汗\",\"B\":\"毛泽东\",\"C\":\"拿破仑\"}"},{"id":2,"endtime":1517561797,"from":"oct","oct":100,"releasedLable":0,"createtime":1517551797,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":3,"endtime":1517561797,"from":"oct","oct":100,"releasedLable":0,"createtime":1517551797,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":5,"endtime":1517561798,"from":"oct","oct":100,"releasedLable":0,"createtime":1517551798,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":6,"endtime":1517562442,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"what is you name","optionanswers":"{\"A\":\"成吉思汗\",\"B\":\"毛泽东\",\"C\":\"拿破仑\"}"},{"id":7,"endtime":1517562442,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":8,"endtime":1517562442,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":9,"endtime":1517552542,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":10,"endtime":1517562442,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552442,"optionanswerscnt":3,"asktitle":"欧联公司福利好","optionanswers":"{\"A\":\"是的\",\"B\":\"不是一般的好\",\"C\":\"非常好\"}"},{"id":11,"endtime":1517562443,"from":"oct","oct":100,"releasedLable":0,"createtime":1517552443,"optionanswerscnt":3,"asktitle":"what is you name","optionanswers":"{\"A\":\"成吉思汗\",\"B\":\"毛泽东\",\"C\":\"拿破仑\"}"}]
         */

        private int currentPage;
        private int rowsPerPage;
        private int totalPages;
        private int totalRows;
        private List<DataBean> data;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getRowsPerPage() {
            return rowsPerPage;
        }

        public void setRowsPerPage(int rowsPerPage) {
            this.rowsPerPage = rowsPerPage;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
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

        public static class DataBean implements Parcelable {
            /**
             * id : 1
             * endtime : 1517561797
             * from : oct
             * oct : 100
             * releasedLable : 0
             * createtime : 1517551797
             * optionanswerscnt : 3
             * asktitle : what is you name
             * optionanswers : {"A":"成吉思汗","B":"毛泽东","C":"拿破仑"}
             */

            private int id;
            private int endtime;
            private String from;
            private int oct;
            private int releasedLable;
            private int createtime;
            private int optionanswerscnt;
            private String asktitle;
            private String optionanswers;
            private String img = "";

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getEndtime() {
                return endtime;
            }

            public void setEndtime(int endtime) {
                this.endtime = endtime;
            }

            public String getFrom() {
                return from == null ? "" : from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public int getOct() {
                return oct;
            }

            public void setOct(int oct) {
                this.oct = oct;
            }

            public int getReleasedLable() {
                return releasedLable;
            }

            public void setReleasedLable(int releasedLable) {
                this.releasedLable = releasedLable;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getOptionanswerscnt() {
                return optionanswerscnt;
            }

            public void setOptionanswerscnt(int optionanswerscnt) {
                this.optionanswerscnt = optionanswerscnt;
            }

            public String getAsktitle() {
                return asktitle == null ? "" : asktitle;
            }

            public void setAsktitle(String asktitle) {
                this.asktitle = asktitle;
            }

            public String getOptionanswers() {
                return optionanswers == null ? "" : optionanswers;
            }

            public void setOptionanswers(String optionanswers) {
                this.optionanswers = optionanswers;
            }

            public String getImg() {
                return img == null ? "" : img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.endtime);
                dest.writeString(this.from);
                dest.writeInt(this.oct);
                dest.writeInt(this.releasedLable);
                dest.writeInt(this.createtime);
                dest.writeInt(this.optionanswerscnt);
                dest.writeString(this.asktitle);
                dest.writeString(this.optionanswers);
                dest.writeString(this.img);
            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {
                this.id = in.readInt();
                this.endtime = in.readInt();
                this.from = in.readString();
                this.oct = in.readInt();
                this.releasedLable = in.readInt();
                this.createtime = in.readInt();
                this.optionanswerscnt = in.readInt();
                this.asktitle = in.readString();
                this.optionanswers = in.readString();
                this.img = in.readString();
            }

            public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel source) {
                    return new DataBean(source);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };
        }
    }
}
