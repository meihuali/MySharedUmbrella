package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class LoginBean {

    /**
     * status : 1
     * data : {"id":":1","phone":"13144743223"}
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
         * id : :1
         * phone : 13144743223
         */

        private String r_id;
        private String phone;
        private int isroot;
        private String photo;
        private String username;
        private String r_img;
        private String r_username;
        private String is_Authentication;
        private String QQ;
        private String wechat;
        private int is_manager;

        public int getIs_manager() {
            return is_manager;
        }

        public void setIs_manager(int is_manager) {
            this.is_manager = is_manager;
        }

        public String getIs_Authentication() {
            return is_Authentication;
        }

        public void setIs_Authentication(String is_Authentication) {
            this.is_Authentication = is_Authentication;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getR_username() {
            return r_username;
        }

        public void setR_username(String r_username) {
            this.r_username = r_username;
        }

        public String getR_img() {
            return r_img;
        }

        public void setR_img(String r_img) {
            this.r_img = r_img;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getIsroot() {
            return isroot;
        }

        public void setIsroot(int isroot) {
            this.isroot = isroot;
        }

        public String getR_id() {
            return r_id;
        }

        public void setR_id(String r_id) {
            this.r_id = r_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
