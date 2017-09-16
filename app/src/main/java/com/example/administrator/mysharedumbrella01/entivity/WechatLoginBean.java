package com.example.administrator.mysharedumbrella01.entivity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/23 0023.
 * //三方 微信登录 实体类
 */

public class WechatLoginBean implements Serializable{

    /**
     * status : 1
     * data : {"r_id":"461","r_username":"啊啊啊","r_img":"20170912/9b839687c7b4b0b25f1861f73f77743f.jpg","r_appid":"17620193389","r_password":"","r_deposit":"0","r_money":"0.00","r_addtime":"0","mobilephone":"17620193389","unionid":"","is_Authentication":"2","wechat":"","qq":""}
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
         * r_id : 461
         * r_username : 啊啊啊
         * r_img : 20170912/9b839687c7b4b0b25f1861f73f77743f.jpg
         * r_appid : 17620193389
         * r_password :
         * r_deposit : 0
         * r_money : 0.00
         * r_addtime : 0
         * mobilephone : 17620193389
         * unionid :
         * is_Authentication : 2
         * wechat :
         * qq :
         */

        private String r_id;
        private String r_username;
        private String r_img;
        private String r_appid;
        private String r_password;
        private String r_deposit;
        private String r_money;
        private String r_addtime;
        private String mobilephone;
        private String unionid;
        private String is_Authentication;
        private String wechat;
        private String qq;

        public String getR_id() {
            return r_id;
        }

        public void setR_id(String r_id) {
            this.r_id = r_id;
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

        public String getR_appid() {
            return r_appid;
        }

        public void setR_appid(String r_appid) {
            this.r_appid = r_appid;
        }

        public String getR_password() {
            return r_password;
        }

        public void setR_password(String r_password) {
            this.r_password = r_password;
        }

        public String getR_deposit() {
            return r_deposit;
        }

        public void setR_deposit(String r_deposit) {
            this.r_deposit = r_deposit;
        }

        public String getR_money() {
            return r_money;
        }

        public void setR_money(String r_money) {
            this.r_money = r_money;
        }

        public String getR_addtime() {
            return r_addtime;
        }

        public void setR_addtime(String r_addtime) {
            this.r_addtime = r_addtime;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getIs_Authentication() {
            return is_Authentication;
        }

        public void setIs_Authentication(String is_Authentication) {
            this.is_Authentication = is_Authentication;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }
    }
}
