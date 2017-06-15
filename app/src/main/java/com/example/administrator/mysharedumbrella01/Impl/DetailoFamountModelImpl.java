package com.example.administrator.mysharedumbrella01.Impl;

import android.app.Activity;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.entivity.DetailoFamounBean;
import com.example.administrator.mysharedumbrella01.model.IsDetailoFamountModel;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class DetailoFamountModelImpl implements IsDetailoFamountModel {
    public static final int PAGE_CUNT = 3;

    @Override
    public void userDetailoFamount(final OnUserDetailoFamount detailoFamount, final Activity activity,
                                   String limt, String shangla,  DetailoFamountAdapter adapter,final int type) {
        final DetailoFamountAdapter ad;
        ad = adapter;
        String url = ConfigUtils.ZHU_YU_MING+ConfigUtils.JINERMINGXI;
        String zhanghao = ShareUtils.getString(activity,"zhanghao","");
        String sl = shangla+",10";

        OkGo.post(url)
                .params("appid",zhanghao)
//                 .params("limit","1000")
                .params("limit",sl)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("mingxi  明细 "+s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int status = jsonObject.optInt("status");
                            if(status == 1){
                                Gson gson = new Gson();
                                DetailoFamounBean dfb =  gson.fromJson(s, DetailoFamounBean.class);
                                List<DetailoFamounBean.DataBean> list =  dfb.getData();
                                detailoFamount.onCompelte(list,type);
                                ad.loadMoreComplete();
                            }else if(status==3){
                                ad.loadMoreEnd();
                            }else{
                                ad.loadMoreEnd();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(activity,"明细接口挂了···",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
