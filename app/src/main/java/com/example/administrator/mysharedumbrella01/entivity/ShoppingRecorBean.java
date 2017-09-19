package com.example.administrator.mysharedumbrella01.entivity;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：ShoppingRecorBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/19 0019 9:52
 * 描述：商家充值记录
 */
public class ShoppingRecorBean {

    /**
     * status : 1
     * data : [{"id":"1","phone":"18825149284","order":"150571743149633","stand":"1","umbrella":"20","c_time":"1505717328","get_time":"0","determine":"1"},{"id":"2","phone":"18825149284","order":"150571743149634","stand":"0","umbrella":"20","c_time":"1505717431","get_time":"0","determine":"1"},{"id":"3","phone":"18825149284","order":"150572003652602","stand":"0","umbrella":"20","c_time":"1505720036","get_time":"0","determine":"0"},{"id":"4","phone":"18825149284","order":"150572015274870","stand":"1","umbrella":"20","c_time":"1505720152","get_time":"1505721064","determine":"1"}]
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
         * id : 1
         * phone : 18825149284
         * order : 150571743149633
         * stand : 1
         * umbrella : 20
         * c_time : 1505717328
         * get_time : 0
         * determine : 1
         */

        private String id;
        private String phone;
        private String order;
        private String stand;
        private String umbrella;
        private String c_time;
        private String get_time;
        private String determine;
        private String error_reason;

        public String getError_reason() {
            return error_reason;
        }

        public void setError_reason(String error_reason) {
            this.error_reason = error_reason;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getStand() {
            return stand;
        }

        public void setStand(String stand) {
            this.stand = stand;
        }

        public String getUmbrella() {
            return umbrella;
        }

        public void setUmbrella(String umbrella) {
            this.umbrella = umbrella;
        }

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public String getGet_time() {
            return get_time;
        }

        public void setGet_time(String get_time) {
            this.get_time = get_time;
        }

        public String getDetermine() {
            return determine;
        }

        public void setDetermine(String determine) {
            this.determine = determine;
        }
    }
}
