package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.GetShoppingJiluBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingJiluModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：ShoppingJiluImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/21 0021 17:05
 * 描述：获取商家充值的记录
 */
public class ShoppingJiluImpl implements IsShoppingJiluModel {
    @Override
    public void shoppingJilu(final onShoppingJiLuLinserset luLinserset, String phone) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.GET_JILU;
        OkGo.post(url)
                .params("phone",phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("获取商家充值记录 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.optInt("status") == 1) {
                                Gson gson = new Gson();
                                GetShoppingJiluBean jilu =    gson.fromJson(s, GetShoppingJiluBean.class);
                                luLinserset.onComplte(jilu);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
