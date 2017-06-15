package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class ManeyBean {

    /**
     * status : 1
     * data : {"deposit":"20","money":"9"}
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
         * deposit : 20
         * money : 9
         */

        private String deposit;
        private String money;

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
