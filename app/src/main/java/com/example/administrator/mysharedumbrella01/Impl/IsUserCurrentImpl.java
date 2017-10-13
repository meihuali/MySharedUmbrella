package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.UserCurrentYusanBean;
import com.example.administrator.mysharedumbrella01.model.IsUserCurrentMonde;
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
 * 文件名：IsUserCurrentImpl
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/6 0006 10:46
 * 描述：TODO
 */
public class IsUserCurrentImpl implements IsUserCurrentMonde {
    @Override
    public void userCurrent(final onUserCurrent liners, String r_id) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.GET_USER_CURRENTS;
        OkGo.post(url)
                .params("r_id",r_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("用户正在使用的雨伞："+s);
                        Gson gson = new Gson();
                        UserCurrentYusanBean useryusanBen  = gson.fromJson(s, UserCurrentYusanBean.class);
                        liners.onComplte(useryusanBen);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        try {
                            liners.onErres();
                        } catch (Exception e1) {
                        }

                    }
                });
    }
}
