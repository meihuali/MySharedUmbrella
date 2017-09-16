package com.example.administrator.mysharedumbrella01.ui;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingCallback;
import com.bilibili.boxing.loader.IBoxingCrop;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.ImageCompressor;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.dialog.PopupShopping;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingAutsaBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingUserRegisterBean;
import com.example.administrator.mysharedumbrella01.peresenet.AuthenticationPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingUserRegisterPersernt;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsAuthenticationView;
import com.example.administrator.mysharedumbrella01.view.IsShoppingUserRegisterView;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShangJiaRenZhengActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/7 0007 14:56
 * 描述：商家认证界面
 */
public class ShangJiaRenZhengActivity extends AppCompatActivity implements View.OnClickListener, IsAuthenticationView {
    private ImageView img_open_photo;
    private static final int REQUEST_CODE = 1024;
    private String pathImg;
    private ImageView img_user_tu;
    private String dianming,ed_phone,ed_shoppingAddres,shoujihaoma,yanzm,et_pasword;
    private Button btn_comints;
    private EditText et_address;
    private String reysd;
    private PromptDialog promptDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangjiarenzheng);
        //沉浸式
        ImmersionBar.with(this)
                .init();
        initView();
        //这里获取上一个界面的数据
        initGetData();
        //添加到栈中
        BaseAppliction.addDestoryActivity(this,"ShangJiaRenZhengActivity");
        promptDialog = new PromptDialog(this);
    }

    /*
    * 这里获取上一个界面的数据
    * */
    private void initGetData() {
        Intent intent = getIntent();
        //店名
        dianming = intent.getStringExtra("ed_dianName");
        //电话号码
        ed_phone = intent.getStringExtra("ed_phone");
        //地址
        ed_shoppingAddres = intent.getStringExtra("ed_shoppingAddres");
        //手机号码
        shoujihaoma = intent.getStringExtra("shoujihaoma");
        //验证码
        yanzm = intent.getStringExtra("yanzm");
        //获取密码
        et_pasword = intent.getStringExtra("et_pasword");
    }

    /*
    * 初始化
    * */
    private void initView() {
        et_address = (EditText) findViewById(R.id.et_address);
        img_open_photo = (ImageView) findViewById(R.id.img_open_photo);
        img_open_photo.setOnClickListener(this);
        img_user_tu = (ImageView) findViewById(R.id.img_user_tu);
        btn_comints = (Button) findViewById(R.id.btn_comints);
        btn_comints.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_open_photo:
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needCamera(R.drawable.ic_boxing_camera_white).needGif().withMaxCount(1);
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, REQUEST_CODE);
                break;
            //点击提交商家资料 请求服务器 去认证
            case R.id.btn_comints:
                promptDialog.showLoading("资料提交中···");
                confimfirService();
                break;
        }
    }
    /*
    * 请求服务器注册 商家账号
    * */
    private void confimfirService() {
        AuthenticationPerserent shangjiarenzhen = new AuthenticationPerserent(this);
        //这里取出商家的普通账号
        String shoppingZh = ShareUtils.getString(getApplicationContext(),"phone","");
        //获取商家输入的地址
        String shoppingAdress = et_address.getText().toString().trim();
        shangjiarenzhen.Shangjiarenzheng(shoppingZh,shoppingAdress,pathImg);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final ArrayList<BaseMedia> medias = Boxing.getResult(data);
            L.e("选择图片的结果 "+medias.size());
            for (int i = 0; i < medias.size(); i++) {
                pathImg = medias.get(i).getPath();
            }
            img_open_photo.setVisibility(View.GONE);
            //这里将图片设置在控件上
            BoxingMediaLoader.getInstance().displayThumbnail(img_user_tu, pathImg, 400, 220);
        }
    }

    /*
    * 商家认证结果回调
    * */
    @Override
    public void showAuthenRelust(Object object) {
        ShoppingAutsaBean sab = (ShoppingAutsaBean) object;
        int status = sab.getStatus();
        if (status == 1) {
            promptDialog.dismiss();
            ToastUtil.showShortToast(getApplicationContext(), sab.getData().getSuccess());
            String is_aut =  sab.getIs_Authentication();
            if (is_aut.equals("2")) {
                ShareUtils.putString(getApplicationContext(),"is_aut",is_aut);
            }
            finish();
        } else {
            promptDialog.dismiss();
            ToastUtil.showShortToast(getApplicationContext(), sab.getData().getSuccess());
            finish();
        }
    }
    /*
    * 商家认证结果失败回调
    * */
    @Override
    public void showErrorRelust() {
        promptDialog.dismiss();
        ToastUtil.showShortToast(getApplicationContext(), "认证失败，服务器访问走Error！");
    }


}
