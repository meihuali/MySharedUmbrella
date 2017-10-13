package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsGetShoppingYajinModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 16:33
 * 描述：获取商家押金 接口
 */
public interface IsGetShoppingYajinModel {

    void getyajin(onGetShoppingYaJinLinerset linerset,String phone);

    interface onGetShoppingYaJinLinerset{

        void onComplte(Object object);
    }
}
