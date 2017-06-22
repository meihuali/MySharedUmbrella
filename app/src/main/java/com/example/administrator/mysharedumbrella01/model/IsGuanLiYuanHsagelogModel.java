package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/6/22 0022.
 * 管理员 历史记录接口
 */

public interface IsGuanLiYuanHsagelogModel {
    /*
    * 管理员接口
    * */
    void guanliyuanjili(OnGuanliyuanjiluLisener lisener);
    /*
    * 管理历史记录接口回调
    * */
    interface OnGuanliyuanjiluLisener{
        void onComlepte();
    }
}
