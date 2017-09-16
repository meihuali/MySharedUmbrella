package com.example.administrator.mysharedumbrella01.model;

import java.io.File;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.model
 * 文件名：IsKeFuFanKuisModel
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/5 0005 18:47
 * 描述：客户反馈
 */
public interface IsKeFuFanKuisModel {

    void bugFanKui(onBugFanKuiLisener lisener, String appid, String type, File file,String body);

    interface onBugFanKuiLisener{
        void onComplte(Object object);
    }
}
