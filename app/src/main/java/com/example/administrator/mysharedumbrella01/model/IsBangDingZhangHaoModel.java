package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/7/13 0013.
 *  //第三方登录的时候 绑定手机号码
 */

public interface IsBangDingZhangHaoModel {
    /*
    * 三方登录绑定手机号码接口
    * */
    void Bangding(OnBangdingLinerest linerest,String zh,String yzm,String r_id,String pwd);

    /*
    * 接口回调
    * */
    interface OnBangdingLinerest{
        void onComplet(Object object);
    }
}
