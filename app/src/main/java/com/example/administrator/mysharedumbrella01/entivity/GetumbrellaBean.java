package com.example.administrator.mysharedumbrella01.entivity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class GetumbrellaBean {

    /**
     * status : 1
     * data : [{"umbrellanumber":1,"vacancynumber":"0","longitude":"113.3123","latitude":"23.2342"}]
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
         * umbrellanumber : 1
         * vacancynumber : 0
         * longitude : 113.3123
         * latitude : 23.2342
         */

        private int umbrellanumber;
        private String vacancynumber;
        private String longitude;
        private String latitude;

        public int getUmbrellanumber() {
            return umbrellanumber;
        }

        public void setUmbrellanumber(int umbrellanumber) {
            this.umbrellanumber = umbrellanumber;
        }

        public String getVacancynumber() {
            return vacancynumber;
        }

        public void setVacancynumber(String vacancynumber) {
            this.vacancynumber = vacancynumber;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
