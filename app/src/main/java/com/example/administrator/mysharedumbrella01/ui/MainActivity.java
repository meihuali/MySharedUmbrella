package com.example.administrator.mysharedumbrella01.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.SaoYiSao.ScannerActivity;
import com.example.administrator.mysharedumbrella01.dialog.KaiSuohoudeGuanggao;
import com.example.administrator.mysharedumbrella01.dialog.PopupWindowGuanGao;
import com.example.administrator.mysharedumbrella01.dialog.UpdataDialog;
import com.example.administrator.mysharedumbrella01.dialog.ZhuYeGuangGao;
import com.example.administrator.mysharedumbrella01.entivity.GetumbrellaBean;
import com.example.administrator.mysharedumbrella01.entivity.SaoYiSaoBean;
import com.example.administrator.mysharedumbrella01.entivity.UpdataBean;
import com.example.administrator.mysharedumbrella01.entivity.YuSanIconBean;
import com.example.administrator.mysharedumbrella01.peresenet.HaiYuSanTuIconPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.UmbrellaPresenet;
import com.example.administrator.mysharedumbrella01.peresenet.UpdataAppPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.UserGetYuSanStatusPeresent;
import com.example.administrator.mysharedumbrella01.peresenet.YuSanTuIconPerserent;
import com.example.administrator.mysharedumbrella01.transition.Utilss;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.dialog.MyPopuopWindowsRigth;
import com.example.administrator.mysharedumbrella01.utils.MyToast;
import com.example.administrator.mysharedumbrella01.utils.NetWorkUtils;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ZhuoBiaoZhuanHuan;
import com.example.administrator.mysharedumbrella01.view.IsHaiYuSanJiemianIconView;
import com.example.administrator.mysharedumbrella01.view.IsUmbrellaView;
import com.example.administrator.mysharedumbrella01.view.IsUpdataAPPView;
import com.example.administrator.mysharedumbrella01.view.IsUserGetYuSanStatusView;
import com.example.administrator.mysharedumbrella01.view.IsYuSanTuIconView;
import com.gyf.barlibrary.ImmersionBar;
import com.mylhyl.zxing.scanner.common.Intents;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;


