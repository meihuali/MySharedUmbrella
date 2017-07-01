package com.example.administrator.mysharedumbrella01.Impl;

import android.app.Activity;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.dialog.PopupWindowGuanGao;
import com.example.administrator.mysharedumbrella01.entivity.GetumbrellaBean;
import com.example.administrator.mysharedumbrella01.entivity.SaoYiSaoBean;
import com.example.administrator.mysharedumbrella01.model.IsUmbrellaStandMode;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ToolsGetAppId;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/31 0031.
 *  雨伞分布 接口的 实现类
 */

public class GetUmberllaModeImpl implements IsUmbrellaStandMode {
    private String appid;

    //    private List<GetumbrellaBean> list = new ArrayList<>();
    @Override
    public void GetUmbrellaStand(final OnGetUmbrellaLiseners liseners, final Activity activity, double longitude, double latitude) {
        //这里从网络获取数据
        // String url = "http://u.sunyie.com/Share/umbrellaindex.php";
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.FENBUTU_HOUZHUI;
        //获取设备ID
        appid = ToolsGetAppId.getinitAppId(activity);
        OkGo.post(url)
                .params("appid",appid)
                .params("longitude",longitude)
                .params("latitude",latitude)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("ssss  "+s);
                        Gson gson = new Gson();
                        try {
                            GetumbrellaBean gb = gson.fromJson(s,GetumbrellaBean.class);
                            List<GetumbrellaBean.DataBean> lists = gb.getData();
                            if (liseners != null) {
                                liseners.onComlete(lists);
                            }

                        } catch (Exception e) {
                            Toast.makeText(activity,"获取雨伞分布接口奔了",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        请求失败

                    }
                });
    }

    /*扫描二维码 借伞*/
    @Override
    public void SaoYiSao(final OnSaoYiSaoListeners listeners, String mincdeID, String phone, final Activity activity) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.SAOMIAOERWEIMA_HOUZHUI;
        final String shebeihao = mincdeID;
        final String ph = phone;
        OkGo.post(url)
                .params("appid",ph)
                .params("machineid", shebeihao)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("saoyisao1  扫一扫"+s);
                        Gson gson = new Gson();
                        SaoYiSaoBean syb = gson.fromJson(s, SaoYiSaoBean.class);
                        listeners.onComplete(syb,ph);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }


}
