package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsGetShoppingAddressModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 12:03
 * 描述：获取商户的新增加的地址
 */
public interface IsGetShoppingAddressModel {
    void getAddress(onGetAddressLinerster linerster,String merchant_id);

    interface onGetAddressLinerster{
        void onComplte(Object object);
     }
}
