package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/2/2.
 */

public class PaidAnswerBean {

    /**
     * code : 0
     * msg : SUCCESS
     * data : {"currentPage":0,"rowsPerPage":10,"totalPages":0,"totalRows":3,"data":[{"id":3,"endtime":1527953025,"from":"qwer111","quantity":"10.0000 OCT","releasedLable":0,"createtime":1527945829,"optionanswerscnt":2,"asktitle":"{\"content\":\"test\",\"title\":\"test5\"}","optionanswers":"{\"A\":\"q\",\"B\":\"a\"}"},{"id":2,"endtime":3058318778,"from":"websocket1","quantity":"10.0000 OCT","releasedLable":0,"createtime":1527944378,"optionanswerscnt":2,"asktitle":"{\"content\":\"Mishap\",\"title\":\"Danica\\t\\t\"}","optionanswers":"{\"A\":\"1\",\"B\":\"2\"}"},{"id":1,"endtime":1528041609,"from":"qwer111","quantity":"10.0000 OCT","releasedLable":0,"createtime":1527944296,"optionanswerscnt":2,"asktitle":"{\"content\":\"test\",\"title\":\"test\"}","optionanswers":"{\"A\":\"a\",\"B\":\"b\"}"}]}
     */

    private String code;
    private String msg;
    private DataBeanX data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
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
         * totalRows : 3
         * data : [{"id":3,"endtime":1527953025,"from":"qwer111","quantity":"10.0000 OCT","releasedLable":0,"createtime":1527945829,"optionanswerscnt":2,"asktitle":"{\"content\":\"test\",\"title\":\"test5\"}","optionanswers":"{\"A\":\"q\",\"B\":\"a\"}"},{"id":2,"endtime":3058318778,"from":"websocket1","quantity":"10.0000 OCT","releasedLable":0,"createtime":1527944378,"optionanswerscnt":2,"asktitle":"{\"content\":\"Mishap\",\"title\":\"Danica\\t\\t\"}","optionanswers":"{\"A\":\"1\",\"B\":\"2\"}"},{"id":1,"endtime":1528041609,"from":"qwer111","quantity":"10.0000 OCT","releasedLable":0,"createtime":1527944296,"optionanswerscnt":2,"asktitle":"{\"content\":\"test\",\"title\":\"test\"}","optionanswers":"{\"A\":\"a\",\"B\":\"b\"}"}]
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
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 3
             * endtime : 1527953025
             * from : qwer111
             * quantity : 10.0000 OCT
             * releasedLable : 0
             * createtime : 1527945829
             * optionanswerscnt : 2
             * asktitle : {"content":"test","title":"test5"}
             * optionanswers : {"A":"q","B":"a"}
             */

            private int id;
            private String endtime;
            private String from;
            private String quantity;
            private int releasedLable;
            private String createtime;
            private int optionanswerscnt;
            private String asktitle;
            private String optionanswers;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getEndtime() {
                return endtime == null ? "" : endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
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

            public int getReleasedLable() {
                return releasedLable;
            }

            public void setReleasedLable(int releasedLable) {
                this.releasedLable = releasedLable;
            }

            public String getCreatetime() {
                return createtime == null ? "" : createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getOptionanswerscnt() {
                return optionanswerscnt;
            }

            public void setOptionanswerscnt(int optionanswerscnt) {
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
    }
}
