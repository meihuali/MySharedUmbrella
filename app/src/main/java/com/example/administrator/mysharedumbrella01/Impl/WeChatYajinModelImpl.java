package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.model.IsWeChatYaJinModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/17 0017.
 *  //充值押金
 */

public class WeChatYajinModelImpl implements IsWeChatYaJinModel {
    @Override
    public void wechatYajin(final OnWechatYajinLinerest linerest, String goods, double total_fee, String apptype, String member_id) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.WECHATPAYZHIFU;
        OkGo.post(url)
                .params("goods",goods)
                .params("total_fee",total_fee)
                .params("apptype",apptype)
                .params("member_id",member_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("微信充值押金结果 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            linerest.onComplet(obj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
