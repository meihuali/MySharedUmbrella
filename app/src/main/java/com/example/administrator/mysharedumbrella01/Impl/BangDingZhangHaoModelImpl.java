package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.BangDingStatusBean;
import com.example.administrator.mysharedumbrella01.model.IsBangDingZhangHaoModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/13 0013.
 *  绑定 手机号码 实现类
 */

public class BangDingZhangHaoModelImpl implements IsBangDingZhangHaoModel {
    @Override
    public void Bangding(final OnBangdingLinerest linerest, String zh, String yzm, String r_id,String pwd) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.BANGDINGSHOUJIHAOMA;
        OkGo.post(url)
                .params("mobilephone",zh)
                .params("r_id",r_id)
                .params("pwd",pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("三方登录验证 "+ s);
                        Gson gson = new Gson();
                        BangDingStatusBean bangdingzhuangtai = gson.fromJson(s, BangDingStatusBean.class);
                        linerest.onComplet(bangdingzhuangtai);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("三方登录验证 "+response.message());
                    }
                });
    }
}
