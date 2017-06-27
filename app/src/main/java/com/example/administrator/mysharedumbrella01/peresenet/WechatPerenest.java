package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.WechatLoginmodelImpl;
import com.example.administrator.mysharedumbrella01.entivity.WechatLoginBean;
import com.example.administrator.mysharedumbrella01.model.IsWechaLoginModel;
import com.example.administrator.mysharedumbrella01.view.IsWechatLoginView;

/**
 * Created by Administrator on 2017/6/23 0023.
 * 微信登录
 */

public class WechatPerenest {
    IsWechaLoginModel wechaLoginModel;
    IsWechatLoginView wechatLoginView;

    public WechatPerenest(IsWechatLoginView wechatLoginView) {
        this.wechatLoginView = wechatLoginView;
        wechaLoginModel = new WechatLoginmodelImpl();
    }

    public void fach(String username, String userphoto, String openid) {
        if (wechaLoginModel != null) {
            wechaLoginModel.login(new IsWechaLoginModel.OnLonInLisenerst() {
                @Override
                public void onComplete(WechatLoginBean wlb) {
                    wechatLoginView.showLogin(wlb);
                }
            },username,userphoto,openid);
        }
    }
}
