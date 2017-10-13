package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.AdminSearchAllBean;
import com.example.administrator.mysharedumbrella01.model.IsAdminSearchAllModel;
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
 * 文件名：AdminSearchAllImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 15:19
 * 描述：管理员 扫码测试全部伞座号是否能正常开锁
 */
public class AdminSearchAllImpl implements IsAdminSearchAllModel {
    @Override
    public void onAdminSeach(final OnAdminSeachLister lister, String phone, String machine_id) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.ANDMIN_SEARCH_ALL;
        OkGo.post(url)
                .params("phone",phone)
                .params("machine_id",machine_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("管理扫描开启全部锁 "+s);
                        Gson gson = new Gson();
                       AdminSearchAllBean allBean =  gson.fromJson(s, AdminSearchAllBean.class);
                       lister.onComptle(allBean);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
