package com.example.administrator.mysharedumbrella01.entivity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 *  历史记录的 实体类
 */

public class HistoryBean {


    /**
     * status : 1
     * data : [{"id":"13","starttime":"1496804792","endtime":"1496805116","duration":5.4,"expense":0.54},{"id":"13","starttime":"1496803410","endtime":"1496803940","duration":8.83,"expense":0.88},{"id":"13","starttime":"1496802767","endtime":"1496802842","duration":1.25,"expense":0.13}]
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
         * id : 13
         * starttime : 1496804792
         * endtime : 1496805116
         * duration : 5.4
         * expense : 0.54
         */

        private String id;
        private String starttime;
        private String endtime;
        private double duration;
        private double expense;
        private String la_umbrella_id;

        public String getLa_umbrella_id() {
            return la_umbrella_id;
        }

        public void setLa_umbrella_id(String la_umbrella_id) {
            this.la_umbrella_id = la_umbrella_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public double getExpense() {
            return expense;
        }

        public void setExpense(double expense) {
            this.expense = expense;
        }
    }
}
