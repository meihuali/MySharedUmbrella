package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：ShoppingQueryAutBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 14:20
 * 描述： 查询商家认证的实体类
 */
public class ShoppingQueryAutBean  {

    /**
     * status : 1
     * data : {"id":"6","merchantname":"啊啊啊吧","phone":"17620193389","money":"0","address":"啊啊啊","adimg":"","proimg":"","business_img":"","c_time":"1505272627","is_Authentication":"2"}
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
         * id : 6
         * merchantname : 啊啊啊吧
         * phone : 17620193389
         * money : 0
         * address : 啊啊啊
         * adimg :
         * proimg :
         * business_img :
         * c_time : 1505272627
         * is_Authentication : 2
         */

        private String id;
        private String merchantname;
        private String phone;
        private String money;
        private String address;
        private String adimg;
        private String proimg;
        private String business_img;
        private String c_time;
        private String is_Authentication;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMerchantname() {
            return merchantname;
        }

        public void setMerchantname(String merchantname) {
            this.merchantname = merchantname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAdimg() {
            return adimg;
        }

        public void setAdimg(String adimg) {
            this.adimg = adimg;
        }

        public String getProimg() {
            return proimg;
        }

        public void setProimg(String proimg) {
            this.proimg = proimg;
        }

        public String getBusiness_img() {
            return business_img;
        }

        public void setBusiness_img(String business_img) {
            this.business_img = business_img;
        }

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public String getIs_Authentication() {
            return is_Authentication;
        }

        public void setIs_Authentication(String is_Authentication) {
            this.is_Authentication = is_Authentication;
        }
    }
}
