package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.UserFeedBackBean;
import com.example.administrator.mysharedumbrella01.model.IsQiTaWenTiModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class QiTaWenTiModelImpl implements IsQiTaWenTiModel {

    @Override
    public void qitawent(OnqitawentListerensst listerensst, String appid, String machine_code, String umbrella_id, String type, String content) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.YONGHUFANKUI_ONE;
        OkGo.post(url)
                .params("appid",appid)
                .params("machine_code",machine_code)
                .params("umbrella_id",umbrella_id)
                .params("type",type)
                .params("content",content)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("用户反馈1 "+s);
                        //解析

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }
                });
    }


}
