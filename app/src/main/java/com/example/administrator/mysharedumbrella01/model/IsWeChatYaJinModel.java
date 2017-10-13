package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/7/17 0017.
 *  该接口充值押金
 */

public interface IsWeChatYaJinModel {
    /*
    * 充值押金
    * */
    void wechatYajin(OnWechatYajinLinerest linerest,String goods,double total_fee,String apptype,String member_id,String is_merchant);
    /*
    * 充值押金接口回调
    * */
    interface OnWechatYajinLinerest{

        void onComplet(Object object);
    }
}
