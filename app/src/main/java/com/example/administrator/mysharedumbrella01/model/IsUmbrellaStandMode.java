package com.example.administrator.mysharedumbrella01.model;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.mysharedumbrella01.entivity.GetumbrellaBean;
import com.example.administrator.mysharedumbrella01.entivity.GetumbrellaBean_two;
import com.example.administrator.mysharedumbrella01.entivity.SaoYiSaoBean;
import com.example.administrator.mysharedumbrella01.entivity.SaoYiSaoErrorBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 * 获取雨伞架子分布图
 */

public interface IsUmbrellaStandMode {
    //获取雨伞接口
    void GetUmbrellaStand(OnGetUmbrellaLiseners liseners,Activity activity,double longitude,double latitude);

    //获取雨伞接口回调
     interface OnGetUmbrellaLiseners{

        void onComlete(List<GetumbrellaBean_two.DataBean> list);
    }

    /*
    * 扫描二维码接口
    * */
    void SaoYiSao(OnSaoYiSaoListeners listeners,String mincdeID,String phone,Activity activity,String app_type);
      /*
      * 扫一扫接口回调
      * */
     interface OnSaoYiSaoListeners{
        //这个是 扫描成功 的回调
        void onComplete(Object syb ,String mincdeID);

          //这个是扫描失败的回调
//          void onCompleteErrer(SaoYiSaoErrorBean syberror);
    }
}
