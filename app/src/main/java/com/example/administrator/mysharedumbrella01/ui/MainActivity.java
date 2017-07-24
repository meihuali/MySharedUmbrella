package com.example.administrator.mysharedumbrella01.ui;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.SaoYiSao.ScannerActivity;
import com.example.administrator.mysharedumbrella01.dialog.KaiSuohoudeGuanggao;
import com.example.administrator.mysharedumbrella01.dialog.PopupWindowBotoom;
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
import com.example.administrator.mysharedumbrella01.peresenet.YuSanTuIconPerserent;
import com.example.administrator.mysharedumbrella01.utils.FlikerProgressBar;
import com.example.administrator.mysharedumbrella01.utils.GlideUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.dialog.MyPopuopWindowsRigth;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.UpdataProgressBar;
import com.example.administrator.mysharedumbrella01.utils.ZhuoBiaoZhuanHuan;
import com.example.administrator.mysharedumbrella01.view.IsHaiYuSanJiemianIconView;
import com.example.administrator.mysharedumbrella01.view.IsUmbrellaView;
import com.example.administrator.mysharedumbrella01.view.IsUpdataAPPView;
import com.example.administrator.mysharedumbrella01.view.IsYuSanTuIconView;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.mylhyl.zxing.scanner.common.Intents;
import com.tencent.bugly.crashreport.CrashReport;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.Response;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.ui
 * 文件名：ShowBaidu
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/4/26 0026 下午 3:25
 * 描述：TODO
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AMap.OnMarkerClickListener, IsUmbrellaView, IsUpdataAPPView,Runnable, IsYuSanTuIconView ,IsHaiYuSanJiemianIconView{
    private AMap aMap;
    private MapView mapView;
    private Polyline polyline;
    private MarkerOptions markerOption;
    private List<LatLng> list1 = new ArrayList<>();

    //    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

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
    private UmbrellaPresenet up;
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
            //这里请求·还伞 图标icon  跟 借伞图标 ICOn
        HttpQuestIcon();
        /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
        //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
//        MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();


    }

    private void HttpQuestIcon() {
        //这里定位成功后首先获取·借伞 界面的雨伞图标
        tyip = new YuSanTuIconPerserent(MainActivity.this);
        tyip.fach("10");
        //这里定位成功后 获取 还伞 界面的图标
        haisanIcon = new HaiYuSanTuIconPerserent(MainActivity.this);
        haisanIcon.haisanIcon("11");
    }



    /*=============================================================*/

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
        if (aMap == null) {
            aMap = mapView.getMap();
            if (aMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        //下面两句 去掉经纬圈
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//       设置marker点击 监听
        aMap.setOnMarkerClickListener(this);
        // 设置地图可视缩放大小
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        aMap.getUiSettings().setCompassEnabled(true);// 设置指南针
        aMap.getUiSettings().setZoomControlsEnabled(false); // 设置右下角的按钮缩放

//        定位成功后的回调
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                laitudes = location.getLatitude();
                longitudes = location.getLongitude();
/*                //这里定位成功后首先获取·借伞 界面的雨伞图标
                tyip = new YuSanTuIconPerserent(MainActivity.this);
                tyip.fach("10");
                //这里定位成功后 获取 还伞 界面的图标
                haisanIcon = new HaiYuSanTuIconPerserent(MainActivity.this);
                haisanIcon.haisanIcon("11");*/

                // 这里定位成功获取雨伞 分布的网络请求
                up = new UmbrellaPresenet(MainActivity.this);
                up.fech(MainActivity.this, laitudes, longitudes,types);
                //定位成功弹出 主页广告（商业广告）
                initShowZhuYeGuangGao();
                /*
                * 定位成功判断用户是否是 登录状态
                * */
                //更新APP的广告
                String zh = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
//                String mi = ShareUtils.getString(getApplicationContext(), "mima", "");
                if (!TextUtils.isEmpty(zh)) {
                    initShowUpdataApp();
                }
            }
        });
    }

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

            UpdataBean.DataBean ubdb  = updataBean.getData();
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
            PackageInfo info = pm.getPackageInfo(getPackageName(),0);
            //获取本地版本号
            bendiVersionCode = info.versionCode;
            //获取本地版本的名字
            bendiVersionName = info.versionName;

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"更新当前版本号获取失败",Toast.LENGTH_SHORT).show();
        }
        /*
        * 判断服务器版本号是否大于本地版本号
        *  qiangzhiUdata 为0 不强制更新, 不为0则强制更新 隐藏取消按钮
        * */

        if (serviceVerSionCode > bendiVersionCode) {
            UpdataDialog.show(this,"修复了一些bug","共享雨伞震撼上线",qiangzhiUdata,false,null);
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
//                String mi = ShareUtils.getString(getApplicationContext(), "mima", "");
                if (!TextUtils.isEmpty(zh)) {
                    //laitudes,longitudes 当前经纬度
                    Intent intent = new Intent(this, SettingsYusanActivity.class);
                    intent.putExtra("laitudes", laitudes);
                    intent.putExtra("longitudes", longitudes);
                    startActivity(intent);
                    //  finish();
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
//                    PopupWindowUtils pwu = new PopupWindowUtils(this);
//                    pwu.showPopupWindow();
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
                //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                //然后把types赋值为2  表示·还伞的字段·用来区分mark 图标
                types = 2;
                up.fech(MainActivity.this, laitudes, longitudes,types);
                btn_haisan.setBackground(ContextCompat.getDrawable(this,R.drawable.image_conent));
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
                //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                //给types 赋值为 1 表示· 雨伞的图标
                types = 1;
                up.fech(MainActivity.this, laitudes, longitudes,types);
                //设置背景
                btn_jiesan.setBackground(ContextCompat.getDrawable(this,R.drawable.image_conent));
                btn_haisan.setBackgroundColor(getResources().getColor(R.color.zhutiyanse));
                //加一个字段为typeHaisan 用来刷新
                typeHaisan = 1;
                break;
            //右下角的客户返回 按钮
            case R.id.image_kehu:
//                PopupWindowBotoom pwb = new PopupWindowBotoom(this);
//                pwb.showPopupWindow();
                startActivity(new Intent(getApplicationContext(),KeHuFanKuiActivity.class));

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
                    //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                    //给types 赋值为 1 表示· 雨伞的图标
                    types = 1;
                    up.fech(MainActivity.this, laitudes, longitudes, types);
                    //点击借伞后请求网络 图标
                    tyip.fach("10");
                } else {
                    a = 1;
                    aMap.clear();
                    //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                    //然后把types赋值为2  表示·还伞的字段·用来区分mark 图标
                    types = 2;
                    up.fech(MainActivity.this, laitudes, longitudes,types);
                    //点还伞后 请求 伞架子的 图标
                    tyip.fach("11");
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
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UpdataDialog.round_flikerbar.setProgress(msg.arg1);
            if(msg.arg1 == 100){
                UpdataDialog.round_flikerbar.finishLoad();
            }
        }
    };




    @Override
    public void run() {
        try {
            while(!downLoadThread.isInterrupted()){
                float progress =  UpdataDialog.round_flikerbar.getProgress();
                progress  += 2;
                Thread.sleep(200);
                Message message = handler.obtainMessage();
                message.arg1 = (int) progress;
                handler.sendMessage(message);
                if(progress == 100){
                    break;
                }
            }
        }catch (InterruptedException e) {
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
        L.e("marker     " + id);

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
    private void addMarkers(final LatLng latLng,  final String umbrellanubers) {

        View view = LayoutInflater.from(this).inflate(R.layout.gaode_marker_icon,null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.img_marker);
        // 这里实例化这个对象 获取到 一个bitmap 的参数·然后设置在 高德地图上的marker上
        SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(resource);
                MarkerOptions markerOption1 = new MarkerOptions().anchor(0.5f, 0.5f)
                        .position(latLng)
                        .title("雨伞个数:")
                        .snippet(umbrellanubers)
                        //   .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.yushan1)))
                        .icon(BitmapDescriptorFactory.fromBitmap(resource))
                        .draggable(true).period(10);
                ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
                markerOptionlst.add(markerOption1);
                List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);
                marker2 = markerlst.get(0);
            }
        };
        L.e("marker借伞界面图标 "+yusanIconUrl);
        Glide.with(getApplicationContext()).load(yusanIconUrl).asBitmap().error(getResources().getDrawable(R.drawable.yushan1)).skipMemoryCache(true).override(35, 35).into(target);
    }

    /*点击线条中间覆盖物显示气泡*/
    private void addMarkerss(final LatLng latLng, final String vacancynumber) {
        View view = LayoutInflater.from(this).inflate(R.layout.gaode_marker_icon,null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.img_marker);
        // 这里实例化这个对象 获取到 一个bitmap 的参数·然后设置在 高德地图上的marker上
        SimpleTarget<Bitmap> simple = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(resource);
                MarkerOptions markerOption1 = new MarkerOptions().anchor(0.5f, 0.5f)
                        .position(latLng).title("空位个数:")
                        .snippet(vacancynumber)
                        .icon(BitmapDescriptorFactory.fromBitmap(resource))
                        .draggable(true).period(10);
                ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();

                markerOptionlst.add(markerOption1);
                List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);
                marker2 = markerlst.get(0);
            }
        };
        //之类是还伞 获取到的 图标 路径

        Glide.with(getApplicationContext()).load(haisanURL).asBitmap().skipMemoryCache(true).override(35, 35).into(simple);

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
                        up.binds(stringExtra, zhanghao,this);
                        //扫描成功后再次请求下网络获取雨伞个数
                        up.fech(MainActivity.this, laitudes, longitudes,types);
