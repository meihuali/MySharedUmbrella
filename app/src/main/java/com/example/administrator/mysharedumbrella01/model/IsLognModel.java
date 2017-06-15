package com.example.administrator.mysharedumbrella01.model;

import com.example.administrator.mysharedumbrella01.entivity.LoginBean;

/**
 * Created by Administrator on 2017/6/2 0002.
 *  该接口 用来登录 用
 */

public interface IsLognModel {
    //登录用的方法
    void loginmode(onLoginmodeLinistener linistener,String phone,String password );
    //登录接口回调
    interface onLoginmodeLinistener{
        void onComplete(LoginBean logindata);
    }
}
