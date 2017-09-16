package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShangJiaRenZhenBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingShangwuzhongxinModel;
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
 * 文件名：ShangWuzhongxinRenZhengImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 14:16
 * 描述：获取商务中心所有数据 包括证人
 */
public class ShangWuzhongxinRenZhengImpl implements IsShoppingShangwuzhongxinModel{

    @Override
    public void getshangwuzhongxinData(final onShangwuzhongxindata listnts, String id) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.SHANGWUZHONGXINRENZHENGJIEMIAN;
        OkGo.post(url)
                .params("id",id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商务中心认证界面 "+s);
                        Gson gson = new Gson();
                        ShangJiaRenZhenBean shangjia = gson.fromJson(s, ShangJiaRenZhenBean.class);
                        listnts.onComptle(shangjia);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        listnts.onErrorComptle();
                    }
                });
    }
}
