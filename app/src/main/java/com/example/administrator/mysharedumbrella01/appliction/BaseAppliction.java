package com.example.administrator.mysharedumbrella01.appliction;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.smssdk.SMSSDK;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class BaseAppliction extends Application {
    //声明一个全局的变量
    public static BaseAppliction intstens;
    /*
    * 首先在application 里面 声明一个静态 map集合用来管理activit
    * */

    public static Map<String, Activity> destoryMap = new HashMap<String, Activity>();
    @Override
    public void onCreate() {
        super.onCreate();
        //安卓 7.0 以上 拍照启动相机需要
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        intstens = this;
        // 下面这一句 是 腾讯bugliy
        CrashReport.initCrashReport(getApplicationContext(), "4bac73bec2", false);
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
            PlatformConfig.setQQZone("1106175515", "r5B1fp4IP2a98VcS");
            Config.DEBUG = true;
        }
    }

    public static BaseAppliction getIntstens() {

        return intstens;
    }


    /**
     * 添加到销毁队列 （就是要销毁哪个activity 就在哪个activity的oncrteae 里面调用这个方法）
     *
     * @param activity 要销毁的activity
     */
    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 销毁指定Activity   你要在 A finish 的时候 同时销毁B 这个类·的时候·那就掉这个方法
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            if (key.equals(activityName)) {
                destoryMap.get(key).finish();
            }
        }
    }

}
