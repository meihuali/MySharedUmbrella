package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.TuikuanBean;
import com.example.administrator.mysharedumbrella01.model.IsTuiKuanModel;
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
 * Created by Administrator on 2017/7/27 0027.
 */

public class TuikuanImpl implements IsTuiKuanModel {
    @Override
    public void tuikuan(final onTuikuanjiekouLinereset linereset, String zhanghao) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.TUIKUANJIEKOU;
        OkGo.post(url)
                .params("user_appid",zhanghao)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("退款结果 "+s);
                        Gson gson = new Gson();
                        TuikuanBean tukuan =  gson.fromJson(s, TuikuanBean.class);
                        linereset.complte(tukuan);
                    }
                });
    }
}
