package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsAddAdressModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 10:03
 * 描述：添加收获地址
 */
public interface IsAddAdressModel {
    void addAddress(onAddressLinenerss linenerss,String name,String phone,String zipcode,String region,String address,String merchant_id,int is_inuser );

    interface onAddressLinenerss{
        void onComptle(Object object);
    }
}
