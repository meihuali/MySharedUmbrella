package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：AdminBandinBean
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/14 0014 13:05
 * 描述：TODO
 */
public class AdminBandinBean {

    /**
     * status : 1
     * data : {"success":"操作成功"}
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
         * success : 操作成功
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
