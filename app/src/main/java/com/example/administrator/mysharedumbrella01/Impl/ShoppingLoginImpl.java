package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingLoginBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingLoginModel;
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
 * 文件名：ShoppingLoginImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 10:45
 * 描述：商家登录
 */
public class ShoppingLoginImpl implements IsShoppingLoginModel {
    @Override
    public void shoppingLogin(final onShoppingLoginLinseres loginLinseres, String phone, String pwd) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.SHOPPING_LOGIN;
        OkGo.post(url)
                .params("phone",phone)
                .params("pwd",pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家登录 "+s);
                        Gson gson = new Gson();
                        ShoppingLoginBean splb = gson.fromJson(s, ShoppingLoginBean.class);
                        loginLinseres.onComplte(splb);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
