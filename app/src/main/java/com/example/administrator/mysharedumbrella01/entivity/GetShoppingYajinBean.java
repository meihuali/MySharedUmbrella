package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：GetShoppingYajinBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 16:48
 * 描述：TODO
 */
public class GetShoppingYajinBean {

    /**
     * status : 1
     * data : {"success":"0.34"}
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
         * success : 0.34
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
