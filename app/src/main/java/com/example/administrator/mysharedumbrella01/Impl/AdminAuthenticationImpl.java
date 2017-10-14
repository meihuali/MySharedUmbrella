package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.AdminAuthenticationBean;
import com.example.administrator.mysharedumbrella01.model.IsAdminAuthenticationModel;
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
 * 文件名：AdminAuthenticationImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 16:45
 * 描述：管理员界面那个扫描后  信息认证
 */
public class AdminAuthenticationImpl implements IsAdminAuthenticationModel {
    @Override
    public void getAdminAllAuthentication(final onAdminAllAuthenticonLisenert lisenert, String phone) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.ADMIN_AUTHENICATION;
        OkGo.post(url)
                .params("phone",phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("管理员扫描信息认证 "+s);

                        Gson gson = new Gson();
                        AdminAuthenticationBean bean = gson.fromJson(s, AdminAuthenticationBean.class);
                        lisenert.onComplte(bean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

    }
}
