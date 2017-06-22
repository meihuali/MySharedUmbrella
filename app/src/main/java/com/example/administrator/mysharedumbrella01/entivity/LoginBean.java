package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class LoginBean {

    /**
     * status : 1
     * data : {"id":":1","phone":"13144743223"}
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
         * id : :1
         * phone : 13144743223
         */

        private String id;
        private String phone;
        private int isroot;
        private String photo;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getIsroot() {
            return isroot;
        }

        public void setIsroot(int isroot) {
            this.isroot = isroot;
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
    }
}
