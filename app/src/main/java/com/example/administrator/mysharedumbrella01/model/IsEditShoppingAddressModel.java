package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsEditShoppingAddressModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 15:56
 * 描述：修改商家地址
 */
public interface IsEditShoppingAddressModel {
    void editAddress(onEditAddressLinserst linserst,String name,String phone,String zipcode,String region,String address,String merchant_id,String is_inuser,String address_id);

    interface onEditAddressLinserst{
        void onComplte(Object object);
    }
}
