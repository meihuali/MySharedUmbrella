package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.WeiXinDingDanJieGuoBean;
import com.example.administrator.mysharedumbrella01.model.IsWeChatPayZhiFuJinErModel;
import com.example.administrator.mysharedumbrella01.model.IsWechaLoginModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/17 0017.
 *  //微信支持金额
 */

public class WeChatPayZhiFuJinErModelImpl implements IsWeChatPayZhiFuJinErModel {

    @Override
    public void WeChatZhiFu(final OnWeChatLinisenet linisenet, String goods, double total_fee, String apptype, String member_id,String is_merchant) {
        String url = ConfigUtils.ZHU_YU_MING + ConfigUtils.WECHATPAYZHIFU;
        OkGo.post(url)
                .params("goods",goods)
                .params("total_fee",total_fee)
                .params("apptype",apptype)
                .params("member_id",member_id)
                .params("is_merchant",is_merchant)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("微信充值金额订单json "+s);
                        //解析后将对象回调给activity
                        Gson gson = new Gson();
                        WeiXinDingDanJieGuoBean weixindingdan = gson.fromJson(s, WeiXinDingDanJieGuoBean.class);
                        linisenet.onComplet(weixindingdan);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }
                });

    }
}
