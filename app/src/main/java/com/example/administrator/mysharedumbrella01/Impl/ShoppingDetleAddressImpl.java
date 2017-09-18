package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingDetelAddressBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingDetleAddressModle;
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
 * 文件名：ShoppingDetleAddressImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 19:25
 * 描述：TODO
 */
public class ShoppingDetleAddressImpl implements IsShoppingDetleAddressModle{
    @Override
    public void detelAddress(final onDelteAddressLinerset linerset, String address_id) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.DELTE_SHOPPING_ADDRESS;
        OkGo.post(url)
                .params("address_id",address_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("删除商家收货地址 "+ s );
                        Gson gson = new Gson();
                        ShoppingDetelAddressBean detelBean = gson.fromJson(s, ShoppingDetelAddressBean.class);
                        linerset.onComplte(detelBean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("删除商家收货地址 "+ response.message() );
                    }
                });
    }
}
