package com.example.administrator.mysharedumbrella01.entivity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class KeFuFanKuiBean {

    /**
     * status : 1
     * data : [{"id":"2","cate_name":"雨伞破坏","cate_id":"1","c_time":"1498456734"},{"id":"3","cate_name":"编码破坏","cate_id":"1","c_time":"1498461439"},{"id":"4","cate_name":"伞座破坏","cate_id":"1","c_time":"1498456734"},{"id":"5","cate_name":"锁被破坏","cate_id":"1","c_time":"1498461630"}]
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
         * id : 2
         * cate_name : 雨伞破坏
         * cate_id : 1
         * c_time : 1498456734
         */

        private String id;
        private String cate_name;
        private String cate_id;
        private String c_time;
        boolean isSelect =false ;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }
    }
}
