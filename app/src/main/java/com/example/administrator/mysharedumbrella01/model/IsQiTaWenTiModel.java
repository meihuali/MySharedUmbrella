package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/6/29 0029.
 * //用户反馈第4个模块 其他问题模块
 */

public interface IsQiTaWenTiModel {
    /*
    *  其他问题 接口
    * */
    void qitawent(OnqitawentListerensst listerensst,String appid,
                  String machine_code,String umbrella_id,String type,String content);

    /*
    * 其他问题模块 接口回调
    * */
    interface OnqitawentListerensst{
        void onCmplete(Object object);
    }
}
