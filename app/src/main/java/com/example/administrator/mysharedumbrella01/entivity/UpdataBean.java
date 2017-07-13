package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/6/26 0026.
 * 更新APP 版本用的实体类
 */

public class UpdataBean {


    /**
     * status : 1
     * data : {"id":"1","versionName":"1.0","versioncode":"1","content":"修复了什么事？、","url":"http://kxy.sunyie.com/api/sengji.php","qiangzhi":"0","c_time":"0","u_time":"0"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * versionName : 1.0
         * versioncode : 1
         * content : 修复了什么事？、
         * url : http://kxy.sunyie.com/api/sengji.php
         * qiangzhi : 0
         * c_time : 0
         * u_time : 0
         */

        private String id;
        private String versionName;
        private String versioncode;
        private String content;
        private String url;
        private String qiangzhi;
        private String c_time;
        private String u_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(String versioncode) {
            this.versioncode = versioncode;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getQiangzhi() {
            return qiangzhi;
        }

        public void setQiangzhi(String qiangzhi) {
            this.qiangzhi = qiangzhi;
        }

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public String getU_time() {
            return u_time;
        }

        public void setU_time(String u_time) {
            this.u_time = u_time;
        }
    }
}
