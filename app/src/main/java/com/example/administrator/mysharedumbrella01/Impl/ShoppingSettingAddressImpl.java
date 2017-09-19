package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingSettingAddressBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingSettingAddressModel;
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
 * 文件名：ShoppingSettingAddressImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/19 0019 11:52
 * 描述：TODO
 */
public class ShoppingSettingAddressImpl implements IsShoppingSettingAddressModel {
    @Override
    public void settingaddress(final onSettingAddressLinerset linerset, String merchant_id, String address_id) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.SHOPPING_SETTING_ADDRESS;

        OkGo.post(url)
                .params("merchant_id",merchant_id)
                .params("address_id",address_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家手动修改地址 "+s);
                        Gson gson = new Gson();
                        ShoppingSettingAddressBean spaddress =    gson.fromJson(s, ShoppingSettingAddressBean.class);
                        linerset.onComplte(spaddress);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("商家手动修改地址 "+response.message());
                    }
                });
    }
}
