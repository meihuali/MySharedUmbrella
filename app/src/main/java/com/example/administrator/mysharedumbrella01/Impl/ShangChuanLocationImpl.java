package com.example.administrator.mysharedumbrella01.Impl;

import com.example.administrator.mysharedumbrella01.model.IsShangChuanLoncationModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class ShangChuanLocationImpl implements IsShangChuanLoncationModel {

    @Override
    public void shanghuanweizi(final onShangchuanListenset listenset, double laitudes, double longitudes ,String zhanghao,String scanResult) {
        String zh;
        String sc;
        zh = zhanghao;
        sc = scanResult;

        String url = ConfigUtils.ZHU_YU_MING + ConfigUtils.DANGQAINWEIZI;
        OkGo.post(url)
                .params("managerid",zh) //管理账号
                .params("machineid",sc) //机器ID
                .params("longitude",longitudes)
                .params("latitude",laitudes)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("guanliyuanshangchuan  管理员上传位子 " + s);

                    }
                });
        listenset.onCompelte();
    }
}
