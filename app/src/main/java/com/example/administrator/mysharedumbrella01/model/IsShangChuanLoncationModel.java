package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public interface IsShangChuanLoncationModel {
    //上传地理位置
    void shanghuanweizi(onShangchuanListenset listenset,double laitudes, double longitudes ,String zhanghao,String scanResult);
    //上传地理位置 接口回调
    interface onShangchuanListenset{
        void onCompelte();
    }
}
