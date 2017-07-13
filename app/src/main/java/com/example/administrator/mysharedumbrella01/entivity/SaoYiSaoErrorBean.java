package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/7/8 0008.
 * 扫一扫 错误的 返回json
 */

public class SaoYiSaoErrorBean {
    /**
     * status : 4
     * data : {"type":1,"money":20}
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
         * type : 1
         * money : 20
         */

        private int type;
        private int money;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
