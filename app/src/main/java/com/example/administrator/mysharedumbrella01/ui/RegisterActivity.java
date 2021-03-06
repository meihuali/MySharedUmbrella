package com.example.administrator.mysharedumbrella01.ui;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.RegisterBean;
import com.example.administrator.mysharedumbrella01.peresenet.RegisterPrestenet;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.utils.MyToast;
import com.example.administrator.mysharedumbrella01.utils.NetWorkUtils;
import com.example.administrator.mysharedumbrella01.utils.RegularUtil;
import com.example.administrator.mysharedumbrella01.view.IsRegisterView;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IsRegisterView {
    //显示 隐藏密码的按钮
    private ImageView img_eye;
    private boolean isSeen = false,isRead = true;
    private TextView remyback;
    //手机号码
    private EditText edit_phone;
    //用户设置都密码
    private EditText edit_pwd;
    //用户添加的验证码
    private EditText edit_verifycode;
    //用户姓名
    private  EditText ed_names;
    //注册按钮
    private Button btn_register;
    //注册下面按钮的 那个关于详细阅读说明 选择框
    private ImageView ck;
    //点击按钮获取短信验证码
    private TextView tv_hqyzm;
private ImageView image_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regster);

        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .statusBarDarkFont(true,0.2f)
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }
    /*初始化控件*/
    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        //获取验证码
        edit_verifycode = (EditText) findViewById(R.id.edit_verifycode);
        tv_hqyzm = (TextView) findViewById(R.id.tv_hqyzm);
        tv_hqyzm.setOnClickListener(this);
        ck=(ImageView)findViewById(R.id.ck);
        ck.setOnClickListener(this);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_phone.setOnClickListener(this);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        edit_pwd.setOnClickListener(this);
        edit_verifycode = (EditText) findViewById(R.id.edit_verifycode);
        edit_verifycode.setOnClickListener(this);
        ed_names = (EditText) findViewById(R.id.ed_names);
        ed_names.setOnClickListener(this);
//         remyback = (TextView) findViewById(R.id.remyback);
//        remyback.setOnClickListener(this);
//        img_eye = (ImageView) findViewById(R.id.img_eye);
//        img_eye.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
//            case R.id.remyback:
//                finish();
//                break;
            //显示隐藏账号密码
            case R.id.img_eye:
                if (isSeen == false) {
                    isSeen = true;
                    edit_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    img_eye.setImageResource(R.drawable.eye_right);
                } else {
                    isSeen = false;
                    edit_pwd.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_eye.setImageResource(R.drawable.eye);
                }
                break;
            //点击注册
            case R.id.btn_register:
                String ed_phone = edit_phone.getText().toString().trim();
                String ed_pwd = edit_pwd.getText().toString().trim();
                String ed_verifycodes = edit_verifycode.getText().toString().trim();
                String ed_name = ed_names.getText().toString().trim();

                if (RegularUtil.isMobile(ed_phone)) {
                    if (RegularUtil.isPassword(ed_pwd)) {
                        if (RegularUtil.isPhoneValidateCode(ed_verifycodes)) {
                            if (RegularUtil.isUserNick(ed_name)) {
                                RegisterPrestenet regist = new RegisterPrestenet(RegisterActivity.this);
                                regist.fact(ed_phone,ed_pwd,ed_name,ed_verifycodes);
                            } else {
                                MyToast.toast(getApplicationContext(),"昵称请用中文或者ABC字母");
                            }
                        } else {
                            MyToast.toast(getApplicationContext(),"请输入4位数验证码！");
                        }
                    } else {
                        MyToast.toast(getApplicationContext(),"请输入6到20位密码！");
                    }
                } else {
                    MyToast.toast(getApplicationContext(),"请输入11位手机号码！");
                }

                break;
            //登录下面的 关于阅读说明的 选择框
            case R.id.ck:
                isRead = !isRead;
                if (isRead) {
                    ck.setImageDrawable(getResources().getDrawable(
                            R.drawable.choice_y));
                } else {
                    ck.setImageDrawable(getResources().getDrawable(
                            R.drawable.choice_n));
                }
                break;
            //点击获取短信验证码
            case R.id.tv_hqyzm:
                getSMS();
                break;
        }
    }

    private void init() {
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        //goMainActivity();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }

                } else {
                    ((Throwable) data).printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplication(), "验证失败", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        };
        //注册信息回调
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void getSMS() {
        String ed_phone = edit_phone.getText().toString().trim();
        if (TextUtils.isEmpty(ed_phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_phone.length() != 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "验证码已发送，请注意查看！", Toast.LENGTH_SHORT).show();
        SMSSDK.getVerificationCode("86", ed_phone);
    }

    @Override
    public void showUmbrella(RegisterBean rb) {
        //注册成功 后的回调
        int status = rb.getStatus();
        if (status == 1) {
            Toast.makeText(this, "恭喜您注册成功", Toast.LENGTH_SHORT).show();
            finish();
        } else{
            Toast.makeText(this,rb.getData(),Toast.LENGTH_SHORT).show();
        }
    }
}
