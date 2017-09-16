package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.entivity.ShoppingImgChangBean;
import com.example.administrator.mysharedumbrella01.model.IsImageChangeModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.ProgressRequestBody;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Impl
 * 文件名：ImageChangImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 15:16
 * 描述：TODO
 */
public class ImageChangImpl implements IsImageChangeModel{

    @Override
    public void imageChange(final onImageChangLinserts linserts, String type, String phone, File file) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.UPDATA_IMAGE_CHANG;
        OkGo.post(url)
                .params("type",type)
                .params("phone",phone)
                .params("img",file)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("街景 "+s);
                        Gson gson = new Gson();
                        ShoppingImgChangBean shoppingimage  =  gson.fromJson(s, ShoppingImgChangBean.class);
                        linserts.onComplte(shoppingimage);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
