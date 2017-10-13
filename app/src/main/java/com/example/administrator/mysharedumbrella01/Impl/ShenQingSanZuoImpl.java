package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingComfirBean;
import com.example.administrator.mysharedumbrella01.model.IsShenQingSanZuoModel;
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
 * 文件名：ShenQingSanZuoImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 19:13
 * 描述：TODO
 */
public class ShenQingSanZuoImpl implements IsShenQingSanZuoModel {

    @Override
    public void shenqingsanzuo(final onShenQinSanZUOLinserst linserst, String phone, final String stand, String umbrella) {
        String url= ConfigUtils.ZHU_YU_MING+ConfigUtils.SHANGHUXIADAN;
        OkGo.post(url)
                .params("phone",phone)
                .params("stand",stand)
                .params("umbrella",umbrella)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("商家申请雨伞 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            int status = obj.optInt("status");
                            if (status == 1) {
                                Gson gson = new Gson();
                                ShoppingComfirBean comfirBean = gson.fromJson(s, ShoppingComfirBean.class);
                                linserst.onComplte(comfirBean);
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
