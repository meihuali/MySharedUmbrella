package com.example.administrator.mysharedumbrella01.entivity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/23 0023.
 * //三方 微信登录 实体类
 */

public class WechatLoginBean implements Serializable{
    /**
     * status : 1
     * data : {"username":"弱水三千","phone":"1A5537649967DEEB388387440782C093","photo":"http://q.qlogo.cn/qqapp/1106175515/1A5537649967DEEB388387440782C093/100","money":"0","r_id":"96","mobilephone":"0","isroot":2}
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
         * username : 弱水三千
         * phone : 1A5537649967DEEB388387440782C093
         * photo : http://q.qlogo.cn/qqapp/1106175515/1A5537649967DEEB388387440782C093/100
         * money : 0
         * r_id : 96
         * mobilephone : 0
         * isroot : 2
         */

        private String username;
        private String phone;
        private String photo;
        private String money;
        private String r_id;
        private String mobilephone;
        private int isroot;

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

        public String getR_id() {
            return r_id;
        }

        public void setR_id(String r_id) {
            this.r_id = r_id;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public int getIsroot() {
            return isroot;
        }

        public void setIsroot(int isroot) {
            this.isroot = isroot;
        }
    }


//    /**
//     * status : 1
//     * data : {"username":"弱水三千","phone":"o5MzfwF9OUoKlT_UI-KPnl5EocfU","photo":"","money":"0"}
//     */
//
//    private int status;
//    private DataBean data;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean implements Serializable{
//        /**
//         * username : 弱水三千
//         * phone : o5MzfwF9OUoKlT_UI-KPnl5EocfU
//         * photo :
//         * money : 0
//         */
//
//        private String username;
//        private String phone;
//        private String photo;
//        private String money;
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//
//        public String getPhoto() {
//            return photo;
//        }
//
//        public void setPhoto(String photo) {
//            this.photo = photo;
//        }
//
//        public String getMoney() {
//            return money;
//        }
//
//        public void setMoney(String money) {
//            this.money = money;
//        }
//    }
}
