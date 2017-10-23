package com.example.administrator.mysharedumbrella01.ui;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.SaoYiSao.ScannerActivity;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.entivity.ManeyBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingQueryAutBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingQueryAuthentionPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.WalletManeyPerserent;
import com.example.administrator.mysharedumbrella01.transition.Utilss;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.GlideUtils;
import com.example.administrator.mysharedumbrella01.utils.MyDialog;
import com.example.administrator.mysharedumbrella01.utils.NetWorkUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsShopingQueryAuthentionView;
import com.example.administrator.mysharedumbrella01.view.IsWalletManeyView;
import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.peresenet.LocatinPeresenet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanLocationView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.mylhyl.zxing.scanner.common.Intents;
import com.whyalwaysmea.circular.AnimUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/3 0003.
 * 个人设置界面
 */

public class SettingsYusanActivity extends AppCompatActivity implements View.OnClickListener, IsLoginView,IsShangChuanLocationView, IsWalletManeyView, IsShopingQueryAuthentionView {
    private ImageView image_back;
    private LinearLayout rl_layout_jilu, rl_layout_settings;
    private CircleImageView image_yuanxing;
    private LinearLayout rll_shangchaunweizi;
    private String scanResult;
    private double laitudes;
    private double longitudes;
    private String zhanghao;
    private LinearLayout ll_layout;
    private LinearLayout rll_Invitingfriends;
    //账户金额
    private TextView tv_jiner;
    //账户押金
    private String deposit;
    private int laserMode;
    private String money;
    private TextView tv_name;
    //过场动画声明
    private View ll_layoutss;
    private RelativeLayout rl_layoutssss;
    private View  sv_layout;
    private LinearLayout ll_lyout;
    private String imageurl;
    private LinearLayout rl_layout_shopping;
    private String is_aut;
    private View ll_layout_amin;
    private   View v;
    private View ll_layout_yajin;
    private LinearLayout rl_layout_isroot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsyusana);

        //这句话的意思 将activity添加到Activity管理的方法中以便在别的activity销毁
        BaseAppliction.addDestoryActivity(this,"SettingsYusanActivity");
        //沉浸式
        ImmersionBar.with(this)
                .init();
        initView();
    }


    private void initView() {

        rl_layout_isroot = (LinearLayout) findViewById(R.id.rl_layout_isroot);
        rl_layout_isroot.setOnClickListener(this);
        //程序进来判断 该字段是否为 管理员
        int isAdministrotar = ShareUtils.getInt(getApplicationContext(),"isAdministrotar",0);
        if (isAdministrotar == 1) { //0 表示 非管理员
            rl_layout_isroot.setVisibility(View.VISIBLE);
        }
        rl_layout_shopping = (LinearLayout) findViewById(R.id.rl_layout_shopping);
        rl_layout_shopping.setOnClickListener(this);
        ll_lyout = (LinearLayout) findViewById(R.id.ll_lyout);
        ll_lyout.setOnClickListener(this);

        //这里是过渡动画 ll_layoutss
        AnimUtils.animhpel((Activity) this,R.id.ll_layoutss);

        tv_name = (TextView) findViewById(R.id.tv_name);
        //程序进来取出用户名来显示 不管是微信还是 QQ 还是普通用户都要取
        String username = ShareUtils.getString(getApplicationContext(),"username","");
        tv_name.setText(username);

        tv_jiner = (TextView) findViewById(R.id.tv_jiner);
        rll_Invitingfriends = (LinearLayout) findViewById(R.id.rll_Invitingfriends);
        rll_Invitingfriends.setOnClickListener(this);
        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        ll_layout.setOnClickListener(this);

        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        rl_layout_jilu = (LinearLayout) findViewById(R.id.rl_layout_jilu);
        rl_layout_jilu.setOnClickListener(this);
        rl_layout_settings = (LinearLayout) findViewById(R.id.rl_layout_settings);
        rl_layout_settings.setOnClickListener(this);
        image_yuanxing = (CircleImageView) findViewById(R.id.image_yuanxing);
        image_yuanxing.setOnClickListener(this);
        //设置图片到控件上 这里是上传头像的那个
        //进来该界面的时候去取图片路径设置在控件上
        updataphoto();
        rll_shangchaunweizi = (LinearLayout) findViewById(R.id.rll_shangchaunweizi);
        rll_shangchaunweizi.setOnClickListener(this);
        //获取主界面传过来的 经纬度
        initDatas();
    }
    /*
    * 上传头像到控件上
    * */
    private void updataphoto() {
        imageurl = ShareUtils.getString(getApplicationContext(),"touxiangURL","");
        if (!TextUtils.isEmpty(imageurl)) {
            if (imageurl.equals("0")) {
                //这里表示用户第一次登录并没有上传头像
                Glide.with(getApplicationContext()).load(R.drawable.liuyifei).into(image_yuanxing);
                return;
            }
            GlideUtils.loadImageViewCache(getApplicationContext(), imageurl, image_yuanxing);
            if (imageurl.contains("http")) {
                Glide.with(this).load(imageurl).error(R.drawable.liuyifei).into(image_yuanxing);
            } else {
                String url = ConfigUtils.ZHU_YU_MING + "public/avatar/" + imageurl;
                Glide.with(this).load(url).error(R.drawable.liuyifei).into(image_yuanxing);
            }
        } else {
            //这里表示用户第一次登录并没有上传头像
            Glide.with(getApplicationContext()).load(R.drawable.liuyifei).into(image_yuanxing);
        }
    }

    private void initDatas() {
        //laitudes,longitudes 当前经纬度
        Intent intent = getIntent();
        laitudes = intent.getDoubleExtra("laitudes", 0.00);
        longitudes = intent.getDoubleExtra("longitudes", 0.00);
        //在取出管理员账号
        zhanghao = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
        //获取钱包金额
/*        WalletManeyPerserent wmp = new WalletManeyPerserent(this);
        wmp.fach(this);*/
        //获取登录账号返回得字段判断是否为管理员
        int isroots = ShareUtils.getInt(getApplicationContext(),"isroots",0);
        if (isroots != 1) { // 1 代表是管理员登录 如果不等于 1 代表是普通用户
            rll_shangchaunweizi.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        L.e("走起 "+"onResume");
        updataphoto();

        //这里取出保存的入驻商家的字段
        is_aut =  ShareUtils.getString(getApplicationContext(),"is_aut","");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //返回上一个界面 ll_layoutss
            case R.id.image_back:
                AnimUtils.finishAmins(SettingsYusanActivity.this,R.id.rl_layoutssss,view,R.id.ll_layoutss);
                break;
            //历史记录
            case R.id.rl_layout_jilu:
                int isroot = ShareUtils.getInt(getApplicationContext(),"isroots",0);
                if (isroot == 1) { //1 表示是管理的历史记录
                    Toast.makeText(getApplicationContext(),"管理员暂时没有历史记录",Toast.LENGTH_SHORT).show();
                } else {
                    //否则表示是 普通用户的 历史记录
                    // startActivity(new Intent(this, UsagelogActivity.class));
                    Intent intent = new Intent(getApplicationContext(),UsagelogActivity.class);
                    AnimUtils.startIntent(intent,view, (Activity) SettingsYusanActivity.this,R.id.rl_layout_jilu);
                }

                break;
            //设置界面
            case R.id.rl_layout_settings:
                // startActivity(new Intent(this, settingsssssActivity.class));
                Intent intent1 = new Intent(this,settingsssssActivity.class);
                AnimUtils.startIntent(intent1,view, (Activity)this,R.id.ll_layoutss);

                break;
            case R.id.image_yuanxing:
                Intent intent2 = new Intent(this,YuanXingTouxiangSettingsActivity.class);
                AnimUtils.startIntent(intent2,view, (Activity)SettingsYusanActivity.this,R.id.ll_layout_amin);
                break;
            //管理里账号上传 地理位子
            case R.id.rll_shangchaunweizi:
                //扫描二维码 获取设备号
                initViewes();
                break;
            //我的钱包
            case R.id.ll_layout:
                Intent intent = new Intent(this,MyWalletActivity.class);
                intent.putExtra("yajin",deposit);
                intent.putExtra("yuer", money);
                //  AnimUtils.startIntent(intent,view,this,R.id.ll_layout);
                AnimUtils.startIntent(intent,view,this,R.id.sv_layout);
                break;
            //邀请好友
            case R.id.rll_Invitingfriends:
                startActivity(new Intent(this,InvitingFriendsAcitivity.class));
                break;
            //联系客户
            case R.id.ll_lyout:
                // startActivity(new Intent(this,LianXiKeFuActivity.class));
                Intent intent3 = new Intent(SettingsYusanActivity.this,LianXiKeFuActivity.class);
                AnimUtils.startIntent(intent3,view, (Activity) SettingsYusanActivity.this,R.id.ll_lyout);
                break;
            //这里点击 跳转到商家界面
            case R.id.rl_layout_shopping:
                if (NetWorkUtils.isNetworkConnected(getApplicationContext())) {
                    StyledDialog.buildLoading("加载中···").show();
                /* 这里入驻商家
                * 网络请求获取该用户是否认证商家通过
                * */
                    ShoppingQueryAuthentionPerserent shoppingAut = new ShoppingQueryAuthentionPerserent(this);
                    shoppingAut.shoppingAut(zhanghao);
                    v = view;
                } else {
                    MyDialog.dialog("提示","无网络，请检查当前网络状态","确认","");
                }
                break;
                //跳转到管理员界面
            case R.id.rl_layout_isroot:
                    startActivity(new Intent(getApplicationContext(),AdimIstratorActivity.class));
                break;

        }
    }


    @Override
    public void showLoction(double laitudes, double longitudes,  String zhanghao, String scanResult) {

    }

    @Override
    public void showLogin(String phone, String password, LoginBean logindata) {

    }

    @Override
    public void showLoginError() {

    }

    @Override
    public void showManey(ManeyBean.DataBean list) {
        //获取钱包金额
        money =  list.getMoney();
        tv_jiner.setText(money+"元");
        deposit =  list.getDeposit();
    }

    /*启动扫描二维码*/
    private void initViewes() {
        //打开扫描界面扫描条形码或二维码
        if (ContextCompat.checkSelfPermission(SettingsYusanActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(SettingsYusanActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            ScannerActivity.gotoActivity(SettingsYusanActivity.this,
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
                    Log.e("扫描结果 " ,""+stringExtra);
                    Toast.makeText(getApplicationContext(),"账号："+zhanghao+"二维码结果"+scanResult,Toast.LENGTH_SHORT).show();
                    L.e("scanResult+zhanghao "+scanResult+zhanghao);
                    LocatinPeresenet lps = new LocatinPeresenet(this);
                    lps.fact(laitudes,longitudes,zhanghao,scanResult);

                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        AnimUtils.finishonBackPressed(SettingsYusanActivity.this,R.id.ll_layoutss);
    }
    /*
    * 该接口用来查询是否认证过了商家
    * */
    @Override
    public void showComplte(Object object) {
        StyledDialog.dismissLoading();
        ShoppingQueryAutBean shpAut = (ShoppingQueryAutBean) object;
        int status = shpAut.getStatus();
        if (status == 1) {
            ShoppingQueryAutBean.DataBean spaut = shpAut.getData();
            //获取商家认证的字段
            String aut = spaut.getIs_Authentication();

            if (aut.equals("1")) { // 1表示 已经认证
                String shoppingId = spaut.getId();
                ShareUtils.putString(getApplicationContext(),"shoppingId",shoppingId);
                Intent intent = new Intent(SettingsYusanActivity.this,ShoppingShangjiaxinxiActivity.class);
                AnimUtils.startIntent(intent,v, (Activity)this,R.id.rl_layout_shopping);

            } else if (aut.equals("2")) { // 2表示正在认证中
                StyledDialog.buildIosAlert("商家认证", "您的资料我们已经收到，我们将在24小时内为您审核通过，谢谢！", new MyDialogListener() {
                    @Override
                    public void onFirst() { //这里是确定

                    }

                    @Override
                    public void onSecond() {

                    }


                }).setBtnText("确定", "取消").show();
            } else { //否则就是0 表示还未证人 直接跳转到认证提交资料的界面
                startActivity(new Intent(getApplicationContext(), ShoppingSettledDataActivity.class));
            }
        } else if (status == 0) {
            startActivity(new Intent(getApplicationContext(), ShoppingSettledDataActivity.class));
        }
    }
}
