package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.YuSanIconBean;
import com.example.administrator.mysharedumbrella01.model.IsYuSanTuIconModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/8 0008.
 *  获取服务器返回的 主界面 雨伞图标
 */

public class YuSanTuIconModelImpl implements IsYuSanTuIconModel {
    @Override
    public void getYushanIcon(final OnYuShanIconLinsetern linsetern, String numberss) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.HUOQUYUSHANTUBIAO;
        OkGo.post(url)
                .params("img_cid",numberss)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("获取雨伞图标 "+s);
                        Gson gson = new Gson();
                        YuSanIconBean ysib =  gson.fromJson(s, YuSanIconBean.class);
                        linsetern.OnComptle(ysib);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }


}
