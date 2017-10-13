package com.example.administrator.mysharedumbrella01.model;

import com.example.administrator.mysharedumbrella01.entivity.ZhiFuBaoYaJinBean;
import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;

/**
 * Created by Administrator on 2017/6/17 0017.
 * //支付宝·本地自己写的接口 MVP 设计来的哦
 */

public interface IsAlipayModel {
    //接口
    void ZhiFuBao(OnZhiFuBaoLinset linset,String zhifujixing,String money,String user, String zhifubiaoti,String is_merchant);
    //接口回调
    interface OnZhiFuBaoLinset{
        void onCompelte(ZhifubaoBean zfb);
    }

}
