package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/7/17 0017.
 *  //微信支付 金额
 */

public interface IsWeChatPayZhiFuJinErModel {
    /*
    *  微信支付接口
    * */
    void WeChatZhiFu(OnWeChatLinisenet linisenet,String goods,double total_fee,String apptype,String member_id);

    /*
    * 微信支付接口回调
    * */
    interface OnWeChatLinisenet{
        void onComplet(Object object);
    }
}

