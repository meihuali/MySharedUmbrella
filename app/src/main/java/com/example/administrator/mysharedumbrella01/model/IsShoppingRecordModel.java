package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsShoppingRecordModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/19 0019 9:35
 * 描述：商家充钱记录
 */
public interface IsShoppingRecordModel {
    void record(onRecordLinserent linserent,String phone);

    interface onRecordLinserent{
        void onComplte(Object object);
    }
}
