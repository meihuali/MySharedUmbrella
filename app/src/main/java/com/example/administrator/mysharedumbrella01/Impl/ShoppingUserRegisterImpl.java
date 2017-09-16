package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingUserRegisterBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingUserRegisterModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：ShoppingUserRegisterImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/7 0007 17:53
 * 描述：商家注册 实现类
 */
public class ShoppingUserRegisterImpl implements IsShoppingUserRegisterModel{
    @Override
    public void ShoppingRegister(final onShoppingLisenerts lisenerts,  String dianming, String shoujihaoma, File img,String address) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.SHOPPING_AUTIONER;
        OkGo.post(url)
                .params("phone",shoujihaoma) //手机号码
                .params("img",img)
                .params("merchantname",dianming) //店名
                .params("address",address)
                // .params("app",appStyle) //app 类型 1为ios，2为Android
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家注册 "+s);
                        Gson gson = new Gson();
                        ShoppingUserRegisterBean shangjia =  gson.fromJson(s, ShoppingUserRegisterBean.class);
                        lisenerts.onComplte(shangjia);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("商家注册 "+response.message());
                    }
                });

    }

}
