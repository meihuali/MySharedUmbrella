package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.KeFuFanKuiBean;
import com.example.administrator.mysharedumbrella01.model.IsKeFuFanKuiModel;
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
 * Created by Administrator on 2017/7/26 0026.
 *   客户反馈 实现类
 */

public class KeFuFanKuiImpl implements IsKeFuFanKuiModel{

    @Override
    public void kefufankui(final onKefufankuiLinerenst linerenst) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.KEFUFANKUI;
        OkGo.get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("客户反馈接口 "+s);
                        try {
                            Gson gson = new Gson();
                            KeFuFanKuiBean keFuFanKuiBean =  gson.fromJson(s, KeFuFanKuiBean.class);
                            linerenst.onComplte(keFuFanKuiBean);
                        } catch (Exception e) {
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
