package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingAddressBean;
import com.example.administrator.mysharedumbrella01.model.IsAddAdressModel;
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
 * 文件名：AddAddressImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 10:09
 * 描述：TODO
 */
public class AddAddressImpl implements IsAddAdressModel {
    @Override
    public void addAddress(final onAddressLinenerss linenerss, String name, String phone, String zipcode, String region, String address, String merchant_id, int is_inuser) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.ADD_SHOPPING_ADDRESS;
        OkGo.post(url)
                .params("name",name)
                .params("phone",phone)
                .params("zipcode",zipcode)
                .params("address",address)
                .params("region",region)
                .params("merchant_id",merchant_id)
                .params("is_inuser",is_inuser)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家添加收获地址 "+s);
                        Gson gson = new Gson();
                        ShoppingAddressBean addressBean = gson.fromJson(s, ShoppingAddressBean.class);
                        linenerss.onComptle(addressBean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("商家添加收获地址 "+response.message());
                    }
                });

    }
}
