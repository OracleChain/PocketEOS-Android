package com.oraclechain.pocketeos.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/1/11.
 */

public class CoinBean {

    /**
     * code : 0
     * message : ok
     * data : [{"id":0,"assetName":"全部资产"},{"id":4,"assetName":"BTC"},{"id":2,"assetName":"EOS"},{"id":3,"assetName":"ETH"},{"id":1,"assetName":"OCT"}]
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
         * id : 0
         * assetName : 全部资产
         */

        private String id;
        private String assetName;

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAssetName() {
            return assetName == null ? "" : assetName;
        }

        public void setAssetName(String assetName) {
            this.assetName = assetName;
        }
    }
}
