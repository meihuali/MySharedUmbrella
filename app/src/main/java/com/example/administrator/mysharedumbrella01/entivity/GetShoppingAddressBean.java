package com.example.administrator.mysharedumbrella01.entivity;

import java.util.List;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：GetShoppingAddressBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 12:23
 * 描述：获取商家新增地址
 *
 */
public class GetShoppingAddressBean {

    /**
     * status : 1
     * data : [{"id":"5","name":"啊东","phone":"17620193389","zipcode":"42187","region":"广东省广州市天河区","address":"测试号","merchant_id":"7","c_time":"0","u_time":"0","is_inuser":"0"},{"id":"6","name":"啊东。。","phone":"17620193388","zipcode":"58478","region":"黑龙江省伊春市乌马河区","address":"擦擦擦巴巴爸爸啊","merchant_id":"7","c_time":"0","u_time":"0","is_inuser":"0"}]
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
         * id : 5
         * name : 啊东
         * phone : 17620193389
         * zipcode : 42187
         * region : 广东省广州市天河区
         * address : 测试号
         * merchant_id : 7
         * c_time : 0
         * u_time : 0
         * is_inuser : 0
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
       private boolean isSelect =false ;


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
