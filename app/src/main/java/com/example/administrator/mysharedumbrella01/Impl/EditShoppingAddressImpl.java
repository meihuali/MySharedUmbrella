package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.EditShoppingAddressBean;
import com.example.administrator.mysharedumbrella01.model.IsEditShoppingAddressModel;
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
 * 文件名：EditShoppingAddressImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 16:00
 * 描述：TODO
 */
public class EditShoppingAddressImpl implements IsEditShoppingAddressModel {
    @Override
    public void editAddress(final onEditAddressLinserst linserst, String name, String phone, String zipcode, String region, String address, String merchant_id, String is_inuser, String address_id) {
        String url = ConfigUtils.ZHU_YU_MING + ConfigUtils.EDIT_SHOPPING_ADDRSSS;
        OkGo.post(url)
                .params("name",name)
                .params("phone",phone)
                .params("zipcode",zipcode)
                .params("region",region)
                .params("address",address)
                .params("merchant_id",merchant_id)
                .params("address_id",address_id)
                .params("is_inuser",is_inuser)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家收获地址修改 "+s);
                        Gson gson = new Gson();
                        EditShoppingAddressBean editbean = gson.fromJson(s, EditShoppingAddressBean.class);
                        linserst.onComplte(editbean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }
                });

    }
}
