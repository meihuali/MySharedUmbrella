package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：ShangJiaRenZhenBean
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 14:31
 * 描述：商务中心认证界面所有数据 包括认证
 */
public class ShangJiaRenZhenBean {

    /**
     * status : 1
     * data : {"id":"11","nickname":"啊东","phone":"17620193389","pwd":"14e1b600b1fd579f47433b88e8d85291","wechat":"","qq":"","c_time":"1504927594","wechat_time":"0","qq_time":"0","money":"0","hread_img":"","ad_img":"","phone_time":"0","business_img":"","address":"啊啊啊吧","is_Authentication":"2"}
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
         * id : 11
         * nickname : 啊东
         * phone : 17620193389
         * pwd : 14e1b600b1fd579f47433b88e8d85291
         * wechat :
         * qq :
         * c_time : 1504927594
         * wechat_time : 0
         * qq_time : 0
         * money : 0
         * hread_img :
         * ad_img :
         * phone_time : 0
         * business_img :
         * address : 啊啊啊吧
         * is_Authentication : 2
         */

        private String id;
        private String nickname;
        private String phone;
        private String pwd;
        private String wechat;
        private String qq;
        private String c_time;
        private String wechat_time;
        private String qq_time;
        private String money;
        private String hread_img;
        private String ad_img;
        private String phone_time;
        private String business_img;
        private String address;
        private String is_Authentication;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
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

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public String getWechat_time() {
            return wechat_time;
        }

        public void setWechat_time(String wechat_time) {
            this.wechat_time = wechat_time;
        }

        public String getQq_time() {
            return qq_time;
        }

        public void setQq_time(String qq_time) {
            this.qq_time = qq_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getHread_img() {
            return hread_img;
        }

        public void setHread_img(String hread_img) {
            this.hread_img = hread_img;
        }

        public String getAd_img() {
            return ad_img;
        }

        public void setAd_img(String ad_img) {
            this.ad_img = ad_img;
        }

        public String getPhone_time() {
            return phone_time;
        }

        public void setPhone_time(String phone_time) {
            this.phone_time = phone_time;
        }

        public String getBusiness_img() {
            return business_img;
        }

        public void setBusiness_img(String business_img) {
            this.business_img = business_img;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIs_Authentication() {
            return is_Authentication;
        }

        public void setIs_Authentication(String is_Authentication) {
            this.is_Authentication = is_Authentication;
        }
    }
}
