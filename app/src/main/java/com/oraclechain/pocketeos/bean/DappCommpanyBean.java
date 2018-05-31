package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/1/3.
 */

public class DappCommpanyBean {
    /**
     * code : 0
     * message : ok
     * data : [{"id":1,"summary":"","enterpriseName":"www","publicImage":"%3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg","enterpriseIcon":"%3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg","weight":1,"introReason":"啦啦啦"},{"id":1,"summary":"","enterpriseName":"www","publicImage":"%3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg","enterpriseIcon":"%3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg","weight":1,"introReason":"啦啦啦"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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
         * summary :
         * enterpriseName : www
         * publicImage : %3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg
         * enterpriseIcon : %3D300/sign=4329e21727738bd4db21b4319189876c/f7246b600c3387447265b2b6580fd9f9d62aa05c.jpg
         * weight : 1
         * introReason : 啦啦啦
         */

        private int id;
        private String summary;
        private String enterpriseName;
        private String publicImage;
        private String enterpriseIcon;
        private int weight;
        private String introReason;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSummary() {
            return summary == null ? "" : summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getEnterpriseName() {
            return enterpriseName == null ? "" : enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getPublicImage() {
            return publicImage == null ? "" : publicImage;
        }

        public void setPublicImage(String publicImage) {
            this.publicImage = publicImage;
        }

        public String getEnterpriseIcon() {
            return enterpriseIcon == null ? "" : enterpriseIcon;
        }

        public void setEnterpriseIcon(String enterpriseIcon) {
            this.enterpriseIcon = enterpriseIcon;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getIntroReason() {
            return introReason == null ? "" : introReason;
        }

        public void setIntroReason(String introReason) {
            this.introReason = introReason;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.summary);
            dest.writeString(this.enterpriseName);
            dest.writeString(this.publicImage);
            dest.writeString(this.enterpriseIcon);
            dest.writeInt(this.weight);
            dest.writeString(this.introReason);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.summary = in.readString();
            this.enterpriseName = in.readString();
            this.publicImage = in.readString();
            this.enterpriseIcon = in.readString();
            this.weight = in.readInt();
            this.introReason = in.readString();
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
