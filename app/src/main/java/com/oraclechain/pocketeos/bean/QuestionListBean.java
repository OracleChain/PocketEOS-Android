package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pocketEos on 2018/2/8.
 */

public class QuestionListBean implements Parcelable {
    private String title;
    private String content;
    private String time;
    private String name;
    private String id;
    private int releasedLable;

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReleasedLable() {
        return releasedLable;
    }

    public void setReleasedLable(int releasedLable) {
        this.releasedLable = releasedLable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.time);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeInt(this.releasedLable);
    }

    public QuestionListBean() {
    }

    protected QuestionListBean(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.time = in.readString();
        this.name = in.readString();
        this.id = in.readString();
        this.releasedLable = in.readInt();
    }

    public static final Parcelable.Creator<QuestionListBean> CREATOR = new Parcelable.Creator<QuestionListBean>() {
        @Override
        public QuestionListBean createFromParcel(Parcel source) {
            return new QuestionListBean(source);
        }

        @Override
        public QuestionListBean[] newArray(int size) {
            return new QuestionListBean[size];
        }
    };
}
