package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.CheckBingdingBean;
import com.example.administrator.mysharedumbrella01.model.IsCheckBindingModel;
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
 * 文件名：CheckBingdingImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/12 0012 14:24
 * 描述：检测用户是否有绑定过手机号码
 */
public class CheckBingdingImpl implements IsCheckBindingModel{
    @Override
    public void checkbangding(final onCheckBindingLinsestes linsestes, String phone) {
        String url  = ConfigUtils.ZHU_YU_MING+ConfigUtils.CHECK_BINGDING;
        OkGo.post(url)
                .params("phone",phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("绑定手机号码："+s);
                        Gson gson = new Gson();
                       CheckBingdingBean checkBingdingBean = gson.fromJson(s, CheckBingdingBean.class);
                        linsestes.onComplte(checkBingdingBean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("绑定手机号码："+response.message());
                    }
                });
    }
}
