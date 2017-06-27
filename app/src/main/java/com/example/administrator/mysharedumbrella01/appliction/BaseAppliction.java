package com.example.administrator.mysharedumbrella01.appliction;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class BaseAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;

        //初始化sharSDK 这个是短信验证码
        SMSSDK.initSDK(this, "1e77fb8c8ce52", "c1a2e85e199b25c410740fac344fab4a");
        //友盟分享
        UMShareAPI.get(this);

        {

            //微信 签名 正式版本的 填写到微信 后台那边去 06967289b1c05bb203e12ec822b138dd
            PlatformConfig.setWeixin("wxac2d038a3a418057", "1b00898c078f3024c4184bd2548c45c8");
            Config.DEBUG = true;
//            PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        }
    }
}
