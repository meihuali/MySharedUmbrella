package com.example.administrator.mysharedumbrella01.model;

/**
 * Created by Administrator on 2017/6/27 0027.
 * /用户反馈接口
 *
 */

public interface IsUserFeedbackModel {
    /*
    *   用户反馈信息 接口
    * */
    void userfeedback(OnUseFeedBackListerenr listerenr,String appid,
                      String machine_code,String umbrella_id,String type,String content);

    /*
    * 用户反馈信息 接口回调
    * */
    interface OnUseFeedBackListerenr{
        void onComplete(Object object);
    }
}

