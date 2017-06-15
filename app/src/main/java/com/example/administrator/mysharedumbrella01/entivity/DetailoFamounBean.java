package com.example.administrator.mysharedumbrella01.entivity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 * //金额 明细 实体类
 */

public class DetailoFamounBean {

    /**
     * status : 1
     * data : [{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"1","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"2","status":"支付失败","pay_time":"2017-06-12 14:18","way":"支付宝支付"},{"money":"2","status":"支付成功","pay_time":"2017-06-12 14:18","way":"微信支付"},{"money":"2","status":"支付失败","pay_time":"2017-06-12 14:18","way":"支付宝支付"},{"money":"1.2","status":"支付成功","pay_time":"2017-06-12 14:14","way":"微信支付"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * money : 1
         * status : 支付成功
         * pay_time : 2017-06-12 14:18
         * way : 微信支付
         */

        private String money;
        private String status;
        private String pay_time;
        private String way;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getWay() {
            return way;
        }

        public void setWay(String way) {
            this.way = way;
        }
    }
}
