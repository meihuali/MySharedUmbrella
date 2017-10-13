package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingConfirmGoodsBean;
import com.example.administrator.mysharedumbrella01.model.IsShoppingConfirmModel;
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
 * 文件名：ShoppingConfirmImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 16:19
 * 描述：获取确认收获列表中的雨伞跟伞座的个数
 */
public class ShoppingConfirmImpl implements IsShoppingConfirmModel {
    @Override
    public void shoppingConfirm(final onShopingConfirmLinsert linsert, String phone) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.CONFIRM_GOODS;
        OkGo.post(url)
                .params("phone",phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("确认收获雨伞数据 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.optInt("status") == 1) {
                                Gson  gson = new Gson();
                                ShoppingConfirmGoodsBean goodsBean =  gson.fromJson(s, ShoppingConfirmGoodsBean.class);
                                linsert.onComplte(goodsBean);
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
