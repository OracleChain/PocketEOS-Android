package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/2/9.
 */

public class UpdateAppBean {

    /**
     * code : 0
     * message : ok
     * data : {"uploadUrl":"http://www.xiaodaiw.cn/downh5/wdph101_v1.0.1.apk","versionDetail":"版本升级","versionCode":"102","versionName":"1.0.2"}
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
         * uploadUrl : http://www.xiaodaiw.cn/downh5/wdph101_v1.0.1.apk
         * versionDetail : 版本升级
         * versionCode : 102
         * versionName : 1.0.2
         */

        private String uploadUrl;
        private String versionDetail;
        private String versionCode;
        private String versionName;

        public String getUploadUrl() {
            return uploadUrl == null ? "" : uploadUrl;
        }

        public void setUploadUrl(String uploadUrl) {
            this.uploadUrl = uploadUrl;
        }

        public String getVersionDetail() {
            return versionDetail == null ? "" : versionDetail;
        }

        public void setVersionDetail(String versionDetail) {
            this.versionDetail = versionDetail;
        }

        public String getVersionCode() {
            return versionCode == null ? "" : versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName == null ? "" : versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}
