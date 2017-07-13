package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.UpdataBean;
import com.example.administrator.mysharedumbrella01.model.IsUpdataAppModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/26 0026.
 * 更新APP 实现类
 */

public class UpdataAppModelImpl implements IsUpdataAppModel {

    @Override
    public void updataApp(final OnUpdataAppListener listener) {
     String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.UDUPDATAAPP;

        OkGo.get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                L.e("更新app " + s);
                Gson gson = new Gson();
                UpdataBean ub = gson.fromJson(s, UpdataBean.class);
                listener.onComplete(ub);
            }
        });
    }
}
