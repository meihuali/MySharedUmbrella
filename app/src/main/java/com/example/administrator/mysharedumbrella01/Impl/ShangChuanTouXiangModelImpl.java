package com.example.administrator.mysharedumbrella01.Impl;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.entivity.ShangChuanTouXiangBean;
import com.example.administrator.mysharedumbrella01.model.IsShangchuanPhotoModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/19 0019.
 *  上传头像的 实现类·
 */

public class ShangChuanTouXiangModelImpl implements IsShangchuanPhotoModel {
    @Override
    public void ShangChuanTouXiang(final OnShangChuanListener listener, File file, Activity activity) {
        final PromptDialog pd = new PromptDialog(activity);
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
                        Gson gson = new Gson();
                        ShangChuanTouXiangBean shangchuanPhone =  gson.fromJson(s, ShangChuanTouXiangBean.class);
                        listener.onComlete(shangchuanPhone);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("shangchaun 上传结果 "+"请求失败");

                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        super.upProgress(currentSize, totalSize, progress, networkSpeed);
                        L.e("dangqian currentSize "+currentSize+" totalSize"+totalSize+" progress"+" networkSpeed"+networkSpeed);
                    }
                });
    }
}
