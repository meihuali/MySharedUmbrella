package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingQianshouBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingConmfirGoodsModel;
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
 * 文件名：ShoppingConmfirGoodsImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 17:06
 * 描述：TODO
 */
public class ShoppingConmfirGoodsImpl implements IsShoppingConmfirGoodsModel {
    @Override
    public void conmfirGoodsService(final onConmfirGoodsSeviceLinerset linerset, String phone, String stand, String umbrella) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.CONFIREM_GOODSSSS;
        OkGo.post(url)
                .params("phone",phone)
                .params("stand",stand)
                .params("umbrella",umbrella)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("确定收获按钮 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.optInt("status") == 1) {
                                Gson gson = new Gson();
                                ShoppingQianshouBean  qianshou =   gson.fromJson(s, ShoppingQianshouBean.class);
                            linerset.onComplte(qianshou);

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
