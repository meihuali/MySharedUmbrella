package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/7/13 0013.
 * 绑定是否成功就是这个实体类
 *
 */

public class BangDingStatusBean {

    /**
     * status : 1
     * data : 绑定成功
     */

    private int status;
    private String data;

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
