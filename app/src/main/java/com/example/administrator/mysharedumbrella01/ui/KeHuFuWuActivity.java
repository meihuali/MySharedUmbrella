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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.SaoYiSao.ScannerActivity;
import com.example.administrator.mysharedumbrella01.dialog.PopupWindowGuanGao;
import com.example.administrator.mysharedumbrella01.entivity.UserFeedBackBean;
import com.example.administrator.mysharedumbrella01.peresenet.UserFeedBackPerserent;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsUserFeedBackView;
import com.gyf.barlibrary.ImmersionBar;
import com.mylhyl.zxing.scanner.common.Intents;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class KeHuFuWuActivity extends AppCompatActivity implements View.OnClickListener, IsUserFeedBackView {
    private ImageView image_back1;
    private ImageView image_saoyisao;
    private int laserMode  = ScannerActivity.EXTRA_LASER_LINE_MODE_1;
    private TextView tv_type;
    private Button btn_comint;
    private EditText et_body;
    private String stringExtra;
    private PromptDialog promptDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kehufuwu);
        //初始化dialog
        promptDialog = new PromptDialog(this);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        //初始化
        initView();
        //数据获取
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        String kaibuqisuo = intent.getStringExtra("fuwu");
        if (kaibuqisuo.equals("1")) {
            tv_type.setText("开不起锁");
        } else if (kaibuqisuo.equals("2")) {
            tv_type.setText("雨伞故障");
        } else if (kaibuqisuo.equals("3")) {
            tv_type.setText("举报");
        }
    }

    private void initView() {
        et_body = (EditText) findViewById(R.id.et_body);
        btn_comint = (Button) findViewById(R.id.btn_comint);
        btn_comint.setOnClickListener(this);
        tv_type = (TextView)findViewById(R.id.tv_type);
        image_saoyisao =(ImageView)findViewById(R.id.image_saoyisao);
        image_saoyisao.setOnClickListener(this);
        image_back1 = (ImageView) findViewById(R.id.image_back1);
        image_back1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back1:
                finish();
                break;
            case R.id.image_saoyisao:
                initOpenSaoyiSao();
                break;
            //提交问题
            case R.id.btn_comint:
                //获取用户输入的 问题内容
                String body = et_body.getText().toString().toString();
                //获取雨伞架子的二维码对应的 号码
                UserFeedBackPerserent ufbp = new UserFeedBackPerserent(this);
                //获取APPID 其实这里是获取的手机号码 当做是appid 给服务器的
                String shoujihaoma = ShareUtils.getString(getApplicationContext(),"zhanghao","");
                if (!TextUtils.isEmpty(body) ) {
                    if (!TextUtils.isEmpty(shoujihaoma)) {
                        if (!TextUtils.isEmpty(stringExtra)) {
                            ufbp.fach(shoujihaoma, stringExtra, null, "1", body);
                        } else {
                            promptDialog.showError("请扫描雨伞二维码");
                        }
                    } else {
                        promptDialog.showError("请登录账号");
                    }
                } else {
                    promptDialog.showError("请输入反馈内容");
                }
                break;

        }
    }
    /*
    * 动态申请权限
    * */
    private void initOpenSaoyiSao() {
        if (ContextCompat.checkSelfPermission(KeHuFuWuActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(KeHuFuWuActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            ScannerActivity.gotoActivity(KeHuFuWuActivity.this,
                    true, laserMode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
                if (data != null) {
                    stringExtra = data.getStringExtra(Intents.Scan.RESULT);
                    Log.e("扫描结果 " ,""+stringExtra);
                   // Toast.makeText(getApplicationContext(),"扫描结果"+stringExtra,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /*
    *  提交后 返回的结果
    * */
    @Override
    public void showUserFeedBack(Object object) {
        UserFeedBackBean ufbb =  (UserFeedBackBean)object;
        int status = ufbb.getStatus();
        if (status == 1) {
            promptDialog.showSuccess("提交成功");
        }
    }
}