import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.ui
 * 文件名：ShowBaidu
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/4/26 0026 下午 3:25
 * 描述：TODO  123
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AMap.OnMarkerClickListener, IsUmbrellaView, IsUpdataAPPView,
        Runnable, IsYuSanTuIconView, IsHaiYuSanJiemianIconView,
        AMapLocationListener,AMap.InfoWindowAdapter, IsUserGetYuSanStatusView,SpeechSynthesizerListener {
    private AMap aMap;
    private MapView mapView;
    private Polyline polyline;
    private MarkerOptions markerOption;
    private List<LatLng> list1 = new ArrayList<>();


    AMapLocationClient mlocationClient;

    List<LatLng> latLngs = new ArrayList<LatLng>();
    private double jingdu;
    private double weidu;
    private String shijian;
    private String startshijian;
    private String stopshijian;
    private LatLng latLng;
    private Marker marker;
    private double J;
    private double W;
    private StringBuilder nian;
    private String qishishijian;
    private String jiesushijian;
    private LatLng stoplatLng;
    private ImageView back_img;
    private int starts;
    private int stops;
    private String id;
    private AMap.OnMarkerClickListener markerClickListener;
    /*声明一个集合用来封装有几个marke*/

    private Marker marker2;
    private Marker marker3;
    private LatLng ll;
    private LatLng StratlatLng;
    private LatLng stoplatLngss;
    private String xianlushij;
    long db = 80000;
    private String xianlushijs;

    private String shengyu;
    private ImageView image_backs;
    private TextView tv_adds;
    private Button btn_scatc;
    private double litude;
    private double latitude;
    private String umbrellanubers;
    // private UmbrellaPresenet up;
    private String scanResult;
    private Button btn_haisan, btn_jiesan;
    private Button btn_scatcs;
    private String vacancynumber;
    //定时器
    private final Timer timer = new Timer();
    private TimerTask task, task2;
    private double laitudes;
    private double longitudes;
    private int statusSaoYiSao;
    private int a = 0;

    private PopupWindowGuanGao pwgg;
    // 扫一扫相关 颜色  如果不赋值的话· 扫描上下滚动的就是绿色这里默认 赋值为 支付宝 那种网格的
    private int laserMode = ScannerActivity.EXTRA_LASER_LINE_MODE_1;
    private double latitudes;
    private LatLng latLng_xianludiana;
    //右下角的 用户反馈按钮
    private CircleImageView image_kehu;
    private int bendiVersionCode;
    private String bendiVersionName;
    private int qiangzhiUdata;
    private Thread downLoadThread;
    private String path;
    private int serviceVerSionCode;
    private String urlapk;
    private PromptDialog promptDialog;
    private LatLng latLng_xianludian;
    private int types = 1; //默认等于1 代表 雨伞分布的请求 点击还伞等于2 的时候表示雨伞架子的请求
    private String yusanIconUrl;
    private YuSanTuIconPerserent tyip;
    private CircleImageView image_shuaxin;
    private int typeHaisan = 1;
    private HaiYuSanTuIconPerserent haisanIcon;
    private String haisanURL;
    private boolean FINISH;
    MyLocationStyle myLocationStyle;
    /* 从新构造开始 声明的变量 */
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    public static final int MY_PERMISSIONS_MAP = 0;
    public static final int REQUEST_CODE_SETTING = 300;
    private LatLng myLatLng;
    private Marker myMarker;
    private Bitmap bitmaps;
    private String citys;
    private String address;
    /**************************百度语音合成客户端******************************/

    private SpeechSynthesizer mSpeechSynthesizer;
    private String mSampleDirPath;
    private static final String SAMPLE_DIR_NAME = "baiduTTS";
    private static final String SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female.dat";
    private static final String SPEECH_MALE_MODEL_NAME = "bd_etts_speech_male.dat";
    private static final String TEXT_MODEL_NAME = "bd_etts_text.dat";
    private static final String LICENSE_FILE_NAME = "temp_license";
    private static final String ENGLISH_SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female_en.dat";
    private static final String ENGLISH_SPEECH_MALE_MODEL_NAME = "bd_etts_speech_male_en.dat";
    private static final String ENGLISH_TEXT_MODEL_NAME = "bd_etts_text_en.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化下dialog
        promptDialog = new PromptDialog(this);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        init();
        //这里请求·还伞 图标icon  跟 借伞图标 ICOn
        HttpQuestIcon();
        //初始化地图 mapview
        mapView = (MapView) findViewById(R.id.map);
        initLocation();
        initPermission();
        initMap();
        // 此方法必须重写
        mapView.onCreate(savedInstanceState);
        //下面两个方法是百度语音合成
        initialEnv();
        startTTS();
    }


    /*
* //初始化定位参数
* */
    private void initLocation() {

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        //mLocationOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //可选，设置是否使用缓存定位，默认为true
        //mLocationOption.setLocationCacheEnable(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    /*
* 动态授权 权限
* */
    private void initPermission() {
        // 申请多个权限。
        AndPermission.with(this)
                .requestCode(MY_PERMISSIONS_MAP)
                .permission(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .callback(permissionListener)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(MainActivity.this, rationale)
                                .show();
                    }
                })
                .start();
    }

    /**
     * 初始化AMap对象
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);
            //自定义地图
            setMapCustomStyleFile(this);
            aMap.setMapCustomEnable(true);
            setUpMap();
        }
    }

    /**
     * 设置一些amap的属性
     */

    private void setUpMap() {
        //初始化 UiSettings
        UiSettings uiSettings = aMap.getUiSettings();
        // 设置默认定位按钮是否显示
        uiSettings.setMyLocationButtonEnabled(true);
        // 设置缩放按钮是否显示
        uiSettings.setZoomControlsEnabled(true);
        //缩放手势
        uiSettings.setZoomGesturesEnabled(true);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(false);
        // 设置指南针
        aMap.getUiSettings().setCompassEnabled(true);
        // 设置右下角的按钮缩放
        aMap.getUiSettings().setZoomControlsEnabled(false);
        // 设置自定义InfoWindow样式
        aMap.setInfoWindowAdapter(this);
    }

    private void setMapCustomStyleFile(Context context) {
        String styleName = "mystyle_sdk_1502163648_0100.data";
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String filePath = null;
        try {
            inputStream = context.getAssets().open(styleName);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            filePath = context.getFilesDir().getAbsolutePath();
            File file = new File(filePath + "/" + styleName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(b);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();

                if (outputStream != null)
                    outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        aMap.setCustomMapStylePath(filePath + "/" + styleName);

        aMap.showMapText(true);

    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (NetWorkUtils.isNetworkConnected(getApplicationContext())) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    Toast.makeText(getApplicationContext(), "定位成功！", Toast.LENGTH_SHORT).show();
                    //获取市区信息
                    citys =  amapLocation.getCity();
                    //获取地址信息
                    address = amapLocation.getAddress();
                    myLatLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                    //点击定位按钮 能够将地图的中心移动到定位点
                    //aMap.moveCamera(CameraUpdateFactory.changeLatLng(myLatLng));
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 16));
                    //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
                    //aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(myLatLng, 18, 30, 0)));
                    // 自定义定位成功后的小圆点
                    if (myMarker != null) {
                        myMarker.remove();
                        myMarker.destroy();
                    }
                    myMarker = aMap.addMarker(new MarkerOptions().position(myLatLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.location_mark)));
                    //停止定位
                    stopLocation();
                    //定位成功弹出 主页广告（商业广告）
                    initShowZhuYeGuangGao();

                    // 这里请求网络获取雨伞分布
                    UmbrellaPresenet up = new UmbrellaPresenet(this);
                    up.fech(MainActivity.this, amapLocation.getLatitude(), amapLocation.getLongitude(), types);

                } else {
                    Toast.makeText(getApplicationContext(), "定位失败！ErrorCode()不等于0", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "定位失败！amapLocation对象为空！", Toast.LENGTH_SHORT).show();
            }
        } else {
            MyToast.toast(getApplicationContext(),"您当前没有网络，定位失败！");
        }

    }



    /*========================初始化一些控件=====================================*/

    private void init() {
        image_shuaxin = (CircleImageView) findViewById(R.id.image_shuaxin);
        image_shuaxin.setOnClickListener(this);
        image_kehu = (CircleImageView) findViewById(R.id.image_kehu);
        image_kehu.setOnClickListener(this);
        //借伞
        btn_jiesan = (Button) findViewById(R.id.btn_jiesan);
        btn_jiesan.setOnClickListener(this);
        //还伞按钮
        btn_haisan = (Button) findViewById(R.id.btn_haisan);
        btn_haisan.setOnClickListener(this);
        btn_scatcs = (Button) findViewById(R.id.btn_scatcs);
        btn_scatcs.setOnClickListener(this);

        //初始化控件以及地图
        image_backs = (ImageView) findViewById(R.id.image_backs);
        image_backs.setOnClickListener(this);
        btn_scatc = (Button) findViewById(R.id.btn_scatc);
        btn_scatc.setOnClickListener(this);
        tv_adds = (TextView) findViewById(R.id.tv_adds);
        tv_adds.setOnClickListener(this);
    }


    /*
    * 更新app 用的
    * */
    private void initShowUpdataApp() {
        UpdataAppPerserent uap = new UpdataAppPerserent(this);
        uap.fach();
    }

    /*
    *  更新APP 后 的接口回调
    *  */
    @Override
    public void showUpdataResltout(UpdataBean updataBean) {
        //修复过哪些内容
        int status = updataBean.getStatus();
        if (status == 1) {

            UpdataBean.DataBean ubdb = updataBean.getData();
            //是否为强制更新 0为不强制更新 1为强制更新
            String qiangzhi = ubdb.getQiangzhi();
            //新的apk 地址
            urlapk = ubdb.getUrl();
            //服务器版本APK versionCode
            String serviceVersionCode = ubdb.getVersioncode();
            serviceVerSionCode = Integer.parseInt(serviceVersionCode);
            //服务器版本的 apk versionName
            String servideVersionName = ubdb.getVersionName();
        }


        /*============下面是获取本地 APK 版本 ================= */
        try {
            PackageManager pm = getPackageManager();
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
            //获取本地版本号
            bendiVersionCode = info.versionCode;
            //获取本地版本的名字
            bendiVersionName = info.versionName;

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "更新当前版本号获取失败", Toast.LENGTH_SHORT).show();
        }
        /*
        * 判断服务器版本号是否大于本地版本号
        *  qiangzhiUdata 为0 不强制更新, 不为0则强制更新 隐藏取消按钮
        * */

        if (serviceVerSionCode > bendiVersionCode) {
            UpdataDialog.show(this, "修复了一些bug", "共享雨伞震撼上线", qiangzhiUdata, false, null);
            UpdataDialog.setOutside(false);
            //注册 取消按钮的 监听
            UpdataDialog.btn_cancel.setOnClickListener(this);
            //注册 确认按钮的 监听
            UpdataDialog.btn_confirm.setOnClickListener(this);
        }

    }


