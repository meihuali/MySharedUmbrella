package com.example.administrator.mysharedumbrella01.ui;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.SaoYiSao.ScannerActivity;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.dialog.KaiSuohoudeGuanggao;
import com.example.administrator.mysharedumbrella01.dialog.MarkerQipao;
import com.example.administrator.mysharedumbrella01.dialog.MyPopuopWindowsRigth;
import com.example.administrator.mysharedumbrella01.dialog.PopupWindowGuanGao;
import com.example.administrator.mysharedumbrella01.dialog.YaJinGuangGao;
import com.example.administrator.mysharedumbrella01.dialog.ZhuYeGuangGao;
import com.example.administrator.mysharedumbrella01.entivity.GetumbrellaBean_two;
import com.example.administrator.mysharedumbrella01.entivity.SaoYiSaoBean;
import com.example.administrator.mysharedumbrella01.entivity.UserCurrentYusanBean;
import com.example.administrator.mysharedumbrella01.entivity.YuSanIconBean;
import com.example.administrator.mysharedumbrella01.lib.LocationTask;
import com.example.administrator.mysharedumbrella01.lib.OnLocationGetListener;
import com.example.administrator.mysharedumbrella01.lib.PositionEntity;
import com.example.administrator.mysharedumbrella01.lib.RegeocodeTask;
import com.example.administrator.mysharedumbrella01.lib.RouteTask;
import com.example.administrator.mysharedumbrella01.peresenet.HaiYuSanTuIconPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.IsUserCurrentPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.UmbrellaPresenet;
import com.example.administrator.mysharedumbrella01.peresenet.UserGetYuSanStatusPeresent;
import com.example.administrator.mysharedumbrella01.peresenet.YuSanTuIconPerserent;
import com.example.administrator.mysharedumbrella01.utils.AMapUtil;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.MyDialog;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.utils.Utils;
import com.example.administrator.mysharedumbrella01.utils.WalkRouteOverlay;
import com.example.administrator.mysharedumbrella01.utils.ZhuoBiaoZhuanHuan;
import com.example.administrator.mysharedumbrella01.view.IsHaiYuSanJiemianIconView;
import com.example.administrator.mysharedumbrella01.view.IsUmbrellaView;
import com.example.administrator.mysharedumbrella01.view.IsUserCurrentView;
import com.example.administrator.mysharedumbrella01.view.IsUserGetYuSanStatusView;
import com.example.administrator.mysharedumbrella01.view.IsYuSanTuIconView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.mylhyl.zxing.scanner.common.Intents;
import com.whyalwaysmea.circular.AnimUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.leefeng.promptlibrary.PromptDialog;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements AMap.OnCameraChangeListener,
        AMap.OnMapLoadedListener, OnLocationGetListener, View.OnClickListener, RouteTask.OnRouteCalculateListener,
        AMap.OnMapTouchListener, RouteSearch.OnRouteSearchListener, AMap.OnMapClickListener, AMap.InfoWindowAdapter, IsUmbrellaView, IsYuSanTuIconView, IsHaiYuSanJiemianIconView, IsUserGetYuSanStatusView, IsUserCurrentView {
    public static final String TAG = "MainActivity";
    public static final int REQUEST_CODE = 1;
    //地图view
    MapView mMapView = null;
    //初始化地图控制器对象
    AMap aMap;

    //定位
    private LocationTask mLocationTask;
    //逆地理编码功能
    private RegeocodeTask mRegeocodeTask;
    //绘制点标记
    private Marker mPositionMark, mInitialMark, tempMark;//可移动、圆点、点击
    //初始坐标、移动记录坐标
    private LatLng mStartPosition, mRecordPositon;
    //默认添加一次
    private boolean mIsFirst = true;
    //就第一次显示位置
    private boolean mIsFirstShow = true;

    private LatLng initLocation;

    private ValueAnimator animator = null;//坐标动画
    private BitmapDescriptor initBitmap, moveBitmap;//定位圆点、可移动、所有标识（车）
    private RouteSearch mRouteSearch;

    private WalkRouteResult mWalkRouteResult;
    private LatLonPoint mStartPoint = null;//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = null;//终点，116.481288,39.995576
    private final int ROUTE_TYPE_WALK = 3;
    private boolean isClickIdentification = false;
    WalkRouteOverlay walkRouteOverlay;//路线
    private String[] time;
    private String distance;

    private PromptDialog promptDialog;
    private String yusanIconUrl;
    private String isFiestype = "1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseAppliction.addDestoryActivity(this, "MainActivity");
        //初始化下dialog
        promptDialog = new PromptDialog(this);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
               //  .statusBarDarkFont(true,0.2f)  //  如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        init();
        //这里请求·还伞 图标icon  跟 借伞图标 ICOn
        HttpQuestIcon();

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        initBitmap();
        initAMap();
        initLocation();
        RouteTask.getInstance(getApplicationContext())
                .addRouteCalculateListener(this);
    }

    private void initBitmap() {
        //定位图标
        initBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.locaticon_iconsssss);

        //移动覆盖物图标
        moveBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.purple_pin);  //icon_loaction_start .png
    }

    /**
     * 初始化地图控制器对象
     */
    private void initAMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mRouteSearch = new RouteSearch(this);
            mRouteSearch.setRouteSearchListener(this);
            aMap.getUiSettings().setZoomControlsEnabled(false);
            aMap.getUiSettings().setGestureScaleByMapCenter(true);
