package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.GetShoppingYajinBean;
import com.example.administrator.mysharedumbrella01.model.IsGetShoppingYajinModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：GetShoppingYajinImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 16:35
 * 描述：获取商家押金的接口
 */
public class GetShoppingYajinImpl implements IsGetShoppingYajinModel {
    @Override
    public void getyajin(final onGetShoppingYaJinLinerset linerset, String phone) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.GET_SHOPPING_YAJIN;
        OkGo.post(url)
                .params("phone",phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("获取商家押金 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            int status = obj.optInt("status");
                            if (status == 1) {
                                Gson gson = new Gson();
                                GetShoppingYajinBean getyajin = gson.fromJson(s, GetShoppingYajinBean.class);
                                linerset.onComplte(getyajin);
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
