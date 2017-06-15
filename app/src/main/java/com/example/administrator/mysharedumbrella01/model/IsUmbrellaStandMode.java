package com.example.administrator.mysharedumbrella01.model;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.mysharedumbrella01.entivity.GetumbrellaBean;
import com.example.administrator.mysharedumbrella01.entivity.SaoYiSaoBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 * 获取雨伞架子分布图
 */

public interface IsUmbrellaStandMode {
    //获取雨伞接口
    void GetUmbrellaStand(OnGetUmbrellaLiseners liseners,Activity activity,double longitude,double latitude );

    //获取雨伞接口回调
    public interface OnGetUmbrellaLiseners{
        void onComlete(List<GetumbrellaBean.DataBean> list);
    }

    //扫描二维码接口
    void SaoYiSao(OnSaoYiSaoListeners listeners,String mincdeID,String phone);
    //扫一扫接口回调
     interface OnSaoYiSaoListeners{
        void onComplete(SaoYiSaoBean syb ,String mincdeID);
    }
}
