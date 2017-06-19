package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;
import com.example.administrator.mysharedumbrella01.model.IsAlipayModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/17 0017.
 * //充值 余额
 */

public class AliPayModelImpl implements IsAlipayModel {
    @Override
    public void ZhiFuBao(final OnZhiFuBaoLinset linset, String zhifujixing, String money, String user, String zhifubiaoti) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.ZHIFUBAO_CHONGZHI_JINER;
        OkGo.post(url)
                .params("app",zhifujixing)
                .params("money",money)
                .params("user",user)
                .params("body",zhifubiaoti)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("zhifubao 支付宝充值金额 "+s);
                        Gson gson = new Gson();
                        ZhifubaoBean zfb = gson.fromJson(s, ZhifubaoBean.class);
                        linset.onCompelte(zfb);
                    }
                });
    }

}
