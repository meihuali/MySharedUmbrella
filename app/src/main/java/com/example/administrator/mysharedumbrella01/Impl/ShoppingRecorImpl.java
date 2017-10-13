package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingRecorBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingRecordModel;
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
 * 文件名：ShoppingRecorImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/19 0019 9:37
 * 描述：TODO
 */
public class ShoppingRecorImpl implements IsShoppingRecordModel {
    @Override
    public void record(final onRecordLinserent linserent, String phone) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.SHOPPING_MONEY_RECOR;
        OkGo.post(url)
                .params("phone", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家充值记录 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.optInt("status") == 1) {
                                Gson gson = new Gson();
                                ShoppingRecorBean sprb =  gson.fromJson(s, ShoppingRecorBean.class);
                                linserent.onComplte(sprb);
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
