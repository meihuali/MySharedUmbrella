package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/7/26 0026.
 *   客户反馈接口
 */

public interface IsKeFuFanKuiModel {
    /*
    * 客户返回接口
    * */
    void kefufankui(onKefufankuiLinerenst linerenst);
    /*
    * 接口回调
    * */
    interface onKefufankuiLinerenst{
        void onComplte(Object object);
    }
}