//    点击返回按钮

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_backs:
                marker = null;
                String zh = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                if (!TextUtils.isEmpty(zh)) {
                    //laitudes,longitudes 当前经纬度
                    Intent intent = new Intent(this, SettingsYusanActivity.class);
                    intent.putExtra("laitudes", laitudes);
                    intent.putExtra("longitudes", longitudes);
                  //  startActivity(intent);
                    //启动过场动画
                    Utilss.transitionTo(intent,MainActivity.this,image_backs);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.tv_adds:
                MyPopuopWindowsRigth pwr = new MyPopuopWindowsRigth(this, aMap);
                pwr.showPopupWindow(R.id.tv_adds);
                break;
            case R.id.btn_scatc:
                initViewes();
                break;
            //点击还伞
            case R.id.btn_haisan:
                a = 1;
                aMap.clear();
                //点击还伞再次设置一下·用户当前位子的覆盖物
                myMarker = aMap.addMarker(new MarkerOptions().position(myLatLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.location_mark)));
                //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                //然后把types赋值为2  表示·还伞的字段·用来区分mark 图标
                types = 2;
                UmbrellaPresenet up = new UmbrellaPresenet(this);
                up.fech(MainActivity.this, laitudes, longitudes, types);
                btn_haisan.setBackground(ContextCompat.getDrawable(this, R.drawable.image_conent));
                btn_jiesan.setBackgroundColor(getResources().getColor(R.color.zhutiyanse));
