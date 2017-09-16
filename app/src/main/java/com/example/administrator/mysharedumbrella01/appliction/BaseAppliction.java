package com.example.administrator.mysharedumbrella01.appliction;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;


import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingCrop;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.example.administrator.mysharedumbrella01.service.GetuiIntentService;
import com.example.administrator.mysharedumbrella01.service.GetuiPushService;
import com.example.administrator.mysharedumbrella01.utils.BoxingGlideLoader;
import com.hss01248.dialog.MyActyManager;
import com.hss01248.dialog.StyledDialog;
import com.igexin.sdk.PushManager;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
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

import static com.tencent.bugly.Bugly.applicationContext;

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
    private int bendiVersionCode;
    private String bendiVersionName;

    @Override
    public void onCreate() {
        super.onCreate();
        //dialogUtils
        StyledDialog.init(getApplicationContext());
        registCallback();

        //图片裁减库 之类的
        BoxingMediaLoader.getInstance().init(new BoxingGlideLoader()); // 需要实现IBoxingMediaLoader
        //啊里热更新
        aliyunUpdataApp();
        //极光推送
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
        //个推
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), GetuiPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GetuiIntentService.class);

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



    /*
    * 啊里热更新配置
    * */
    private void aliyunUpdataApp() {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
            //获取本地版本号
            bendiVersionCode = info.versionCode;
            //获取本地版本的名字
            bendiVersionName = info.versionName;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"获取当前版本信息失败",Toast.LENGTH_SHORT).show();
        }

        // initialize最好放在attachBaseContext最前面
        SophixManager.getInstance().setContext(this)
                .setAppVersion(bendiVersionName)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀，以此加快应用补丁
                            // 建议调用killProcessSafely，详见1.3.2.3
                            // SophixManager.getInstance().killProcessSafely();
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
// queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
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

    /*
    * 配置dialogUtils 用的
    * */
    private void registCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActyManager.getInstance().setCurrentActivity(activity);


            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }



}
