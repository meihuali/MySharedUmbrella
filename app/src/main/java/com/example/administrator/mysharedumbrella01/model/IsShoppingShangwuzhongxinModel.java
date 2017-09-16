package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingShangwuzhongxinModel
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 14:13
 * 描述：获取商务中心的所有 数据 包括认证
 */
public interface IsShoppingShangwuzhongxinModel {

    void getshangwuzhongxinData(onShangwuzhongxindata listnts,String id);

    interface onShangwuzhongxindata{
        /*
        * 成功接口回调
        * */
        void onComptle(Object object);

        /*
        * 失败接口回调
        * */
        void onErrorComptle();
    }
}
