package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.KefufankuisBean;
import com.example.administrator.mysharedumbrella01.model.IsKeFuFanKuisModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：IsKefufankuisImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/5 0005 18:50
 * 描述：TODO
 */
public class IsKefufankuisImpl implements IsKeFuFanKuisModel {
    @Override
    public void bugFanKui(final onBugFanKuiLisener lisener, String appid, String type, File file,String bodys) {

        if (file != null) {
            String url = ConfigUtils.ZHU_YU_MING + ConfigUtils.TIJIAOWENTFANKUI;
            OkGo.post(url)
                    .params("appid", appid)
                    .params("type", type)
                    .params("img", file)
                    .params("content", bodys)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            L.e("提交问题 " + s);
                            Gson gson = new Gson();
                            KefufankuisBean kf = gson.fromJson(s, KefufankuisBean.class);
                            lisener.onComplte(kf);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            L.e("提交问题 " + response.message());
                        }
                    });
        } else {
            String url = ConfigUtils.ZHU_YU_MING + ConfigUtils.TIJIAOWENTFANKUI;
            OkGo.post(url)
                    .params("appid", appid)
                    .params("type", type)
                    .params("content", bodys)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            L.e("提交问题 " + s);
                            Gson gson = new Gson();
                            KefufankuisBean kf = gson.fromJson(s, KefufankuisBean.class);
                            lisener.onComplte(kf);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            L.e("提交问题 " + response.message());
                        }
                    });
        }

    }
}
