package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;
import com.example.administrator.mysharedumbrella01.model.IsAipayYaJinModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class AliPayYaJinModelImpl implements IsAipayYaJinModel {

    public void ZhiFuBaoYaJin(final IsAipayYaJinModel.OnZhifubaoYajinLinenere linenere, String zhifujixing, String money, String user, String zhifubiaoti,String is_merchant) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.ZHIFUBAO_CHONGZHI_JINER;
        OkGo.post(url)
                .params("app",zhifujixing)
                .params("money",money)
                .params("user",user)
                .params("body",zhifubiaoti)
                .params("is_merchant",is_merchant)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("zhifubao 支付宝充值金额 "+s);
                        Gson gson = new Gson();
                        ZhifubaoBean zfb = gson.fromJson(s, ZhifubaoBean.class);
                        linenere.OnCompelte(zfb);
                    }
                });
    }
}
