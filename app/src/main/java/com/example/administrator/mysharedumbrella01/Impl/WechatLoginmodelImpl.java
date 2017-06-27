package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.WechatLoginBean;
import com.example.administrator.mysharedumbrella01.model.IsWechaLoginModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/23 0023.
 * // 微信登录 实现类
 */

public class WechatLoginmodelImpl implements IsWechaLoginModel {
    @Override
    public void login(final OnLonInLisenerst lisenerst, String username, String userphoto, String openid) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.WECHAT_LOGING;
        OkGo.post(url)
                .params("appid",openid)
                .params("name",username)
                .params("photo",userphoto)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("三方微信登录 "+s);
                        //解析
                        Gson gson = new Gson();
                        WechatLoginBean wlb = gson.fromJson(s, WechatLoginBean.class);
                        lisenerst.onComplete(wlb);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