//                //点还伞后 请求 伞架子的 图标
//                tyip.fach("11");
                //加一个字段为typeHaisan 用来刷新
                typeHaisan = 2;
                break;
            //点击借伞
            case R.id.btn_jiesan:
                a = 2;
                aMap.clear();
                //点击还伞再次设置一下·用户当前位子的覆盖物
                myMarker = aMap.addMarker(new MarkerOptions().position(myLatLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.location_mark)));

                //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                //给types 赋值为 1 表示· 雨伞的图标
                types = 1;
                UmbrellaPresenet upIcons = new UmbrellaPresenet(this);
                upIcons.fech(MainActivity.this, laitudes, longitudes, types);
                //设置背景
                btn_jiesan.setBackground(ContextCompat.getDrawable(this, R.drawable.image_conent));
                btn_haisan.setBackgroundColor(getResources().getColor(R.color.zhutiyanse));
                //加一个字段为typeHaisan 用来刷新
                typeHaisan = 1;
                break;
            //右下角的客户返回 按钮
            case R.id.image_kehu:
//                PopupWindowBotoom pwb = new PopupWindowBotoom(this);
//                pwb.showPopupWindow();
                Intent intentkehu = new Intent(getApplicationContext(), KeHuFanKuiActivity.class);
                //启动过场动画
                Utilss.transitionTo(intentkehu,MainActivity.this,image_kehu);
                break;
            //更新APP 弹出的dialog 取消按钮
            case R.id.btn_cancel:
                if (qiangzhiUdata == 0) {
                    UpdataDialog.dialogcancle();
                } else {
                    return;
                }
                break;
            //左下角的 刷新按钮
            case R.id.image_shuaxin:
                if (typeHaisan == 1) {
                    a = 2;
                    aMap.clear();
                    //点击还伞再次设置一下·用户当前位子的覆盖物
                    myMarker = aMap.addMarker(new MarkerOptions().position(myLatLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.location_mark)));
                    //然后再次启动定位一下
                    startLocation();
                    //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                    //给types 赋值为 1 表示· 雨伞的图标
                    types = 1;
                    UmbrellaPresenet upIcon = new UmbrellaPresenet(this);
                    upIcon.fech(MainActivity.this, laitudes, longitudes, types);
                    //点击借伞后请求网络 图标
//                   tyip.fach("10");

                } else {
                    a = 1;
                    aMap.clear();
                    //点击还伞再次设置一下·用户当前位子的覆盖物
                    myMarker = aMap.addMarker(new MarkerOptions().position(myLatLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.location_mark)));
                    //然后再次启动定位一下
                    startLocation();
                    //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                    //然后把types赋值为2  表示·还伞的字段·用来区分mark 图标
                    types = 2;
                    UmbrellaPresenet upsanjiazi = new UmbrellaPresenet(this);
                    upsanjiazi.fech(MainActivity.this, laitudes, longitudes, types);
                    //点还伞后 请求 伞架子的 图标
