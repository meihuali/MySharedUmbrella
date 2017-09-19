package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.GetShoppingAddressBean;
import com.example.administrator.mysharedumbrella01.model.IsGetShoppingAddressModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：GetShoppingAddressImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 12:08
 * 描述：获取商家新增的 收获地址
 */
public class GetShoppingAddressImpl implements IsGetShoppingAddressModel {

    @Override
    public void getAddress(final onGetAddressLinerster linerster, String merchant_id) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.GET_SHOPPING_ADDRESS;
        OkGo.post(url)
                .params("merchant_id",merchant_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("获取商家新增的所有地址 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            int status = obj.optInt("status");
                            if (status == 1) {
                                Gson gson = new Gson();
                                GetShoppingAddressBean getaddress =  gson.fromJson(s, GetShoppingAddressBean.class);
                                linerster.onComplte(getaddress);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
