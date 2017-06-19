package com.example.administrator.mysharedumbrella01.Impl;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.model.IsShangchuanPhotoModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class ShangChuanTouXiangModelImpl implements IsShangchuanPhotoModel {
    @Override
    public void ShangChuanTouXiang(OnShangChuanListener listener, File file, Activity activity) {
        //这里 写上传 头像的  网络请求
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.SHANGCHUANTOUXIANG;
        String zh = ShareUtils.getString(activity,"zhanghao","");
        OkGo.post(url)
                .params("appid",zh)
                .params("img",file)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("shangchaun 上传结果 "+s);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        super.upProgress(currentSize, totalSize, progress, networkSpeed);
                        L.e("dangqian currentSize "+currentSize+" totalSize"+totalSize+" progress"+" networkSpeed"+networkSpeed);
                    }
                });
    }
}
