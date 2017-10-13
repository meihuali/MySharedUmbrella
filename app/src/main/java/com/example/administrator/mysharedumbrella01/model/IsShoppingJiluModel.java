package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingJiluModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 17:02
 * 描述：商家充值记录
 */
public interface IsShoppingJiluModel  {

    void shoppingJilu(onShoppingJiLuLinserset luLinserset,String phone);

    interface onShoppingJiLuLinserset{
        void onComplte(Object object);
    }
}
