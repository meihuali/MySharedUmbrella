package com.example.administrator.mysharedumbrella01.entivity;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：AdminAuthenticationBean
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 17:02
 * 描述：TODO
 */
public class AdminAuthenticationBean {

    /**
     * status : 1
     * data : [{"id":24,"merchantname":"都换个地方","phone":"15625177596","m_money":0,"address":"符合规范广告哈哈哈","adimg":"","proimg":"","business_img":"business/20171011/2530910c9e90794fcf937fd259dd8893.png","c_time":1507689237,"is_Authentication":1,"stand":0,"umbrella_num":0,"uncollected_stand":0,"uncollected_umbrella":0,"ad_explain":""}]
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
         * id : 24
         * merchantname : 都换个地方
         * phone : 15625177596
         * m_money : 0
         * address : 符合规范广告哈哈哈
         * adimg :
         * proimg :
         * business_img : business/20171011/2530910c9e90794fcf937fd259dd8893.png
         * c_time : 1507689237
         * is_Authentication : 1
         * stand : 0
         * umbrella_num : 0
         * uncollected_stand : 0
         * uncollected_umbrella : 0
         * ad_explain :
         */

        private int id;
        private String merchantname;
        private String phone;
        private int m_money;
        private String address;
        private String adimg;
        private String proimg;
        private String business_img;
        private int c_time;
        private int is_Authentication;
        private int stand;
        private int umbrella_num;
        private int uncollected_stand;
        private int uncollected_umbrella;
        private String ad_explain;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getM_money() {
            return m_money;
        }

        public void setM_money(int m_money) {
            this.m_money = m_money;
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

        public int getC_time() {
            return c_time;
        }

        public void setC_time(int c_time) {
            this.c_time = c_time;
        }

        public int getIs_Authentication() {
            return is_Authentication;
        }

        public void setIs_Authentication(int is_Authentication) {
            this.is_Authentication = is_Authentication;
        }

        public int getStand() {
            return stand;
        }

        public void setStand(int stand) {
            this.stand = stand;
        }

        public int getUmbrella_num() {
            return umbrella_num;
        }

        public void setUmbrella_num(int umbrella_num) {
            this.umbrella_num = umbrella_num;
        }

        public int getUncollected_stand() {
            return uncollected_stand;
        }

        public void setUncollected_stand(int uncollected_stand) {
            this.uncollected_stand = uncollected_stand;
        }

        public int getUncollected_umbrella() {
            return uncollected_umbrella;
        }

        public void setUncollected_umbrella(int uncollected_umbrella) {
            this.uncollected_umbrella = uncollected_umbrella;
        }

        public String getAd_explain() {
            return ad_explain;
        }

        public void setAd_explain(String ad_explain) {
            this.ad_explain = ad_explain;
        }
    }
}
