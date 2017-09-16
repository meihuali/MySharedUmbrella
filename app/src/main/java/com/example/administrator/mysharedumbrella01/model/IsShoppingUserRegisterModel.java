package com.example.administrator.mysharedumbrella01.model;

import java.io.File;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingUserRegisterModel
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/7 0007 17:31
 * 描述：商家注册 的接口
 */
public interface IsShoppingUserRegisterModel {
    /*
    *   appStyle  1为ios，2为Android
    * */
    void ShoppingRegister(onShoppingLisenerts lisenerts, String dianming, String shoujihaoma, File img,String address);

    /*
    *
    * 接口回调
    * */
    interface onShoppingLisenerts{
        void onComplte(Object object);
    }
}
