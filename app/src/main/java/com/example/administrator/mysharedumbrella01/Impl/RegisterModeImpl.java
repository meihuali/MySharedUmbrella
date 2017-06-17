package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.RegisterBean;
import com.example.administrator.mysharedumbrella01.model.IsRegisterModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/6 0006.
 * //注册接口 的实现类
 */

public class RegisterModeImpl implements IsRegisterModel {
    @Override
    public void register(final OnRegisterListener listener, String ed_phone, String ed_pwd, String ed_name,String yanzhengma) {
        //这里网络请求
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.ZHUCEJIEKOUHOUZHUO;
        OkGo.post(url)
                .params("phone",ed_phone)
                .params("password",ed_pwd)
                .params("name",ed_name)
                .params("code",yanzhengma)
                .params("app","2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("zhucejieguo  注册结果 "+s);
                        //这里解析
                        Gson gson = new Gson();
                        RegisterBean rb =  gson.fromJson(s, RegisterBean.class);
                        listener.onComplete(rb);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }
                });

    }
}
