package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by pocketEos on 2018/4/19.
 */

public class RedPacketHistoryBean {

    /**
     * msg : SUCCEED
     * code : 0
     * data : [{"residueCount":0,"packetCount":10,"amount":"100.0000","type":"EOS","createTime":"2018-04-16","isSend":true},{"residueCount":0,"packetCount":10,"amount":"100.0000","type":"EOS","createTime":"2018-04-16","isSend":true},{"residueCount":0,"packetCount":10,"amount":"100.0000","type":"EOS","createTime":"2018-04-16","isSend":true},{"residueCount":0,"packetCount":10,"amount":"100.0000","type":"EOS","createTime":"2018-04-16","isSend":true},{"residueCount":0,"packetCount":10,"amount":"100.0000","type":"EOS","createTime":"2018-04-16","isSend":true},{"residueCount":0,"packetCount":10,"amount":"100.0000","type":"EOS","createTime":"2018-04-16","isSend":true}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {

        /**
         * residueCount : 0
         * packetCount : 10
         * amount : 100.0000
         * type : EOS
         * createTime : 2018-04-16
         * isSend : true
         */

        private int residueCount;
        private int packetCount;
        private String amount;
        private String type;
        private String createTime;
        private String id;
        private String residueAmount;
        private String verifyString;
        private boolean isSend;

        public int getResidueCount() {
            return residueCount;
        }

        public void setResidueCount(int residueCount) {
            this.residueCount = residueCount;
        }

        public int getPacketCount() {
            return packetCount;
        }

        public void setPacketCount(int packetCount) {
            this.packetCount = packetCount;
        }

        public String getAmount() {
            return amount == null ? "" : amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getType() {
            return type == null ? "" : type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResidueAmount() {
            return residueAmount == null ? "" : residueAmount;
        }

        public void setResidueAmount(String residueAmount) {
            this.residueAmount = residueAmount;
        }

        public String getVerifyString() {
            return verifyString == null ? "" : verifyString;
        }

        public void setVerifyString(String verifyString) {
            this.verifyString = verifyString;
        }

        public boolean isSend() {
            return isSend;
        }

        public void setSend(boolean send) {
            isSend = send;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.residueCount);
            dest.writeInt(this.packetCount);
            dest.writeString(this.amount);
            dest.writeString(this.type);
            dest.writeString(this.createTime);
            dest.writeString(this.id);
            dest.writeString(this.residueAmount);
            dest.writeString(this.verifyString);
            dest.writeByte(this.isSend ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.residueCount = in.readInt();
            this.packetCount = in.readInt();
            this.amount = in.readString();
            this.type = in.readString();
            this.createTime = in.readString();
            this.id = in.readString();
            this.residueAmount = in.readString();
            this.verifyString = in.readString();
            this.isSend = in.readByte() != 0;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
