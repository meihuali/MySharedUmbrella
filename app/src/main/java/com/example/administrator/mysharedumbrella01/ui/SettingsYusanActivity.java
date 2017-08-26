package com.example.administrator.mysharedumbrella01.ui;

import android.Manifest;
import android.app.Activity;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.SaoYiSao.ScannerActivity;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.entivity.ManeyBean;
import com.example.administrator.mysharedumbrella01.peresenet.WalletManeyPerserent;
import com.example.administrator.mysharedumbrella01.transition.Utilss;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.GlideUtils;
import com.example.administrator.mysharedumbrella01.view.IsWalletManeyView;
import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.peresenet.LocatinPeresenet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanLocationView;
import com.gyf.barlibrary.ImmersionBar;
import com.mylhyl.zxing.scanner.common.Intents;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/3 0003.
 * 个人设置界面
 */

public class SettingsYusanActivity extends AppCompatActivity implements View.OnClickListener, IsLoginView,IsShangChuanLocationView, IsWalletManeyView {
    private ImageView image_back;
    private RelativeLayout rl_layout_jilu, rl_layout_settings;
    private CircleImageView image_yuanxing;
    private RelativeLayout rll_shangchaunweizi;
    private String scanResult;
    private double laitudes;
    private double longitudes;
    private String zhanghao;
    private RelativeLayout ll_layout;
    private RelativeLayout rll_Invitingfriends;
    //账户金额
    private TextView tv_jiner;
    //账户押金
    private String deposit;
    private int laserMode;
    private String money;
    private TextView tv_name;
    //过场动画声明
   private View ll_layout_a;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsyusana);
        //过渡动画
        initTranstiosn();

        //这句话的意思 将activity添加到Activity管理的方法中以便在别的activity销毁
        BaseAppliction.addDestoryActivity(this,"SettingsYusanActivity");
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        initView();
    }
    /*
    * 过渡动画
    * */
    private void initTranstiosn() {
        ll_layout_a = findViewById(R.id.ll_layoutss);
        //进入动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setSharedElementEnterTransition(Utilss.buildShareElemEnterSet(ll_layout_a,R.id.ll_layoutss));
        }
        //返回动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementReturnTransition(Utilss.buildShareElemReturnSet(ll_layout_a,R.id.ll_layoutss));
        }
    }

    private void initView() {

        tv_name = (TextView) findViewById(R.id.tv_name);
        //程序进来取出用户名来显示 不管是微信还是 QQ 还是普通用户都要取
        String username = ShareUtils.getString(getApplicationContext(),"username","");
        tv_name.setText(username);

        tv_jiner = (TextView) findViewById(R.id.tv_jiner);
        rll_Invitingfriends = (RelativeLayout) findViewById(R.id.rll_Invitingfriends);
        rll_Invitingfriends.setOnClickListener(this);
        ll_layout = (RelativeLayout) findViewById(R.id.ll_layout);
        ll_layout.setOnClickListener(this);

        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        rl_layout_jilu = (RelativeLayout) findViewById(R.id.rl_layout_jilu);
        rl_layout_jilu.setOnClickListener(this);
        rl_layout_settings = (RelativeLayout) findViewById(R.id.rl_layout_settings);
        rl_layout_settings.setOnClickListener(this);
        image_yuanxing = (CircleImageView) findViewById(R.id.image_yuanxing);
        image_yuanxing.setOnClickListener(this);
        //进来该界面的时候去取图片路径设置在控件上
        String  imageurl = ShareUtils.getString(getApplicationContext(),"touxiangURL","");
        if (!TextUtils.isEmpty(imageurl)) {
            if (imageurl.contains("http")) {
                GlideUtils.loadImageViewCache(getApplicationContext(), imageurl, image_yuanxing);
            } else {
                String url = ConfigUtils.ZHU_YU_MING+"public/avatar/"+imageurl;
                GlideUtils.loadImageViewCache(getApplicationContext(), url, image_yuanxing);
            }
        }
        rll_shangchaunweizi = (RelativeLayout) findViewById(R.id.rll_shangchaunweizi);
        rll_shangchaunweizi.setOnClickListener(this);
        //获取主界面传过来的 经纬度
        initDatas();
    }

    private void initDatas() {
        //laitudes,longitudes 当前经纬度
        Intent intent = getIntent();
        laitudes = intent.getDoubleExtra("laitudes", 0.00);
        longitudes = intent.getDoubleExtra("longitudes", 0.00);
        //在取出管理员账号
        zhanghao = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
        //获取钱包金额
        WalletManeyPerserent wmp = new WalletManeyPerserent(this);
        wmp.fach(this);
        //获取登录账号返回得字段判断是否为管理员
        int isroots = ShareUtils.getInt(getApplicationContext(),"isroots",0);
        if (isroots != 1) { // 1 代表是管理员登录 如果不等于 1 代表是普通用户
            rll_shangchaunweizi.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
             //   finish();
                //这里finish 是有过渡动画的
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
                break;
            //历史记录
            case R.id.rl_layout_jilu:
                int isroot = ShareUtils.getInt(getApplicationContext(),"isroots",0);
                if (isroot == 1) { //1 表示是管理的历史记录
                    Toast.makeText(getApplicationContext(),"管理员暂时没有历史记录",Toast.LENGTH_SHORT).show();
                } else {
                    //否则表示是 普通用户的 历史记录
                    startActivity(new Intent(this, UsagelogActivity.class));
                }

                break;
            //设置界面
            case R.id.rl_layout_settings:
                startActivity(new Intent(this, settingsssssActivity.class));
                break;
            case R.id.image_yuanxing:
                startActivity(new Intent(this, YuanXingTouxiangSettingsActivity.class));
                break;
            //管理里账号上传 地理位子
            case R.id.rll_shangchaunweizi:
                //扫描二维码 获取设备号
                initViewes();
                break;
            //我的钱包
            case R.id.ll_layout:
            /*    PopUpWindowBootom pwb = new PopUpWindowBootom(this);
                pwb.showPopupWindow();*/
                Intent intent = new Intent(this,MyWalletActivity.class);
                intent.putExtra("moneysss",deposit);
                intent.putExtra("yuer", money);
                startActivity(intent);
                break;
            //邀请好友
            case R.id.rll_Invitingfriends:
                startActivity(new Intent(this,InvitingFriendsAcitivity.class));
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
}
