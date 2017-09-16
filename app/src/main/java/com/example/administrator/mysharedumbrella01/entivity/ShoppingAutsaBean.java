package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：ShoppingAutsaBean
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 12:16
 * 描述：商家认证
 */
public class ShoppingAutsaBean {

    /**
     * status : 1
     * data : {"success":"信息提交成功"}
     */

    private int status;
    private DataBean data;
    private String is_Authentication;

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
