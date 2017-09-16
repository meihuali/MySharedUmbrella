package com.example.administrator.mysharedumbrella01.entivity;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.entivity
 * 文件名：UserCurrentYusanBean
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/6 0006 11:42
 * 描述：这里是用户正在使用的雨伞
 */
public class UserCurrentYusanBean {

    /**
     * status : 0
     * data : not find
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
