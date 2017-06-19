package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ManeyBean;
import com.example.administrator.mysharedumbrella01.peresenet.WalletManeyPerserent;
import com.example.administrator.mysharedumbrella01.view.IsWalletManeyView;
import com.example.administrator.mysharedumbrella01.wxapi.WXPayEntryActivity;
import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.peresenet.LocatinPeresenet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanLocationView;
import com.example.administrator.mysharedumbrella01.wxapi.WxPayUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.List;

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
    private String money;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsyusana);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }

    private void initView() {
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
                finish();
                break;
            //历史记录
            case R.id.rl_layout_jilu:
                startActivity(new Intent(this, UsagelogActivity.class));
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

    /*启动扫描二维码*/
    private void initViewes() {
        //打开扫描界面扫描条形码或二维码
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描后 回调结果
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            //获取扫描后的结果
            scanResult = bundle.getString("result");
            Toast.makeText(getApplicationContext(),"账号："+zhanghao+"二维码结果"+scanResult,Toast.LENGTH_SHORT).show();
            L.e("scanResult+zhanghao "+scanResult+zhanghao);
            LocatinPeresenet lps = new LocatinPeresenet(this);
            lps.fact(laitudes,longitudes,zhanghao,scanResult);
//            finish();

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
}
