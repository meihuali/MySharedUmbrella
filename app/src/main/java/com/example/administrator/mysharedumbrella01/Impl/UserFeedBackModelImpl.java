package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.UserFeedBackBean;
import com.example.administrator.mysharedumbrella01.model.IsUserFeedbackModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/27 0027.
 *   用户反馈
 */

public class UserFeedBackModelImpl implements IsUserFeedbackModel {
    @Override
    public void userfeedback(final OnUseFeedBackListerenr listerenr, String appid, String machine_code, String umbrella_id, String type, String content) {
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
                        Gson gson = new Gson();
                        UserFeedBackBean ufbb = gson.fromJson(s, UserFeedBackBean.class);
                        listerenr.onComplete(ufbb);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }
                });
    }
}
