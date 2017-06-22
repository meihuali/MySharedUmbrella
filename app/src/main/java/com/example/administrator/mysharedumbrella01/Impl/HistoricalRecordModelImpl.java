package com.example.administrator.mysharedumbrella01.Impl;

import android.app.Activity;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.entivity.HistoryBean;
import com.example.administrator.mysharedumbrella01.model.HistoricalRecordModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ToolsGetAppId;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class HistoricalRecordModelImpl implements HistoricalRecordModel {
    @Override
    public void historicalrecord(final OnHistoricalrecordListener listener, final Activity activity,int isroot,String phone,String ptuser) {
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.HUOQULISHILI;
        String appid = ToolsGetAppId.getinitAppId(activity);
        //1表示 是管理员
        if (isroot == 1) {
            OkGo.post(url)
                    .params("appid", phone)
                    .params("limit", "0,1000")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            L.e("lishijili 管理历史记录 " + s);
                            //这里直接就 解析了
                            Gson gson = new Gson();
                            try {
                                HistoryBean hb = gson.fromJson(s, HistoryBean.class);
                                List<HistoryBean.DataBean> list = hb.getData();
                                if (listener != null) {
                                    listener.onComplete(list);
                                }
                            } catch (Exception e) {
                                Toast.makeText(activity, "历史记录接口挂了", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);

                        }
                    });
        } else { //否则就是普通用户登录
            OkGo.post(url)
                    .params("appid",ptuser)
                    .params("limit","0,1000")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            L.e("lishijili 历史记录 "+s);
                            //这里直接就 解析了
                            Gson gson = new Gson();
                            try {
                                HistoryBean hb = gson.fromJson(s, HistoryBean.class);
                                List<HistoryBean.DataBean>list =  hb.getData();
                                if (listener != null) {
                                    listener.onComplete(list);
                                }
                            } catch (Exception e) {
                                Toast.makeText(activity,"历史记录接口挂了",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);

                        }
                    });

        }




    }


}