//                //定时刷新
//                        timetask(laitudes, longitudes,2);
                        //开锁中的广告
                        pwgg = new PopupWindowGuanGao(this,stringExtra);
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


    //  扫一扫的接口回调
    @Override
    public void showSaoYiSao(SaoYiSaoBean syb, String mincdeID, String phone) {
        statusSaoYiSao = syb.getStatus();
        //这里就是 回调 的结果 扫描开锁后的 结果
        if (statusSaoYiSao == 1) {
            //雨伞分布的 坐标 有多少个雨伞
            aMap.clear();
            up.fech(MainActivity.this, laitudes, longitudes,types);
            Toast.makeText(getApplicationContext(), "借伞成功", Toast.LENGTH_SHORT).show();
            int statsus = statusSaoYiSao;
            String datas =  syb.getData();
            L.e("开锁成功拿到伞的id  "+datas);
            pwgg.stopUpdata(statsus);
            //隐藏开锁广告popup
            pwgg.dismiss();
            //开锁成功 又开启一个popupwindow
            KaiSuohoudeGuanggao zy = new KaiSuohoudeGuanggao(this,datas);
            zy.showPopupWindow();
        } else {
            Toast.makeText(getApplicationContext(), "借伞失败", Toast.LENGTH_SHORT).show();
            int statsus = statusSaoYiSao;
            pwgg.stopUpdata(statsus);
            //隐藏开锁广告popup
            pwgg.dismiss();
//            //开锁失败 又开启一个popupwindow
//            KaiSuohoudeGuanggao zy = new KaiSuohoudeGuanggao(this,datas);
//            zy.showPopupWindow();
        }

    }



    /*获取 雨伞分布图·从这里开始 ·已经开始 学习 了 MVP 架构设计模式了*/
    @Override
    public void showUmbrella(List<GetumbrellaBean.DataBean> list,int types) {
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
                latLng_xianludian =  ZhuoBiaoZhuanHuan.transformFromWGSToGCJ(latLng_xianludiana);
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

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
            mlocationClient.stopLocation();
        }
        marker = null;
    }
    /*
    *  该方法是定位成功后 网络请求 从服务器获取到 返回的雨伞图标
    * */
    @Override
    public void ShowYuSanIcon(Object object) {
        YuSanIconBean ysib = (YuSanIconBean) object;
        int status =  ysib.getStatus();
        if (status == 1) {
            yusanIconUrl = ysib.getData();
            L.e("借伞图标ICON "+yusanIconUrl);
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
        int status =  haisanIcon.getStatus();
        if (status == 1) {
            haisanURL = haisanIcon.getData();
            L.e("还伞图标ICON "+haisanURL);
        }
    }

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
}
