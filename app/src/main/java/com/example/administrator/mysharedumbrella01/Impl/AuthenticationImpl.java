package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingAutsaBean;
import com.example.administrator.mysharedumbrella01.model.IsAuthenticationModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：AuthenticationImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 10:49
 * 描述：商家认证
 */
public class AuthenticationImpl implements IsAuthenticationModel {
    @Override
    public void Authent(final onAuthenticationLinerset linerset, String phone, String address, String img) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.SHOPPING_AUTIONER;
        OkGo.post(url)
                .params("phone",phone)
                .params("address",address)
                .params("img",img)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家认证 "+s);
                        Gson gson = new Gson();
                        ShoppingAutsaBean sab = gson.fromJson(s, ShoppingAutsaBean.class);
                        linerset.onComplet(sab);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("商家认证 "+response.message());
                        linerset.onErrorComplte();

                    }
                });
    }
}