//            aMap.getUiSettings().setScrollGesturesEnabled(false);
            aMap.setOnMapTouchListener(this);
            aMap.setOnMapLoadedListener(this);
            aMap.setOnCameraChangeListener(this);
            aMap.setOnMapClickListener(this);
            // 绑定 Marker 被点击事件
            aMap.setOnMarkerClickListener(markerClickListener);
            aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        }
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationTask = LocationTask.getInstance(getApplicationContext());
        mLocationTask.setOnLocationGetListener(this);
        mRegeocodeTask = new RegeocodeTask(getApplicationContext());
    }

    // 定义 Marker 点击事件监听
    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {

        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(final Marker marker) {
            Log.e(TAG, "点击的Marker");
            Log.e(TAG, marker.getPosition() + "");
            isClickIdentification = true;
            if (tempMark != null) {
                tempMark.setIcon(marker.getIcons().get(0));
                walkRouteOverlay.removeFromMap();
                tempMark = null;
            }
            startAnim(marker);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                        tempMark = marker;
                        Log.e(TAG, mPositionMark.getPosition().latitude + "===" + mPositionMark.getPosition().longitude);
                        mStartPoint = new LatLonPoint(mRecordPositon.latitude, mRecordPositon.longitude);
                        mPositionMark.setPosition(mRecordPositon);
                        mEndPoint = new LatLonPoint(marker.getPosition().latitude, marker.getPosition().longitude);
                        marker.setPosition(marker.getPosition());
                        searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return true;
        }
    };

    /*
    * 将marker 点击后变大一点
    * */
    private void startAnim(Marker marker) {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.6f, 1.0f, 1.6f);
        anim.setDuration(300);
        marker.setAnimation(anim);
        marker.startAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        Utils.removeMarkers();
        mMapView.onDestroy();
        mLocationTask.onDestroy();
        RouteTask.getInstance(getApplicationContext()).removeRouteCalculateListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
        if (mInitialMark != null) {
            mInitialMark.setToTop();
        }
        if (mPositionMark != null) {
            mPositionMark.setToTop();
        }
        userCurrentYusan();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        Log.e(TAG, "onCameraChangeFinish" + cameraPosition.target);
        if (!isClickIdentification) {
            mRecordPositon = cameraPosition.target;
        }
        mStartPosition = cameraPosition.target;
        mRegeocodeTask.setOnLocationGetListener(this);
        mRegeocodeTask
                .search(mStartPosition.latitude, mStartPosition.longitude);
