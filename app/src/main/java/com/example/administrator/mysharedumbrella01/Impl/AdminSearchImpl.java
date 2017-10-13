package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.AdminSearchAllBean;
import com.example.administrator.mysharedumbrella01.model.IsAdminSearchModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：AdminSearchImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 15:47
 * 描述：管理员 .扫伞的特定id开启对应的伞座号
 */
public class AdminSearchImpl implements IsAdminSearchModel {
    @Override
    public void adminsearch(final onAdminSearchLinerest linerest, String umbrellaid) {
        String url = ConfigUtils.ZHU_YU_MING + ConfigUtils.ANDMIN_SEARCH_ID;
        OkGo.post(url)
                .params("umbrellaid",umbrellaid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        AdminSearchAllBean allBean = gson.fromJson(s, AdminSearchAllBean.class);
                        linerest.onComplte(allBean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
