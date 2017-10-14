package com.example.administrator.mysharedumbrella01.model;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsComiteBindingModel
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/14 0014 11:22
 * 描述：
 */
public interface IsComiteBindingModel {
    void comiteBangding(onComiteBangdingLinesiet linesiet,String phone,String machine_id,String merchant_id,String longitude,String latitude);

    interface onComiteBangdingLinesiet{
        void onComplte(Object object);
    }
}
