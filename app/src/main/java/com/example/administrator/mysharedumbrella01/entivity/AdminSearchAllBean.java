package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：AdminSearchAllBean
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 15:33
 * 描述：TODO
 */
public class AdminSearchAllBean {


    /**
     * status : 1
     * data : 开锁指令发送成功
     */

    private int status;
    private String data;
    private String error_reason;

    public String getError_reason() {
        return error_reason;
    }

    public void setError_reason(String error_reason) {
        this.error_reason = error_reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
