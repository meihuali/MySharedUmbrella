package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ModifyPasswordBean;
import com.example.administrator.mysharedumbrella01.model.IsModifyPasswordModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/16 0016.
 * // 修改密码的实现类
 */

public class ModifyPasswordModelImpl implements IsModifyPasswordModel {
    @Override
    public void password(final OnPassWordLineserset lineserset, String phone, String newPassWord, String yanzhenma) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.XIUGAIMIMA;
        OkGo.post(url)
                .params("appid",phone)
                .params("newpassword",newPassWord)
                .params("type","2")
                .params("code",yanzhenma)
                .params("app","2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("xiugaimima  修改密码  "+s);
                        Gson gson = new Gson();
                        ModifyPasswordBean mpb = gson.fromJson(s, ModifyPasswordBean.class);
                        lineserset.OnCompelte(mpb);
                    }
                });
    }
}
