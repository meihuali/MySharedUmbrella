package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingQueryAutBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingQueryAuthenticationModel;
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
 * 文件名：ShoppingQueryAuthentionImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 14:07
 * 描述：查询商家是否已经认证过了
 */
public class ShoppingQueryAuthentionImpl implements IsShoppingQueryAuthenticationModel{
    @Override
    public void QueryAutent(final onQueryAutentLinserts linserts, String phone) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.QUERYSHOPPINGAUT;
        OkGo.post(url)
                .params("phone",phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家是否认证 "+s);
                        Gson gson = new Gson();
                        ShoppingQueryAutBean shoppingAut = gson.fromJson(s, ShoppingQueryAutBean.class);
                        linserts.onComplte(shoppingAut);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }
                });

    }
}
