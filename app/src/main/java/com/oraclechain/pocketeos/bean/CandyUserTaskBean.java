package com.oraclechain.pocketeos.bean;

import java.util.List;

/**
 * Created by pocketEos on 2018/5/26.
 */

public class CandyUserTaskBean {

    /**
     * code : 0
     * data : [{"candyTask":{"id":"65fd4b7e66614aceb0e38fd700248e7b","title":"注册就送10个积分","description":"注册就送10个积分","avatar":"0","scoreNum":10,"taskUrl":"0","createTime":"2018-05-24 18:37:17","updateTime":"2018-05-24 18:37:17"},"completed":false}]
     * message : SUCCEED
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
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * candyTask : {"id":"65fd4b7e66614aceb0e38fd700248e7b","title":"注册就送10个积分","description":"注册就送10个积分","avatar":"0","scoreNum":10,"taskUrl":"0","createTime":"2018-05-24 18:37:17","updateTime":"2018-05-24 18:37:17"}
         * completed : false
         */

        private CandyTaskBean candyTask;
        private boolean completed;

        public CandyTaskBean getCandyTask() {
            return candyTask;
        }

        public void setCandyTask(CandyTaskBean candyTask) {
            this.candyTask = candyTask;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public static class CandyTaskBean {
            /**
             * id : 65fd4b7e66614aceb0e38fd700248e7b
             * title : 注册就送10个积分
             * description : 注册就送10个积分
             * avatar : 0
             * scoreNum : 10
             * taskUrl : 0
             * createTime : 2018-05-24 18:37:17
             * updateTime : 2018-05-24 18:37:17
             */

            private String id;
            private String title;
            private String description;
            private String avatar;
            private int scoreNum;
            private String taskUrl;
            private String createTime;
            private String updateTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getScoreNum() {
                return scoreNum;
            }

            public void setScoreNum(int scoreNum) {
                this.scoreNum = scoreNum;
            }

            public String getTaskUrl() {
                return taskUrl;
            }

            public void setTaskUrl(String taskUrl) {
                this.taskUrl = taskUrl;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
