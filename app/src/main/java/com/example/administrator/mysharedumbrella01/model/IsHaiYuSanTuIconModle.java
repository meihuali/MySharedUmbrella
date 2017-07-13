package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/7/10 0010.
 * 这里是 还伞 界面 图标
 */

public interface IsHaiYuSanTuIconModle {
    /*
* 回去雨伞图标接口
* */
    void getYushanIcon(IsYuSanTuIconModel.OnYuShanIconLinsetern linsetern, String numberss);
    /*
    * 接口回调
    * */
    interface OnYuShanIconLinsetern{
        void OnComptle(Object object);
    }
}
