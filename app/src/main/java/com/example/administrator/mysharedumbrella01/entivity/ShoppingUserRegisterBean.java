package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：ShoppingUserRegisterBean
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/7 0007 18:14
 * 描述：商家注册的 实体
 */
public class ShoppingUserRegisterBean {


    /**
     * status : 1
     * data : {"success":"信息提交成功"}
     */

    private int status;
    private DataBean data;
    private String is_Authentication;
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getIs_Authentication() {
        return is_Authentication;
    }

    public void setIs_Authentication(String is_Authentication) {
        this.is_Authentication = is_Authentication;
    }



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
         * success : 信息提交成功
         */

        private String success;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }
    }
}
