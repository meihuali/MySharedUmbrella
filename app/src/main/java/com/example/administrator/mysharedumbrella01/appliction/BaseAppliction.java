package com.example.administrator.mysharedumbrella01.appliction;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class BaseAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化sharSDK 这个是短信验证码
        SMSSDK.initSDK(this, "1e77fb8c8ce52", "c1a2e85e199b25c410740fac344fab4a");
        //友盟分享
        UMShareAPI.get(this);

        {

            PlatformConfig.setWeixin("wxac2d038a3a418057", "e4447ce67dd243f917ec07992c2ac6ba");
            Config.DEBUG = true;
//            PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        }
    }
}
