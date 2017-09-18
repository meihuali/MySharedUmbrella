package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：SanZuoSanBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 18:57
 * 描述：TODO
 */
public class SanZuoSanBean {


    /**
     * status : 1
     * data : {"seat":30,"umbrella":"20","address":{"id":"5","name":"啊啊啊","phone":"擦擦擦刺激了","zipcode":"baccalaure","region":"浙江省绍兴市上虞市","address":"不知道","merchant_id":"7","c_time":"0","u_time":"0","is_inuser":"1"}}
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
         * seat : 30
         * umbrella : 20
         * address : {"id":"5","name":"啊啊啊","phone":"擦擦擦刺激了","zipcode":"baccalaure","region":"浙江省绍兴市上虞市","address":"不知道","merchant_id":"7","c_time":"0","u_time":"0","is_inuser":"1"}
         */

        private int seat;
        private String umbrella;
        private AddressBean address;

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public String getUmbrella() {
            return umbrella;
        }

        public void setUmbrella(String umbrella) {
            this.umbrella = umbrella;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public static class AddressBean {
            /**
             * id : 5
             * name : 啊啊啊
             * phone : 擦擦擦刺激了
             * zipcode : baccalaure
             * region : 浙江省绍兴市上虞市
             * address : 不知道
             * merchant_id : 7
             * c_time : 0
             * u_time : 0
             * is_inuser : 1
             */

            private String id;
            private String name;
            private String phone;
            private String zipcode;
            private String region;
            private String address;
            private String merchant_id;
            private String c_time;
            private String u_time;
            private String is_inuser;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(String merchant_id) {
                this.merchant_id = merchant_id;
            }

            public String getC_time() {
                return c_time;
            }

            public void setC_time(String c_time) {
                this.c_time = c_time;
            }

            public String getU_time() {
                return u_time;
            }

            public void setU_time(String u_time) {
                this.u_time = u_time;
            }

            public String getIs_inuser() {
                return is_inuser;
            }

            public void setIs_inuser(String is_inuser) {
                this.is_inuser = is_inuser;
            }
        }
    }
}
