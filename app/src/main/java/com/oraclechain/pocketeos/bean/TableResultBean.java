package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/6/22.
 */

public class TableResultBean {

    /**
     * code : 0
     * message : ok
     * data : {"more":false,"rows":[{"quote":{"balance":"1251817.7221 EOS","weight":"0.50000000000000000"},"supply":"10000000000.0000 RAMCORE","base":{"balance":"54895975262 RAM","weight":"0.50000000000000000"}}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * more : false
         * rows : [{"quote":{"balance":"1251817.7221 EOS","weight":"0.50000000000000000"},"supply":"10000000000.0000 RAMCORE","base":{"balance":"54895975262 RAM","weight":"0.50000000000000000"}}]
         */

        private boolean more;
        private List<RowsBean> rows;

        public boolean isMore() {
            return more;
        }

        public void setMore(boolean more) {
            this.more = more;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * quote : {"balance":"1251817.7221 EOS","weight":"0.50000000000000000"}
             * supply : 10000000000.0000 RAMCORE
             * base : {"balance":"54895975262 RAM","weight":"0.50000000000000000"}
             */

            private QuoteBean quote;
            private String supply;
            private BaseBean base;

            public QuoteBean getQuote() {
                return quote;
            }

            public void setQuote(QuoteBean quote) {
                this.quote = quote;
            }

            public String getSupply() {
                return supply;
            }

            public void setSupply(String supply) {
                this.supply = supply;
            }

            public BaseBean getBase() {
                return base;
            }

            public void setBase(BaseBean base) {
                this.base = base;
            }

            public static class QuoteBean {
                /**
                 * balance : 1251817.7221 EOS
                 * weight : 0.50000000000000000
                 */

                private String balance;
                private String weight;

                public String getBalance() {
                    return balance;
                }

                public void setBalance(String balance) {
                    this.balance = balance;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }
            }

            public static class BaseBean {
                /**
                 * balance : 54895975262 RAM
                 * weight : 0.50000000000000000
                 */

                private String balance;
                private String weight;

                public String getBalance() {
                    return balance;
                }

                public void setBalance(String balance) {
                    this.balance = balance;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }
            }
        }
    }
}
