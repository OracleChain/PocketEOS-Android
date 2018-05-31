package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/1/27.
 */

public class ChainInfoBean {


    /**
     * code : 0
     * message : ok
     * data : {"server_version":"2cc40a4e","head_block_num":"307","last_irreversible_block_num":"289","head_block_id":"00000133b3261702a51f6d10a254dd682ef0def232442ee3b3d80be967dcd4ae","head_block_time":"2018-01-22T02:36:58","head_block_producer":"inith","recent_slots":"1111111111111111111111111111111111111111111111111111111111111111","participation_rate":"1.00000000000000000"}
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
         * server_version : 2cc40a4e
         * head_block_num : 307
         * last_irreversible_block_num : 289
         * head_block_id : 00000133b3261702a51f6d10a254dd682ef0def232442ee3b3d80be967dcd4ae
         * head_block_time : 2018-01-22T02:36:58
         * head_block_producer : inith
         * recent_slots : 1111111111111111111111111111111111111111111111111111111111111111
         * participation_rate : 1.00000000000000000
         */

        private String server_version;
        private Integer head_block_num;
        private String last_irreversible_block_num;
        private String head_block_id;
        private String head_block_time;
        private String head_block_producer;
        private String recent_slots;
        private String participation_rate;

        public String getServer_version() {
            return server_version == null ? "" : server_version;
        }

        public void setServer_version(String server_version) {
            this.server_version = server_version;
        }

        public Integer getHead_block_num() {
            return head_block_num;
        }

        public void setHead_block_num(Integer head_block_num) {
            this.head_block_num = head_block_num;
        }

        public String getLast_irreversible_block_num() {
            return last_irreversible_block_num == null ? "" : last_irreversible_block_num;
        }

        public void setLast_irreversible_block_num(String last_irreversible_block_num) {
            this.last_irreversible_block_num = last_irreversible_block_num;
        }

        public String getHead_block_id() {
            return head_block_id == null ? "" : head_block_id;
        }

        public void setHead_block_id(String head_block_id) {
            this.head_block_id = head_block_id;
        }

        public String getHead_block_time() {
            return head_block_time == null ? "" : head_block_time;
        }

        public void setHead_block_time(String head_block_time) {
            this.head_block_time = head_block_time;
        }

        public String getHead_block_producer() {
            return head_block_producer == null ? "" : head_block_producer;
        }

        public void setHead_block_producer(String head_block_producer) {
            this.head_block_producer = head_block_producer;
        }

        public String getRecent_slots() {
            return recent_slots == null ? "" : recent_slots;
        }

        public void setRecent_slots(String recent_slots) {
            this.recent_slots = recent_slots;
        }

        public String getParticipation_rate() {
            return participation_rate == null ? "" : participation_rate;
        }

        public void setParticipation_rate(String participation_rate) {
            this.participation_rate = participation_rate;
        }
    }
}