//                    tyip.fach("11");
                }

                break;
            //更新APP 弹出的dialog 确认按钮
            case R.id.btn_confirm:
                //下面这几句话是调用系统自带的浏览器打开新的APK 下载地址
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse(urlapk);
                intent.setData(url);
                startActivity(intent);
                //隐藏dialog
                UpdataDialog.dialogcancle();
                break;
        }
    }


    private int status;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UpdataDialog.round_flikerbar.setProgress(msg.arg1);
            if (msg.arg1 == 100) {
                UpdataDialog.round_flikerbar.finishLoad();
            }
        }
    };


    @Override
    public void run() {
        try {
            while (!downLoadThread.isInterrupted()) {
                float progress = UpdataDialog.round_flikerbar.getProgress();
                progress += 2;
                Thread.sleep(200);
                Message message = handler.obtainMessage();
                message.arg1 = (int) progress;
                handler.sendMessage(message);
                if (progress == 100) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*
* 显示主要广告
* */
    private void initShowZhuYeGuangGao() {
        ZhuYeGuangGao zygg = new ZhuYeGuangGao(this);
        zygg.showPopupWindow();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        String id = marker.getId();
        L.e("marker " + id);

        if (aMap != null) {
            if (marker.equals(marker2)) {
//				jumpPoint(marker);
            } else if (marker.equals(marker3)) {
//                growInto(marker);
            }
        }
        return false;
    }



    /*点击线条中间覆盖物显示气泡*/
    private void addMarkers(final LatLng latLng, final String umbrellanubers) {
        //取出借伞图标 的路径地址
        String jiesanUrl =  ShareUtils.getString(getApplicationContext(),"jiesantubiao","");
        Glide.with(MainActivity.this)
                .load(jiesanUrl)
                // 加载网络中的静态图片
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        //在这加个图片处理的方法
//                        Bitmap bit = ShareUtils.compressScale(resource,80f,80f);
                        // 这里将bitmap对象图片绘制成圆形
//                        BitmapToRound_Util round_Util = new BitmapToRound_Util();
//                        bitmaps = round_Util.toRoundBitmap(bit);
                        //绘制覆盖物
                        aMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("雨伞个数："+umbrellanubers)
                                // .snippet(umbrellanubers)
                                .anchor(0.5f, 0.5f)
                                .icon(BitmapDescriptorFactory.fromBitmap(resource)));
                    }
                });

    }

    /*点击线条中间覆盖物显示气泡*/
    private void addMarkerss(final LatLng latLng, final String vacancynumber) {
        //之类是还伞 获取到的 图标 路径
        String haisanURLS = ShareUtils.getString(getApplicationContext(), "haisantubiao", "");
        Glide.with(MainActivity.this)
                .load(haisanURLS)
                // 加载网络中的静态图片
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        //在这加个图片处理的方法
//                        Bitmap bit = ShareUtils.compressScale(resource,80f,80f);
                        // 这里将bitmap对象图片绘制成圆形
//                        BitmapToRound_Util round_Util = new BitmapToRound_Util();
//                        bitmaps = round_Util.toRoundBitmap(bit);
                        //绘制覆盖物
                        aMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("空位个数："+vacancynumber)
                                // .snippet(vacancynumber)
                                .anchor(0.5f, 0.5f)
                                .icon(BitmapDescriptorFactory.fromBitmap(resource)));
                    }
                });
    }

    /*===================扫描二维码============================================*/
 /*启动扫描二维码*/
    private void initViewes() {

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            ScannerActivity.gotoActivity(MainActivity.this,
                    true, laserMode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
                if (data != null) {
                    String stringExtra = data.getStringExtra(Intents.Scan.RESULT);
                    Log.e("扫描结果 ", "" + stringExtra);

                    String zhanghao = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
//                    String mima = ShareUtils.getString(getApplicationContext(), "mima", "");
                    if (!TextUtils.isEmpty(zhanghao)) {
                        //扫描请求借伞
                        UmbrellaPresenet up = new UmbrellaPresenet(this);
                        up.binds(stringExtra, zhanghao, this);
                        //扫描成功后再次请求下网络获取雨伞个数
                        up.fech(MainActivity.this, laitudes, longitudes, types);
//                //定时刷新
//                        timetask(laitudes, longitudes,2);
                        //开锁中的广告
                        pwgg = new PopupWindowGuanGao(this, stringExtra);
                        pwgg.showPopupWindow();

                    } else {
                        //Toast.makeText(getApplicationContext(), "请登录", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    }
                }
            }
        }
    }



    /*
    * 借伞的接口回调
    *
    *       当stauts为1时，app显示开锁成功
           当status为2时，app显示押金不足
           当status为3时，app显示余额不足
           当status为4时，app显示请求超时，开锁失败
           当status为5时，app显示无伞可借
           当status为6或者7时，app重新发起开锁请求
           当status为11时，app显示借伞成功
           当status为0时，app显示通信错误
    *
    * */
    @Override
    public void showSaoYiSao(Object syb, String mincdeID, String phone) {
        SaoYiSaoBean object = (SaoYiSaoBean) syb;
        statusSaoYiSao = object.getStatus();
        String data = object.getData();
        if (statusSaoYiSao == 11) {
            //promptDialog.showSuccess("借伞成功");
            mSpeechSynthesizer.speak("您已借伞成功");
            //停止广告上的进度条
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
        } else if (statusSaoYiSao == 1) {
            mSpeechSynthesizer.speak("您已开锁成功，请取走雨伞");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

            //当statusSaoYiSao等于1 的时候 去请求另外一个接口判断用户是否有取消雨伞
            UserGetYuSanStatusPeresent qusan = new UserGetYuSanStatusPeresent(this);
            //这里截取 前面这段字符串 222222222222222202,555555555555555555
            String stely =  data.substring(0,data.lastIndexOf(","));
            qusan.userGetYuSan(stely);

            //这里截取 后面的这段字符串 222222222222222202,555555555555555555
            String enddata =  data.substring(data.lastIndexOf(",",data.length()));

            //雨伞分布的 坐标 有多少个雨伞
            aMap.clear();
            //这里再次设置覆盖物
            myMarker = aMap.addMarker(new MarkerOptions().position(myLatLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.location_mark)));
            UmbrellaPresenet up = new UmbrellaPresenet(this);
            up.fech(MainActivity.this, laitudes, longitudes, types);
            //开锁成功 又开启一个popupwindow
            KaiSuohoudeGuanggao zy = new KaiSuohoudeGuanggao(this, enddata);
            zy.showPopupWindow();

        } else if (statusSaoYiSao == 2) {
           // promptDialog.showSuccess("押金不足");
            mSpeechSynthesizer.speak("您的押金不足");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

        } else if (statusSaoYiSao == 3) {
           // promptDialog.showSuccess("余额不足");
            mSpeechSynthesizer.speak("您的余额不足");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

        } else if (statusSaoYiSao == 4) {
           // promptDialog.showSuccess("请求超时，开锁失败");
            mSpeechSynthesizer.speak("请求超时，开锁失败");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

        } else if (statusSaoYiSao == 5) {
          //  promptDialog.showSuccess("无伞可借");
            mSpeechSynthesizer.speak("您已无伞可借了");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

        } else if (statusSaoYiSao == 6) {
          //  promptDialog.showSuccess("重新发起开锁请求");
            mSpeechSynthesizer.speak("重新发起开锁请求");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

        } else if (statusSaoYiSao == 7) {
          //  promptDialog.showSuccess("重新发起开锁请求");
            mSpeechSynthesizer.speak("重新发起开锁请求");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

        } else if (statusSaoYiSao == 0) {
          //  promptDialog.showSuccess("通信错误");
            mSpeechSynthesizer.speak("通信错误");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

        }



    }


    /*获取 雨伞分布回调·从这里开始 ·已经开始 学习 了 MVP 架构设计模式了*/
    @Override
    public void showUmbrella(List<GetumbrellaBean.DataBean> list, int types) {
        if (types == 1) {
            int sizes = list.size();
            L.e("sizes" + sizes);
            for (int i = 0; i < list.size(); i++) {
                String longitude = list.get(i).getLongitude();
                String latitude = list.get(i).getLatitude();
                //剩余 雨伞个数
                int umbrellanuber = list.get(i).getUmbrellanumber();
                umbrellanubers = String.valueOf(umbrellanuber);
                //剩余空位个数
                vacancynumber = list.get(i).getVacancynumber();

                longitudes = Double.parseDouble(longitude);
                latitudes = Double.parseDouble(latitude);
                latLng_xianludiana = new LatLng(latitudes, longitudes);
                // 坐标转换
                latLng_xianludian = ZhuoBiaoZhuanHuan.transformFromWGSToGCJ(latLng_xianludiana);
                //雨伞架子分布个数
                addMarkers(latLng_xianludian, umbrellanubers);
                if (a == 1) { //a== 1表示 还伞 剩余空位个数
                    addMarkerss(latLng_xianludian, vacancynumber);

                }
            }
        } else {  // else 表示还伞 显示 marek
            int sizes = list.size();
            L.e("sizes" + sizes);
            for (int i = 0; i < list.size(); i++) {
                String longitude = list.get(i).getLongitude();
                String latitude = list.get(i).getLatitude();
                //剩余 雨伞个数
                int umbrellanuber = list.get(i).getUmbrellanumber();
                umbrellanubers = String.valueOf(umbrellanuber);
                //剩余空位个数
                vacancynumber = list.get(i).getVacancynumber();

                longitudes = Double.parseDouble(longitude);
                latitudes = Double.parseDouble(latitude);
                latLng_xianludiana = new LatLng(latitudes, longitudes);
                // 坐标转换
                latLng_xianludian = ZhuoBiaoZhuanHuan.transformFromWGSToGCJ(latLng_xianludiana);
                //雨伞架子分布个数
//                addMarkers(latLng_xianludian, umbrellanubers);
                if (a == 1) { //a== 1表示 还伞 剩余空位个数
                    if (!vacancynumber.equals("0")) {
                        addMarkerss(latLng_xianludian, vacancynumber);
                    }
                }
            }
        }

    }

    /*
    *  该方法是定位成功后 网络请求 从服务器获取到 返回的雨伞图标
    * */
    @Override
    public void ShowYuSanIcon(Object object) {
        YuSanIconBean ysib = (YuSanIconBean) object;
        int status = ysib.getStatus();
        if (status == 1) {
            yusanIconUrl = ysib.getData();
            String jiesanurl  = ConfigUtils.YUSANTUBIAO+yusanIconUrl;
            ShareUtils.putString(getApplicationContext(), "jiesantubiao", jiesanurl);
            L.e("借伞图标ICON " + yusanIconUrl);
        } else {
            promptDialog.showError("获取雨伞图标失败");
        }
    }

    /*
    *  该回调是还伞界面的 雨伞图标
    * */
    @Override
    public void ShowHaiSanIcon(Object object) {
        YuSanIconBean haisanIcon = (YuSanIconBean) object;
        int status = haisanIcon.getStatus();
        if (status == 1) {
            haisanURL = haisanIcon.getData();
            String haisanurl = ConfigUtils.YUSANTUBIAO+haisanURL;
            ShareUtils.putString(getApplicationContext(), "haisantubiao",  haisanurl);
            L.e("还伞图标ICON " + haisanURL);
        }
    }


    private void HttpQuestIcon() {
        //这里定位成功后首先获取·借伞 界面的雨伞图标
        tyip = new YuSanTuIconPerserent(MainActivity.this);
        tyip.fach("2");
        //这里定位成功后 获取 还伞 界面的图标
        haisanIcon = new HaiYuSanTuIconPerserent(MainActivity.this);
        haisanIcon.haisanIcon("1");
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
        mapView.onDestroy();
        //取消语音合成
        this.mSpeechSynthesizer.release();
        //取消手机震动
     //   vibrator.cancel();

    }

    /**
     * 开始定位
     */
    private void
    startLocation() {
        //启动定位
        if (mLocationClient != null) {
            mLocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    private void stopLocation() {
        //停止定位
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
        }
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }


        /*============================6.0 动态权限回调 ===================================*/
    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case MY_PERMISSIONS_MAP: {
                    Toast.makeText(MainActivity.this, R.string.dingweiquanxianchenggong, Toast.LENGTH_SHORT).show();
                    //开始定位
                    startLocation();
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            /* 这个if 就是判断 部分机型有的 机子他妈的 明明授权成功 然而却显示 授权失败 */
            if (AndPermission.hasPermission(getApplicationContext(), deniedPermissions)) {
                Toast.makeText(MainActivity.this, R.string.dingweiquanxianshibai, Toast.LENGTH_SHORT).show();
                // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
                if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, deniedPermissions)) {
                    // 第一种：用默认的提示语。
                    AndPermission.defaultSettingDialog(MainActivity.this, REQUEST_CODE_SETTING).show();
                }
            } else {
                //开始定位
                startLocation();
            }

        }
    };


    //当点击回退键key时执行此方法

    /**
     * 退出程序提醒
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            if (!FINISH) {
                // Toast backToast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT);
                //  backToast.show();
                promptDialog.showError("再按一次退出程序");
                FINISH = true;
                new Timer().schedule(new TimerTask() {

                    @Override
                    public void run() {
                        FINISH = false;

                    }
                }, 2000);
            } else {
                return super.dispatchKeyEvent(event);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    /*
    * 这个方法以及下面的这个方法是自定义infoWindow
    * */
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoContent = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        render(marker, infoContent);
        return infoContent;
    }

    private void render(Marker marker, View view) {
        //这句话是加载气泡上的图片·可加可不加
        //  ((ImageView) view.findViewById(R.id.badge)).setImageResource(R.drawable.badge_sa);

        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.title));
        if (title != null) {
            SpannableString titleText = new SpannableString(title);
            titleText.setSpan(new ForegroundColorSpan(Color.RED), 0,
                    titleText.length(), 0);
            titleUi.setTextSize(15);
            titleUi.setText(titleText);

        } else {
            //  titleUi.setText("");
            titleUi.setText(address);
        }
        String snippet = marker.getSnippet();
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        if (snippet != null) {
            SpannableString snippetText = new SpannableString(snippet);
            snippetText.setSpan(new ForegroundColorSpan(Color.GREEN), 0,
                    snippetText.length(), 0);
            snippetUi.setTextSize(20);
            snippetUi.setText(snippetText);
        } else {
            snippetUi.setText("");
        }
    }

    /*
    * 用户是否取走雨伞的 接口回调
    * */
    @Override
    public void showGetyunsanStatus(Object object) {
        JSONObject obj = (JSONObject) object;
        int status = obj.optInt("status");
        String data =  obj.optString("data");
        mSpeechSynthesizer.speak(data);
       // Toast.makeText(getApplicationContext(),data.toString(),Toast.LENGTH_SHORT).show();

    }


    /*
    *  百度语音合成 需要的 sdk路径获取
    * */
    private void initialEnv() {
        if (mSampleDirPath == null) {
            String sdcardPath = Environment.getExternalStorageDirectory()
                    .toString();
            mSampleDirPath = sdcardPath + "/" + SAMPLE_DIR_NAME;
        }
        makeDir(mSampleDirPath);
        copyFromAssetsToSdcard(false, SPEECH_FEMALE_MODEL_NAME, mSampleDirPath
                + "/" + SPEECH_FEMALE_MODEL_NAME);
        copyFromAssetsToSdcard(false, SPEECH_MALE_MODEL_NAME, mSampleDirPath
                + "/" + SPEECH_MALE_MODEL_NAME);
        copyFromAssetsToSdcard(false, TEXT_MODEL_NAME, mSampleDirPath + "/"
                + TEXT_MODEL_NAME);
        copyFromAssetsToSdcard(false, LICENSE_FILE_NAME, mSampleDirPath + "/"
                + LICENSE_FILE_NAME);
        copyFromAssetsToSdcard(false, "english/"
                + ENGLISH_SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/"
                + ENGLISH_SPEECH_FEMALE_MODEL_NAME);
        copyFromAssetsToSdcard(false, "english/"
                + ENGLISH_SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/"
                + ENGLISH_SPEECH_MALE_MODEL_NAME);
        copyFromAssetsToSdcard(false, "english/" + ENGLISH_TEXT_MODEL_NAME,
                mSampleDirPath + "/" + ENGLISH_TEXT_MODEL_NAME);
    }

    /*
* 创建文件夹路径
* */
    private void makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 将sample工程需要的资源文件拷贝到SD卡中使用（授权文件为临时授权文件，请注册正式授权）
     *
     * @param isCover
     *            是否覆盖已存在的目标文件
     * @param source
     * @param dest
     */
    private void copyFromAssetsToSdcard(boolean isCover, String source,
                                        String dest) {
        File file = new File(dest);
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = getResources().getAssets().open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    // 初始化语音合成客户端并启动
    private void startTTS() {
        this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        this.mSpeechSynthesizer.setContext(this);
        this.mSpeechSynthesizer.setSpeechSynthesizerListener(this);
        // 文本模型文件路径 (离线引擎使用)
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mSampleDirPath+ "/" + TEXT_MODEL_NAME);
        // 声学模型文件路径 (离线引擎使用)
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mSampleDirPath+ "/" + SPEECH_FEMALE_MODEL_NAME);
        // 本地授权文件路径,如未设置将使用默认路径.设置临时授权文件路径，LICENCE_FILE_NAME请替换成临时授权文件的实际路径，仅在使用临时license文件时需要进行设置，如果在[应用管理]中开通了正式离线授权，不需要设置该参数，建议将该行代码删除（离线引擎）
        // 如果合成结果出现临时授权文件将要到期的提示，说明使用了临时授权文件，请删除临时授权即可。
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_LICENCE_FILE, mSampleDirPath + "/"+ LICENSE_FILE_NAME);
        // 请替换为语音开发者平台上注册应用得到的App ID (离线授权)
        this.mSpeechSynthesizer.setAppId("9959735");
        // 请替换为语音开发者平台注册应用得到的apikey和secretkey (在线授权)
        this.mSpeechSynthesizer.setApiKey("EfZlabSvnGbdvoQnE4s7blGH","71cd434667f8fa9a15f090a3e4a3f5d2");
        // 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，1--普通男声，2--特别男声，3--情感男声。。。）
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置Mix模式的合成策略
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE,SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 授权检测接口(只是通过AuthInfo进行检验授权是否成功。)
        // AuthInfo接口用于测试开发者是否成功申请了在线或者离线授权，如果测试授权成功了，可以删除AuthInfo部分的代码（该接口首次验证时比较耗时），不会影响正常使用（合成使用时SDK内部会自动验证授权）
        AuthInfo authInfo = this.mSpeechSynthesizer.auth(TtsMode.MIX);
        if (authInfo.isSuccess()) {
            Toast.makeText(getApplicationContext(),"授权成功",Toast.LENGTH_SHORT).show();
        } else {
            String errorMsg = authInfo.getTtsError().getDetailMessage();
            Toast.makeText(getApplicationContext(),"授权失败 " +errorMsg ,Toast.LENGTH_SHORT).show();
        }

        // 初始化tts
        mSpeechSynthesizer.initTts(TtsMode.MIX);
        // 加载离线英文资源（提供离线英文合成功能）
        int result = mSpeechSynthesizer.loadEnglishModel(mSampleDirPath + "/"
                + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath + "/"
                + ENGLISH_SPEECH_FEMALE_MODEL_NAME);
        //  toPrint("loadEnglishModel result=" + result);

    }




    /*
    *  下面6个方法都是 百度语音合成从写的
     *  SpeechSynthesizerListener接口的方法
    * */

    @Override
    public void onSynthesizeStart(String s) {
        // 监听到合成开始，在此添加相关操作
        L.e("测试 "+"onSynthesizeStart");
    }

    @Override
    public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {
        // 监听到有合成数据到达，在此添加相关操作
        L.e("测试"+"onSynthesizeDataArrived");
    }

    @Override
    public void onSynthesizeFinish(String s) {
        // 监听到合成结束，在此添加相关操作
        L.e("测试"+"onSynthesizeFinish");
    }

    @Override
    public void onSpeechStart(String s) {
        // 监听到合成并播放开始，在此添加相关操作
        L.e("测试"+"onSpeechStart");
    }

    @Override
    public void onSpeechProgressChanged(String s, int i) {
        // 监听到播放进度有变化，在此添加相关操作
        L.e("测试"+"onSpeechProgressChanged");
    }

    @Override
    public void onSpeechFinish(String s) {
        // 监听到播放结束，在此添加相关操作
        L.e("测试"+"onSpeechFinish");
    }

    @Override
    public void onError(String s, SpeechError speechError) {
        // 监听到出错，在此添加相关操作
        L.e("测试"+"onError");
        L.e("测试error "+s+" 测试 SpeechError "+speechError.description);
    }


}
