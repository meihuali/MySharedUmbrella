package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.YuSanIconBean;
import com.example.administrator.mysharedumbrella01.model.IsHaiYuSanTuIconModle;
import com.example.administrator.mysharedumbrella01.model.IsYuSanTuIconModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/10 0010.
 *  还伞 界面的图标
 */

public class HaiYuSanTuIconModelImpl implements IsHaiYuSanTuIconModle {
    @Override
    public void getYushanIcon(final IsYuSanTuIconModel.OnYuShanIconLinsetern linsetern, String numberss) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.HUOQUYUSHANTUBIAO;
        OkGo.post(url)
                .params("imgid",numberss)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("还伞获取雨伞图标 "+s);
                        Gson gson = new Gson();
                        YuSanIconBean haisanIconbean = gson.fromJson(s, YuSanIconBean.class);
                        linsetern.OnComptle(haisanIconbean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
