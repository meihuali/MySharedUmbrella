package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.SanZuoSanBean;
import com.example.administrator.mysharedumbrella01.model.IsGetShoppingSanzuoSanModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：GetShoppingSanZuoSanImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 18:35
 * 描述：
 */
public class GetShoppingSanZuoSanImpl implements IsGetShoppingSanzuoSanModel{
    @Override
    public void getSanZuoSan(final onGetSanZuoSanLinerst linerst, String merchant_id) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.GET_SHOPPING_SANZUOSAN;
        OkGo.post(url)
                .params("merchant_id",merchant_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("获取商家伞座跟伞 "+s);
                        Gson gson = new Gson();
                        SanZuoSanBean sanzuosan =  gson.fromJson(s, SanZuoSanBean.class);
                        linerst.onComplte(sanzuosan);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("获取商家伞座跟伞 "+response.message());
                    }
                });

    }
}
