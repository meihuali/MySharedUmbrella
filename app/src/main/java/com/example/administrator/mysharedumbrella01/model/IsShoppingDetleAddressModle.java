package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingDetleAddressModle
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 19:24
 * 描述：删除商家收获地址
 */
public interface IsShoppingDetleAddressModle {
    void detelAddress(onDelteAddressLinerset linerset,String address_id);

    interface onDelteAddressLinerset{
        void onComplte(Object object);
    }
}
