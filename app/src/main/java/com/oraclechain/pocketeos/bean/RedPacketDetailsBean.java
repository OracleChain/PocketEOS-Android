package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/4/20.
 */

public class RedPacketDetailsBean {

    /**
     * msg : SUCCEED
     * code : 0
     * data : {"statistics":{"residueCount":0,"residueAmount":"0.0000","packetCount":1,"amount":"10.0000","isSend":true},"redPacketOrderRedisDtos":[{"type":"EOS","amount":"10.0000","account":"lucan222","createTime":"2018-04-20 15:30:14"}]}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * statistics : {"residueCount":0,"residueAmount":"0.0000","packetCount":1,"amount":"10.0000","isSend":true}
         * redPacketOrderRedisDtos : [{"type":"EOS","amount":"10.0000","account":"lucan222","createTime":"2018-04-20 15:30:14"}]
         */

        private StatisticsBean statistics;
        private List<RedPacketOrderRedisDtosBean> redPacketOrderRedisDtos;

        public StatisticsBean getStatistics() {
            return statistics;
        }

        public void setStatistics(StatisticsBean statistics) {
            this.statistics = statistics;
        }

        public List<RedPacketOrderRedisDtosBean> getRedPacketOrderRedisDtos() {
            return redPacketOrderRedisDtos;
        }

        public void setRedPacketOrderRedisDtos(List<RedPacketOrderRedisDtosBean> redPacketOrderRedisDtos) {
            this.redPacketOrderRedisDtos = redPacketOrderRedisDtos;
        }

        public static class StatisticsBean {
            /**
             * residueCount : 0
             * residueAmount : 0.0000
             * packetCount : 1
             * amount : 10.0000
             * isSend : true
             */

            private int residueCount;
            private String residueAmount;
            private int packetCount;
            private String amount;
            private boolean isSend;

            public int getResidueCount() {
                return residueCount;
            }

            public void setResidueCount(int residueCount) {
                this.residueCount = residueCount;
            }

            public String getResidueAmount() {
                return residueAmount;
            }

            public void setResidueAmount(String residueAmount) {
                this.residueAmount = residueAmount;
            }

            public int getPacketCount() {
                return packetCount;
            }

            public void setPacketCount(int packetCount) {
                this.packetCount = packetCount;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public boolean isIsSend() {
                return isSend;
            }

            public void setIsSend(boolean isSend) {
                this.isSend = isSend;
            }
        }

        public static class RedPacketOrderRedisDtosBean {
            /**
             * type : EOS
             * amount : 10.0000
             * account : lucan222
             * createTime : 2018-04-20 15:30:14
             */

            private String type;
            private String amount;
            private String account;
            private String createTime;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
