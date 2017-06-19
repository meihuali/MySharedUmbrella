package com.example.administrator.mysharedumbrella01.model;

import com.example.administrator.mysharedumbrella01.entivity.ZhiFuBaoYaJinBean;
import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public interface IsAipayYaJinModel {
    //充值押金
    void ZhiFuBaoYaJin(OnZhifubaoYajinLinenere linenere,String zhifujixing,String money,String user, String zhifubiaoti);

    //充值押金 接口回调
    interface OnZhifubaoYajinLinenere{
        void OnCompelte(ZhifubaoBean zfb);
    }
}
