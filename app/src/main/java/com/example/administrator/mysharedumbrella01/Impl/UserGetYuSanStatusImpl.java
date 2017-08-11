package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.model.IsUserGetYuSanStatusModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/11.
 *  用户取走雨伞的接口 实现类
 */

public class UserGetYuSanStatusImpl implements IsUserGetYuSanStatusModel {
    @Override
    public void getYuSanStatus(final onUserGetYuSanSttusLenerist lenerist, String sealt) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.USERGETYUSANSTATUS;
        OkGo.post(url)
                .params("seat",sealt)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("取伞成功  "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            lenerist.onComplte(obj);
                        } catch (Exception e) {
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("取伞失败"+e.getMessage());
                    }
                });
    }
}
