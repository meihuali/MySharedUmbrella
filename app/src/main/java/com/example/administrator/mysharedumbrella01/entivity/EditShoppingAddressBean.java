package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：EditShoppingAddressBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 16:55
 * 描述：修改商家地址
 */
public class EditShoppingAddressBean {

    /**
     * status : 1
     * data : {"success":"保存成功"}
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
         * success : 保存成功
         */

        private String success;
        private String error_reason;

        public String getError_reason() {
            return error_reason;
        }

        public void setError_reason(String error_reason) {
            this.error_reason = error_reason;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }
    }
}
