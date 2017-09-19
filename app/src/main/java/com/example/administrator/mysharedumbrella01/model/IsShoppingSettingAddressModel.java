package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingSettingAddressModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/19 0019 11:48
 * 描述：商家手动在列表点击设置 默认地址
 */
public interface IsShoppingSettingAddressModel {

    void settingaddress(onSettingAddressLinerset linerset,String merchant_id, String address_id );

    interface onSettingAddressLinerset{
        void onComplte(Object object);
    }
}
