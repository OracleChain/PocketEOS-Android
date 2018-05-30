package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pocketEos on 2018/4/12.
 */

public class SendRedPacketBean {

    /**
     * msg : SUCCEED
     * code : 0
     * data : {"id":"9c865d2f31424ec0b44674f2da8b6f2b","verifyString":"f6bed7bd609012bc03911d5879bf9807","createTime":"2018-04-16 10:03:19"}
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

    public static class DataBean implements Parcelable {


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
        private String id;
        private String verifyString;
        private String endTime;

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.verifyString = in.readString();
            this.endTime = in.readString();
        }

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVerifyString() {
            return verifyString == null ? "" : verifyString;
        }

        public void setVerifyString(String verifyString) {
            this.verifyString = verifyString;
        }

        public String getEndTime() {
            return endTime == null ? "" : endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.verifyString);
            dest.writeString(this.endTime);
        }
    }
}
