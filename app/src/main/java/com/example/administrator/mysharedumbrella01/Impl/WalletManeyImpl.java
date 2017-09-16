package com.example.administrator.mysharedumbrella01.Impl;

import android.app.Activity;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.entivity.ManeyBean;
import com.example.administrator.mysharedumbrella01.model.IsWalletManeyModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/15 0015.
 * //请求接口 获取钱包金额
 */

public class WalletManeyImpl implements IsWalletManeyModel {

    @Override
    public void walletMeney(final OnWalletmyListener listener, final Activity activity) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.QIANBAOJINE;
        //获取用户登录过的 账号
        String zh = ShareUtils.getString(activity,"zhanghao","");
        OkGo.post(url)
                .params("appid",zh)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //money 为余额
                        //deposit 为押金？
                        L.e("qianbao 钱包 "+s);
                        try {
                            Gson gson = new Gson();
                            ManeyBean mb = gson.fromJson(s, ManeyBean.class);
                            int status = mb.getStatus();
                            if (status == 1) {
                                ManeyBean.DataBean mbdb = mb.getData();
                                listener.onCompelte(mbdb);
                            }
                        } catch (Exception e) {
                            Toast.makeText(activity,"管理员登录获取不到钱包",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("钱包请求接口挂了· "+e.getMessage());
                    }
                });
    }
}
