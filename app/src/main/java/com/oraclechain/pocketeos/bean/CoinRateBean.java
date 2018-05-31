package com.oraclechain.pocketeos.bean;

import java.math.BigDecimal;

/**
 * Created by pocketEos on 2018/2/5.
 * 获取资产汇率
 */

public class CoinRateBean {

    /**
     * code : 0
     * message : ok
     * data : {"id":"eos","price_usd":"9.45208","price_cny":"59.586384924","percent_change_24h":"12.31"}
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
         * id : eos
         * price_usd : 9.45208
         * price_cny : 59.586384924
         * percent_change_24h : 12.31
         */

        private String id;
        private BigDecimal price_usd;
        private BigDecimal price_cny;
        private String percent_change_24h;

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public BigDecimal getPrice_usd() {
            return price_usd;
        }

        public void setPrice_usd(BigDecimal price_usd) {
            this.price_usd = price_usd;
        }

        public BigDecimal getPrice_cny() {
            return price_cny;
        }

        public void setPrice_cny(BigDecimal price_cny) {
            this.price_cny = price_cny;
        }

        public String getPercent_change_24h() {
            return percent_change_24h == null ? "" : percent_change_24h;
        }

        public void setPercent_change_24h(String percent_change_24h) {
            this.percent_change_24h = percent_change_24h;
        }
    }
}
