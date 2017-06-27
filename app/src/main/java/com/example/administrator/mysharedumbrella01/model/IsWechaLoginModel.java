package com.example.administrator.mysharedumbrella01.model;

import com.example.administrator.mysharedumbrella01.entivity.WechatLoginBean;

/**
 * Created by Administrator on 2017/6/23 0023.
 * 微信登录发openID 跟用户名用户头像 给 服务器
 */

public interface IsWechaLoginModel {
    /*
    * 微信登录
    * */
    void login(OnLonInLisenerst lisenerst,String username,String userphoto,String openid);
    /*
    * 微信登录接口回调
    * */
    interface OnLonInLisenerst{
        void onComplete(WechatLoginBean wlb);
    }
}
