package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingLoginModel
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 10:42
 * 描述： 商家登录 接口
 */
public interface IsShoppingLoginModel {
    void shoppingLogin(onShoppingLoginLinseres loginLinseres,String phone,String pwd);

    interface onShoppingLoginLinseres{

        void onComplte(Object object);
    }
}
