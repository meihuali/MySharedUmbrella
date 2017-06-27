package com.example.administrator.mysharedumbrella01.entivity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/23 0023.
 * //三方 微信登录 实体类
 */

public class WechatLoginBean implements Serializable{

    /**
     * status : 1
     * data : {"username":"弱水三千","phone":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","photo":"","money":"0"}
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

    public static class DataBean implements Serializable{
        /**
         * username : 弱水三千
         * phone : o5MzfwF9OUoKlT_UI-KPnl5EocfU
         * photo :
         * money : 0
         */

        private String username;
        private String phone;
        private String photo;
        private String money;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
