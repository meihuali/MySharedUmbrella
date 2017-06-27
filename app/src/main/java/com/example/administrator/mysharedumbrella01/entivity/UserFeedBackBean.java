package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/6/27 0027.
 * 用户反馈实体类
 */

public class UserFeedBackBean {

    /**
     * status : 1
     * data : 反馈成功
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
