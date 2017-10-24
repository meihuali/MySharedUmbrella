package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingQueryAutBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingQueryAuthentionPerserent;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsShopingQueryAuthentionView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.whyalwaysmea.circular.AnimUtils;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingDataActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/25 0025 14:48
 * 描述：显示商家资料界面
 */
public class ShoppingDataActivity extends AppCompatActivity implements View.OnClickListener, IsShopingQueryAuthentionView {
    private TextView tv_name,tv_xiangxidizhi;
    private ImageView img_zhizao;
    private ImageView image_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingdata);

        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        initview();
        initData();
    }
    /*
    * 获取数据
    * */
    private void initData() {
        StyledDialog.buildLoading("正在获取数据").show();
        String zhanghao =  ShareUtils.getString(getApplicationContext(),"zhanghao","");
        ShoppingQueryAuthentionPerserent shoppingAut = new ShoppingQueryAuthentionPerserent(this);
        shoppingAut.shoppingAut(zhanghao);
    }

    private void initview() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_xiangxidizhi = (TextView) findViewById(R.id.tv_xiangxidizhi);
        img_zhizao = (ImageView) findViewById(R.id.img_zhizao);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;

        }
    }
    /*
    * 回去商家数据 的接口回调
    * */
    @Override
    public void showComplte(Object object) {
        StyledDialog.dismissLoading();
        ShoppingQueryAutBean shpAut = (ShoppingQueryAutBean) object;
        //获取商家昵称
        String niceName = shpAut.getData().getMerchantname();
        if (!TextUtils.isEmpty(niceName)) {
            tv_name.setText(niceName);
        }

        String address = shpAut.getData().getAddress();
        if (!TextUtils.isEmpty(address)) {
            tv_xiangxidizhi.setText(address);
        }
        String shoppingImg = shpAut.getData().getBusiness_img();
        if (!TextUtils.isEmpty(shoppingImg)) {
            String zhongjian = "public/"+shoppingImg;
            String url = ConfigUtils.ZHU_YU_MING+zhongjian;
            Glide.with(getApplicationContext()).load(url).into(img_zhizao);
        }
    }
}
