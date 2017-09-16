package com.example.administrator.mysharedumbrella01.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.entivity.ManeyBean;
import com.example.administrator.mysharedumbrella01.peresenet.LocatinPeresenet;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.GlideUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanLocationView;
import com.example.administrator.mysharedumbrella01.view.IsWalletManeyView;
import com.gyf.barlibrary.ImmersionBar;
import com.mylhyl.zxing.scanner.common.Intents;
import com.whyalwaysmea.circular.AnimUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/3 0003.
 * 商家个人信息
 */

public class ShoppingShangjiaxinxiActivity extends AppCompatActivity implements View.OnClickListener{
    private CircleImageView image_yuanxing;
    private LinearLayout rll_shangchaunweizi;
    private String scanResult;
    private double laitudes;
    private double longitudes;
    private String zhanghao;
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
    private LinearLayout rl_layout_settings;
    private LinearLayout ll_layout;
    private LinearLayout rl_layout_jilu;
    private LinearLayout ll_layoutGoods;
    private ImageView image_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangjiaxinxi);

        //这句话的意思 将activity添加到Activity管理的方法中以便在别的activity销毁
        BaseAppliction.addDestoryActivity(this,"ShoppingShangjiaxinxiActivity");
        //沉浸式
        ImmersionBar.with(this)
                //  .transparentBar()
                .init();
        initView();

    }
    /*
    * 初始化
    * */
    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        ll_layoutGoods = (LinearLayout) findViewById(R.id.ll_layoutGoods);
        ll_layoutGoods.setOnClickListener(this);
        ll_lyout = (LinearLayout) findViewById(R.id.ll_lyout);
        ll_lyout.setOnClickListener(this);
        rl_layout_jilu = (LinearLayout) findViewById(R.id.rl_layout_jilu);
        rl_layout_jilu.setOnClickListener(this);

        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        ll_layout.setOnClickListener(this);
        image_yuanxing = (CircleImageView) findViewById(R.id.image_yuanxing);
        image_yuanxing.setOnClickListener(this);
        rl_layout_settings = (LinearLayout) findViewById(R.id.rl_layout_settings);
        rl_layout_settings.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //程序进来取出服务器返回的那个头像路径因为用户上传头像到服务器成功过后通过sp保存到本地了，在下面回调中保存了
        String photoImg = ShareUtils.getString(getApplicationContext(),"imgPath","");
        if (!TextUtils.isEmpty(photoImg)) {
            String url = ConfigUtils.ZHU_YU_MING+"public/avatar/"+photoImg;
            Glide.with(getApplicationContext()).load(url).into(image_yuanxing);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.image_yuanxing:
                Intent intent = new Intent(getApplicationContext(), ShoppingShangWuZhongXinActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_layout_settings:
                startActivity(new Intent(getApplicationContext(),ShoppingSettingsActivity.class));
                break;

            case R.id.ll_layout:
                startActivity(new Intent(getApplicationContext(),ShanZuoSanActivity.class));
                break;
            //更改图图片
            case R.id.rl_layout_jilu:
                startActivity(new Intent(getApplicationContext(),UdataImageActivity.class));
                break;
            //商家记录
            case R.id.ll_lyout:
                startActivity(new Intent(getApplicationContext(),ShoppingRecordActivity.class));
                break;
            //获取签收
            case R.id.ll_layoutGoods:
                startActivity(new Intent(getApplicationContext(),ShoppingGoodsReceiptActivity.class));
                break;
        }
    }



}
