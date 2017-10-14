package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.AdminBandinBean;
import com.example.administrator.mysharedumbrella01.model.IsComiteBindingModel;
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
 * 文件名：ComiteBindingImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/14 0014 11:25
 * 描述：TODO
 */
public class ComiteBindingImpl implements IsComiteBindingModel {
    @Override
    public void comiteBangding(final onComiteBangdingLinesiet linesiet, String phone, String machine_id, String merchant_id, String longitude, String latitude) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.ANDMIN_BANGDIN;
        OkGo.post(url)
                .params("phone",phone)
                .params("machine_id",machine_id)
                .params("merchant_id",merchant_id)
                .params("longitude",longitude)
                .params("latitude",latitude)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("管理员绑定信息 "+s);
                        Gson gson = new Gson();
                       AdminBandinBean bandinBean =  gson.fromJson(s, AdminBandinBean.class);
                        linesiet.onComplte(bandinBean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("管理员绑定信息 "+response.message());
                    }
                });

    }
}
