package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsAuthenticationModel
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 10:33
 * 描述：商家认证界面
 */
public interface IsAuthenticationModel {
    void Authent(onAuthenticationLinerset linerset,String phone,String address,String img);

    /*
    * 认证接口回调
    * */
    interface onAuthenticationLinerset{
        /*
        * 成功的接口回调
        * */
        void onComplet(Object object);

        /*
        * 失败走error的接口回调
        * */
        void onErrorComplte();
    }


}
