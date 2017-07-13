package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/7/8 0008.
 *  //这个接口是获取服务器·给的 主界面 雨伞图标的
 */

public interface IsYuSanTuIconModel {
    /*
    * 回去雨伞图标接口
    * */
    void getYushanIcon(OnYuShanIconLinsetern linsetern,String numberss);
    /*
    * 接口回调
    * */
    interface OnYuShanIconLinsetern{
        void OnComptle(Object object);
    }
}