//        Utils.removeMarkers();

        if (mInitialMark != null) {
            mInitialMark.setToTop();
        }
        if (mPositionMark != null) {
            mPositionMark.setToTop();
            if (!isClickIdentification) {
                animMarker();
            }
        }
    }


    /**
     * 地图加载完成
     */
    @Override
    public void onMapLoaded() {
        initPermission();
    }

    /**
     * 创建初始位置图标
     */
    private void createInitialPosition(double lat, double lng) {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.setFlat(true);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(lat, lng));
        markerOptions.icon(initBitmap);
        mInitialMark = aMap.addMarker(markerOptions);
        mInitialMark.setClickable(false);
    }

    /**
     * 创建移动位置图标
     */
    private void createMovingPosition() {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.setFlat(true);
//        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(0, 0));
        markerOptions.icon(moveBitmap);
        mPositionMark = aMap.addMarker(markerOptions);
        mPositionMark.setPositionByPixels(mMapView.getWidth() / 2,
                mMapView.getHeight() / 2);
        mPositionMark.setClickable(false);
    }

    PositionEntity entity;

    @Override
    public void onLocationGet(PositionEntity entity) {
        this.entity = entity;

        // todo 这里在网络定位时可以减少一个逆地理编码
        Log.e("onLocationGet", "onLocationGet" + entity.address);
        RouteTask.getInstance(getApplicationContext()).setStartPoint(entity);
        mStartPosition = new LatLng(entity.latitue, entity.longitude);
        createInitialPosition(entity.latitue, entity.longitude);
        createMovingPosition();
        mIsFirst = false;
        if (mIsFirstShow) {
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    mStartPosition, 17);
            aMap.animateCamera(cameraUpate);
            mIsFirstShow = false;
        }
        mInitialMark.setPosition(mStartPosition);
        initLocation = mStartPosition;
        Log.e("onLocationGet", "onLocationGet" + mStartPosition);
        mLocationTask.stopLocate();
        //定位成功后获取雨伞分布
        UmbrellaPresenet getyusanfenbu = new UmbrellaPresenet(this);
        getyusanfenbu.fech(MainActivity.this,entity.latitue, entity.longitude, types);

    }

    @Override
    public void onRegecodeGet(PositionEntity entity) {
        Log.e(TAG, "onRegecodeGet" + entity.address);
        entity.latitue = mStartPosition.latitude;
        entity.longitude = mStartPosition.longitude;
        RouteTask.getInstance(getApplicationContext()).setStartPoint(entity);
        RouteTask.getInstance(getApplicationContext()).search();
        Log.e(TAG, "onRegecodeGet" + mStartPosition);
    }

    @Override
    public void onRouteCalculate(float cost, float distance, int duration) {
        Log.e(TAG, "cost" + cost + "---" + "distance" + distance + "---" + "duration" + duration);
        PositionEntity endPoint = RouteTask.getInstance(getApplicationContext()).getEndPoint();
        mRecordPositon = new LatLng(endPoint.latitue, endPoint.longitude);
        clickMap();
        RouteTask.getInstance(getApplicationContext()).setEndPoint(null);
    }

    @Override
    public void onTouch(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() >= 2) {
            aMap.getUiSettings().setScrollGesturesEnabled(false);
        } else {
            aMap.getUiSettings().setScrollGesturesEnabled(true);
        }
    }

    private void animMarker() {
        if (animator != null) {
            animator.start();
            return;
        }
        animator = ValueAnimator.ofFloat(mMapView.getHeight() / 2, mMapView.getHeight() / 2 - 30);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(150);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                mPositionMark.setPositionByPixels(mMapView.getWidth() / 2, Math.round(value));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mPositionMark.setIcon(moveBitmap);
            }
        });
        animator.start();
    }

    private void endAnim() {
        if (animator != null && animator.isRunning())
            animator.end();
    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
        // LoadDialog.getInstance().dismiss();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mWalkRouteResult = result;
                    final WalkPath walkPath = mWalkRouteResult.getPaths()
                            .get(0);
                    walkRouteOverlay = new WalkRouteOverlay(
                            this, aMap, walkPath,
                            mWalkRouteResult.getStartPos(),
                            mWalkRouteResult.getTargetPos());
                    walkRouteOverlay.removeFromMap();
                    walkRouteOverlay.addToMap();
                    walkRouteOverlay.zoomToSpan();
                    int dis = (int) walkPath.getDistance();
                    int dur = (int) walkPath.getDuration();
                    time = AMapUtil.getFriendlyTimeArray(dur);
                    distance = AMapUtil.getFriendlyLength(dis);
                    des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
                    tempMark.setTitle(des);
                    tempMark.showInfoWindow();
                    Log.e(TAG, des);
                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(this, R.string.no_result);
                }
            } else {
                ToastUtil.show(this, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this.getApplicationContext(), errorCode);
        }
    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(int routeType, int mode) {
        if (mStartPoint == null) {
            ToastUtil.show(this, "定位中，稍后再试...");
            return;
        }
        if (mEndPoint == null) {
            ToastUtil.show(this, "终点未设置");
        }
        //showDialog();

        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
            mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        }
    }

   /* private void showDialog()
    {
        LoadDialog loadDialog =  LoadDialog.getInstance();
        loadDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.load_dialog);
        LoadDialog.getInstance().show(getSupportFragmentManager(),"");
    }*/

    @Override
    public void onMapClick(LatLng latLng) {
        clickMap();
    }

    private void clickRefresh() {
        clickInitInfo();
        if (initLocation != null) {
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    initLocation, 17f);
            aMap.animateCamera(cameraUpate);
        }
    }

    private void clickMap() {
        clickInitInfo();
        if (mRecordPositon != null) {
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    mRecordPositon, 17f);
            aMap.animateCamera(cameraUpate);
        }
    }

    private void clickInitInfo() {
        isClickIdentification = false;
        if (null != tempMark) {
            tempMark.hideInfoWindow();
            tempMark = null;
        }
        if (null != walkRouteOverlay) {
            walkRouteOverlay.removeFromMap();
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
        TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
        TextView tv_time_info = (TextView) view.findViewById(R.id.tv_time_info);
        TextView tv_distance = (TextView) view.findViewById(R.id.tv_distance);
        tv_time.setText(time[0]);
        tv_time_info.setText(time[1]);
        tv_distance.setText(distance);
        String yusan = marker.getSnippet();
        //L.e("测试 "+yusan);
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (marker.getId().equals("Marker1")) {
            View infoContents = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
            TextView myLoction = (TextView) infoContents.findViewById(R.id.title);
            myLoction.setText(entity.address);
            return infoContents;
        } else {

            View infoContent = getLayoutInflater().inflate(R.layout.activity_infowindow, null);
            String caonima = marker.getSnippet();
            String kejie = caonima.substring(0,caonima.indexOf(","));
            String kehai = caonima.substring(caonima.lastIndexOf(",", caonima.length()));
            String[] strs=kehai.split(",");
            for(int i=0,len=strs.length;i<len;i++){
                System.out.println(strs[1].toString());
                kehaistr = strs[1].toString();
            }
            L.e("可借 ："+kejie+"可还："+kehaistr);
            TextView kejieYuSan = (TextView) infoContent.findViewById(R.id.tv_yusanconct);
            TextView kehaiYuSan = (TextView) infoContent.findViewById(R.id.tv_yusanconct2);
            TextView tv_bushu = (TextView) infoContent.findViewById(R.id.tv_bushu);
            kejieYuSan.setText("可借："+kejie);
            kehaiYuSan.setText("可还："+kehaistr);
            tv_bushu.setText("步行："+des);
            L.e("距离目标距离 "+"控件");
            //气泡上的按钮的点击事件
            ImageView img_cesss = (ImageView) infoContent.findViewById(R.id.img_cesss);
            img_cesss.setOnClickListener(this);

            return infoContent;
        }
    }

    @Override
    public void ShowYuSanIcon(Object object) {
        YuSanIconBean ysib = (YuSanIconBean) object;
        int status = ysib.getStatus();
        if (status == 1) {
            yusanIconUrl = ysib.getData();
            String jiesanurl = ConfigUtils.YUSANTUBIAO + yusanIconUrl;
            ShareUtils.putString(getApplicationContext(), "jiesantubiao", jiesanurl);
            L.e("借伞图标ICON " + yusanIconUrl);
        } else {
            promptDialog.showError("获取雨伞图标失败");
        }
    }

    @Override
    public void ShowHaiSanIcon(Object object) {
        YuSanIconBean haisanIcon = (YuSanIconBean) object;
        int status = haisanIcon.getStatus();
        if (status == 1) {
            haisanURL = haisanIcon.getData();
            String haisanurl = ConfigUtils.YUSANTUBIAO + haisanURL;
            ShareUtils.putString(getApplicationContext(), "haisantubiao", haisanurl);
            L.e("还伞图标ICON " + haisanURL);
        }
    }
        /*
        *
        * 判断用户是否取走雨伞
        * */
    @Override
    public void showGetyunsanStatus(Object object) {
        //这句话是 用户不管取走不取走雨伞 都要再次设置 扫描按钮的监听
        plgig.setOnClickListener(this);
        isFiestype = "1";
        JSONObject obj = (JSONObject) object;
        int status = obj.optInt("status");
        String data =  obj.optString("data");

    }

        /*
    * 地图坐上角的 用户正在使用雨伞的接口回调
    * */

    @Override
    public void showCurrent(Object object) {
        UserCurrentYusanBean userYuSanBean = (UserCurrentYusanBean) object;
        int status = userYuSanBean.getStatus();
        if (status == 0) {
            tv_current.setVisibility(View.GONE);
        } else if (status == 1) {
            tv_current.setVisibility(View.VISIBLE);
            btn_cuonst.setText(userYuSanBean.getData());
        }
    }

    @Override
    public void showErres() {
        tv_current.setVisibility(View.GONE);
    }

    @Override
    public void showUmbrella(List<GetumbrellaBean_two.DataBean> list, int types) {
        L.e("雨伞个数 "+"showUmbrella");
        if (types == 1) {
            int sizes = list.size();
            listInfoWindow.addAll(list);
            L.e("sizes" + sizes);
            for (int i = 0; i < list.size(); i++) {
                String longitude = list.get(i).getLongitude();
                String latitude = list.get(i).getLatitude();
                //剩余 雨伞个数
                int umbrellanuber = (list.get(i).getStand_all()) - (list.get(i).getStand_surplus());
                umbrellanubers = String.valueOf(umbrellanuber);
                //剩余空位个数
                vacancynumber = list.get(i).getStand_surplus()+"";
                longitudes = Double.parseDouble(longitude);
                latitudes = Double.parseDouble(latitude);
                latLng_xianludiana = new LatLng(latitudes, longitudes);
                // 坐标转换
                latLng_xianludian = ZhuoBiaoZhuanHuan.transformFromWGSToGCJ(latLng_xianludiana);
                //雨伞架子分布个数
                addMarkers(latLng_xianludian, umbrellanubers,vacancynumber);
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

    @Override
    public void showSaoYiSao(Object syb, String mincdeID, String phone) {
        SaoYiSaoBean object = (SaoYiSaoBean) syb;
        statusSaoYiSao = object.getStatus();
        L.e("开锁状态："+statusSaoYiSao);
        String data = object.getData();
        if (statusSaoYiSao == 11) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();

            //停止广告上的进度条
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
        } else if (statusSaoYiSao == 1) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();

            //当statusSaoYiSao等于1 的时候 去请求另外一个接口判断用户是否有取消雨伞
            UserGetYuSanStatusPeresent qusan = new UserGetYuSanStatusPeresent(this);
            //这里截取 前面这段字符串 222222222222222202,555555555555555555
            String stely = data.substring(0, data.lastIndexOf(","));
            qusan.userGetYuSan(stely);

            //这里截取 后面的这段字符串 222222222222222202,555555555555555555
            String enddata = data.substring(data.lastIndexOf(",", data.length()));

            //雨伞分布的 坐标 有多少个雨伞
            aMap.clear();
            mIsFirst = true;
            //这里再次设置覆盖物
            getResfh();

            //开锁成功 又开启一个popupwindow
            KaiSuohoudeGuanggao zy = new KaiSuohoudeGuanggao(this, enddata);
            zy.showPopupWindow();

        } else if (statusSaoYiSao == 2) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            // promptDialog.showSuccess("押金不足");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
            YaJinGuangGao yaJinGuangGao = new YaJinGuangGao(this);
            yaJinGuangGao.showPopupWindow();


        } else if (statusSaoYiSao == 3) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            // promptDialog.showSuccess("余额不足");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
            YaJinGuangGao yaJinGuangGao = new YaJinGuangGao(this);
            yaJinGuangGao.showPopupWindow();


        } else if (statusSaoYiSao == 4) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            // promptDialog.showSuccess("请求超时，开锁失败");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
            dialogss("借伞", data);

        } else if (statusSaoYiSao == 5) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            //  promptDialog.showSuccess("无伞可借");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
            dialogss("借伞", data);

        } else if (statusSaoYiSao == 6) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            //  promptDialog.showSuccess("重新发起开锁请求");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
            dialogss("借伞", data);

        } else if (statusSaoYiSao == 7) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            //  promptDialog.showSuccess("重新发起开锁请求");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
            dialogss("借伞", data);

        } else if (statusSaoYiSao == 0) {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            //  promptDialog.showSuccess("通信错误");
            pwgg.stopUpdata(statusSaoYiSao);
            //隐藏开锁广告popup
            pwgg.dismiss();
            dialogss("借伞", data);

        } else if (statusSaoYiSao == 8) {
            dialogss("借伞", data);
            isFiestype = "1";
            //手机震动
            shoujizhengdong();
            //隐藏开锁广告popup
            pwgg.dismiss();
        } else {
            //这里是第一条接口请求失败后 要设置上 扫描按钮的监听事件
            plgig.setOnClickListener(this);
            isFiestype = "1";
            MyDialog.dialog("提示","开锁失败不知道服务器返回的是什么鬼，"+data,"确定","");
        }
    }

    private LinearLayout tv_current;
    private RelativeLayout ll_xxxx;
    private ImageView image_shuaxin;
    private ImageView image_kehu;
    private Button btn_haisan, btn_jiesan;
    private ImageView image_backs;
    private GifImageView plgig;
    private TextView tv_adds;
    private YuSanTuIconPerserent tyip;
    private HaiYuSanTuIconPerserent haisanIcon;
    public static final int MY_PERMISSIONS_MAP = 0;
    public static final int REQUEST_CODE_SETTING = 300;
    private int statusSaoYiSao;
    //手机震动
    private Vibrator vibrator;
    private PopupWindowGuanGao pwgg;
    private int types = 1; //默认等于1 代表 雨伞分布的请求 点击还伞等于2 的时候表示雨伞架子的请求
    private String haisanURL;
    private boolean FINISH;
    private int a = 0;
    private int typeHaisan = 1;
    // 扫一扫相关 颜色  如果不赋值的话· 扫描上下滚动的就是绿色这里默认 赋值为 支付宝 那种网格的
    private int laserMode = ScannerActivity.EXTRA_LASER_LINE_MODE_0;
    private String stringExtra;
    List<GetumbrellaBean_two.DataBean> listInfoWindow = new ArrayList<>();
    private String umbrellanubers;
    private double longitudes;
    private double latitudes;
    private String vacancynumber;
    private LatLng latLng_xianludiana;
    private LatLng latLng_xianludian;
    private String kehaistr;
    private String des;

    private Button btn_cuonst;
       /*========================初始化一些控件=====================================*/

    private void init() {
        btn_cuonst = (Button) findViewById(R.id.btn_cuonst);
        btn_cuonst.setOnClickListener(this);
        tv_current = (LinearLayout) findViewById(R.id.tv_current);
        tv_current.setOnClickListener(this);
        ll_xxxx = (RelativeLayout) findViewById(R.id.ll_xxxx);
        image_shuaxin = (ImageView) findViewById(R.id.image_shuaxin);
        image_shuaxin.setOnClickListener(this);
        image_kehu = (ImageView) findViewById(R.id.image_kehu);
        image_kehu.setOnClickListener(this);


        //初始化控件以及地图
        image_backs = (ImageView) findViewById(R.id.image_backs);
        image_backs.setOnClickListener(this);
        plgig = (GifImageView) findViewById(R.id.plgig);
        plgig.setOnClickListener(this);

        tv_adds = (TextView) findViewById(R.id.tv_adds);
        tv_adds.setOnClickListener(this);
    }

    private void HttpQuestIcon() {
        //这里定位成功后首先获取·借伞 界面的雨伞图标
        tyip = new YuSanTuIconPerserent(MainActivity.this);
        tyip.fach("2");
        //这里定位成功后 获取 还伞 界面的图标
        haisanIcon = new HaiYuSanTuIconPerserent(MainActivity.this);
        haisanIcon.haisanIcon("1");
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
                    //startLocation();
                    mLocationTask.startLocate();
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
                //startLocation();
                mLocationTask.startLocate();
            }

        }
    };

    /*
   * 这里是主页弹窗
   * 可能由于业务过于多 导致 直接 加载popupwindow 的话
   * 加载不出来·所以这里延时了一下3秒钟
   * */
    private void popupwindowsss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //主页广告
                initShowZhuYeGuangGao();
            }
        }, 5000);
    }

    /*
    * 显示主要广告
    * */
    private void initShowZhuYeGuangGao() {
        ZhuYeGuangGao zygg = new ZhuYeGuangGao(MainActivity.this);
        zygg.setPopupWindowFullScreen(true);
        zygg.showPopupWindow();
    }

    /*
       * 用户正在使用的雨伞网络请求
       * */
    private void userCurrentYusan() {
        IsUserCurrentPerserent zhengzaishiyongyusan = new IsUserCurrentPerserent(this);
        //这里获取用户正在使用雨伞的 接口回调
        String r_id = ShareUtils.getString(getApplicationContext(), "r_id", "");
        zhengzaishiyongyusan.currents(r_id);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    /*
* 手机震动
* */
    private void shoujizhengdong() {
         /*
         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
         * */
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // 停止 开启 停止 开启
        long[] pattern = {100, 400, 100, 400};
        //重复两次上面的pattern 如果只想震动一次，index设为-1
        vibrator.vibrate(pattern, -1);
    }


    public void dialogss(String title, String messgg) {
        StyledDialog.buildIosAlert("借伞", messgg, new MyDialogListener() {
            @Override
            public void onFirst() {

            }

            @Override
            public void onSecond() {

            }
        }).setBtnText("确定", "").show();
    }

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_backs:
                String zh = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                if (!TextUtils.isEmpty(zh)) {
                    //laitudes,longitudes 当前经纬度
                    Intent intent = new Intent(this, SettingsYusanActivity.class);
//                    intent.putExtra("laitudes", entity.latitue);
//                    intent.putExtra("longitudes", entity.longitude);
                    //启动过场动画
                    AnimUtils.startIntent(intent, view, MainActivity.this, R.id.ll_flayout);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.tv_adds:
                MyPopuopWindowsRigth pwr = new MyPopuopWindowsRigth(this, aMap);
                pwr.showPopupWindow(R.id.tv_adds);
                break;
            case R.id.plgig:
                //1 表示可以点击 2表示不可以点击
                if (isFiestype.equals("2")) {
                    MyDialog.dialog("提示","您正在开锁状态中，请稍后点击该按钮··","确定","");
                } else {
                    initViewes();
                }
                break;


            //右下角的客户返回 按钮
            case R.id.image_kehu:
                Intent intentkehu = new Intent(getApplicationContext(), KeHuFanKuiActivity.class);
                //启动过场动画
                AnimUtils.startIntent(intentkehu, view, MainActivity.this, R.id.ll_xxxx);
                break;
            //左下角的 刷新按钮
            case R.id.image_shuaxin:
                //点击刷新 请求用户正在使用的雨伞个数 左上角的那个
                userCurrentYusan();
                mIsFirstShow = true;
                if (typeHaisan == 1) {
                    a = 2;
                    aMap.clear();
                    //点击还伞再次设置一下·用户当前位子的覆盖物
                    // 然后再次启动定位一下
                    mLocationTask.startLocate();
                    //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                    //给types 赋值为 1 表示· 雨伞的图标
                    types = 1;
                    UmbrellaPresenet upIcon = new UmbrellaPresenet(this);
                    upIcon.fech(MainActivity.this, entity.latitue, entity.longitude, types);
                    //点击借伞后请求网络 图标
//                   tyip.fach("10");

                } else {
                    a = 1;
                    aMap.clear();
                    //点击还伞再次设置一下·用户当前位子的覆盖物
                    // 然后再次启动定位一下
                    mLocationTask.startLocate();
                    //借伞再次请求网络查询 分布的雨伞架子还有多少雨伞
                    //然后把types赋值为2  表示·还伞的字段·用来区分mark 图标
                    types = 2;
                    UmbrellaPresenet upsanjiazi = new UmbrellaPresenet(this);
                    upsanjiazi.fech(MainActivity.this, entity.latitue, entity.longitude, types);
                    //点还伞后 请求 伞架子的 图标
//                    tyip.fach("11");
                }

                break;
            case R.id.tv_current:
                startActivity(new Intent(getApplicationContext(), UsagelogActivity.class));
                break;
                //这个是气泡上的按钮点击事件
                case R.id.img_cesss:
                    MarkerQipao mq = new MarkerQipao(this);
                    mq.showPopupWindow();
                    break;
        }
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
            //服务器返回过程中·这里 禁止掉 扫描按钮的 监听事件
          //  plgig.setOnClickListener(null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
                if (data != null) {
                    stringExtra = data.getStringExtra(Intents.Scan.RESULT);
                    Log.e("扫描结果 ", "" + stringExtra);

                    String zhanghao = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
//                    String mima = ShareUtils.getString(getApplicationContext(), "mima", "");
                    if (!TextUtils.isEmpty(zhanghao)) {
                        //将该字段设置为2 以免用户重复点击扫描按钮
                        isFiestype = "2";
                        //扫描请求借伞
                       UmbrellaPresenet up = new UmbrellaPresenet(this);
                        if (stringExtra.length()==19) {
                            up.binds(stringExtra, zhanghao, this, "2");
                            // 扫描成功后再次请求下网络获取雨伞个数
                         //   getResfh();
                            //开锁中的广告
                            pwgg = new PopupWindowGuanGao(this, stringExtra);
                            pwgg.showPopupWindow();

                        } else if (stringExtra.length() > 19) {
                            String stringExtras = stringExtra.substring(stringExtra.length() - 19);
                            up.binds(stringExtras, zhanghao, this, "2");
                            // 扫描成功后再次请求下网络获取雨伞个数
                         //   getResfh();
                        } else {
                            MyDialog.dialog("提示","无效的二维码","确定","");
                            //将该字段重置为1 让用户可以继续点击扫描按钮
                            isFiestype = "1";
                        }



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
    *    扫描成功后再次请求下网络获取雨伞个数
    *
    * */

    public void getResfh() {
        //开锁后获取雨伞分布
        UmbrellaPresenet getyusanfenbu = new UmbrellaPresenet(this);
        getyusanfenbu.fech(MainActivity.this,entity.latitue, entity.longitude, types);

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

    /*点击线条中间覆盖物显示气泡*/
    private void addMarkers(final LatLng latLng, final String umbrellanubers, final String vacancynumber) {
        //取出借伞图标 的路径地址
        String jiesanUrl =  ShareUtils.getString(getApplicationContext(),"jiesantubiao","");
        Glide.with(MainActivity.this)
                .load(jiesanUrl)
                // 加载网络中的静态图片
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        //绘制覆盖物
                        aMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .snippet(umbrellanubers+","+vacancynumber)

                                .anchor(0.5f, 0.5f)
                                .draggable(true)
                                .icon(BitmapDescriptorFactory.fromBitmap(resource)))
                                .setObject(listInfoWindow);

                    }
                });

    }
}