package com.example.administrator.mysharedumbrella01.Impl;

import android.app.Activity;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.model.IsLognModel;
import com.example.administrator.mysharedumbrella01.ui.LoginActivity;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import java.lang.annotation.ElementType;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/2 0002.
 *   //接口 model  的实现类
 */

public class LoginModelImpl implements IsLognModel {
    @Override
    public void loginmode(final onLoginmodeLinistener linistener, String phone, String password, final Activity activity) {
        final PromptDialog promptDialog = new PromptDialog(activity);
        //登录发请求
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.LOGIN_HOUZHUI;
        OkGo.post(url)
                .params("phone",phone)
                .params("password",password)
                .execute(new StringCallback() {
                             @Override
                             public void onSuccess(String s, Call call, Response response) {
                                 L.e("普通账号登录登录中··· "+s);
                                 //解析
                                 Gson gson = new Gson();
                                 try {
                                     JSONObject object = new JSONObject(s);
                                     int status = object.optInt("status");
                                     if (status == 1) {
                                         LoginBean lb = gson.fromJson(s, LoginBean.class);
                                         LoginBean.DataBean logdata = lb.getData();
                                         if (linistener != null) {
                                             linistener.onComplete(lb);
                                         }
                                     } else {
                                         String statusEroor =  object.optString("data");
                                         promptDialog.showError("密码错误");
                                     }


                                 } catch (Exception e) {
//                            Toast.makeText(  ,"登录解析挂B了",Toast.LENGTH_SHORT).show();
                                 }
                             }

                             @Override
                             public void onError(Call call, Response response, Exception e) {
                                 super.onError(call, response, e);

                             }


                         }
                );
    }
}
