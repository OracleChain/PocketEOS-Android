package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/4/8.
 */

public class SparkLinesBean {

    /**
     * code : 0
     * message : ok
     * data : {"sparkline_eos_png":"https://s2.coinmarketcap.com/generated/sparklines/web/7d/usd/1765.png","sparkline_oct_png":"https://s2.coinmarketcap.com/generated/sparklines/web/7d/usd/1838.png"}
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
        return message == null ? "" : message;
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
         * sparkline_eos_png : https://s2.coinmarketcap.com/generated/sparklines/web/7d/usd/1765.png
         * sparkline_oct_png : https://s2.coinmarketcap.com/generated/sparklines/web/7d/usd/1838.png
         */

        private String sparkline_eos_png;
        private String sparkline_oct_png;

        public String getSparkline_eos_png() {
            return sparkline_eos_png == null ? "" : sparkline_eos_png;
        }

        public void setSparkline_eos_png(String sparkline_eos_png) {
            this.sparkline_eos_png = sparkline_eos_png;
        }

        public String getSparkline_oct_png() {
            return sparkline_oct_png == null ? "" : sparkline_oct_png;
        }

        public void setSparkline_oct_png(String sparkline_oct_png) {
            this.sparkline_oct_png = sparkline_oct_png;
        }
    }
}
