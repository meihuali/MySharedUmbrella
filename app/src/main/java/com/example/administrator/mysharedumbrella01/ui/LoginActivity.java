package com.example.administrator.mysharedumbrella01.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dyhdyh.widget.loading.dialog.LoadingDialog;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.CustomDialogFactory;
import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.peresenet.LoginPeresenet;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.utils.MD5Util;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;


/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IsLoginView {
    private Button btn_login;
    private EditTextWithDelete edit_phone; //手机号码
    private EditText edit_pwd; //密码
    private LoginPeresenet lp;
    private String zhanghao,mima;
    private TextView txt_register;
    //忘记密码
    private TextView tv_forget;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.white) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .statusBarDarkFont(true,0.2f)
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
        initData();
    }

    private void initData() {
        lp = new LoginPeresenet(this);
    }

    // 初始化
    private void initView() {
        txt_register = (TextView) findViewById(R.id.txt_register);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 下划线
        tv_forget.setOnClickListener(this);

        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_register.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        edit_phone = (EditTextWithDelete) findViewById(R.id.edit_phone);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        String zhanghaos = ShareUtils.getString(getApplicationContext(),"zhanghao","");
        String mimas = ShareUtils.getString(getApplicationContext(),"mima","");

        if (!TextUtils.isEmpty(zhanghaos) && !TextUtils.isEmpty(mimas)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            return;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //点击登录
            case R.id.btn_login:
                final String phone = edit_phone.getText().toString().trim();
                final String pwd = edit_pwd.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
                    Dialog dialog = LoadingDialog.make(this, new CustomDialogFactory())
                            .setMessage("正在登录中···")//提示消息
                            .create();
                    dialog.show();
                    Handler handler =  new Handler();
                    handler.postDelayed(new Thread() {
                        @Override
                        public void run() {
                            //用MD5 加密工具 加密
                            String pwdone = MD5Util.getStringMD5(pwd);
                            String pwdtwo = MD5Util.getStringMD5(pwdone);
                            lp.fach(phone, pwdtwo);
                            //默认显示登录 圆圈进度
                        }
                    },3000);

                } else {
                    Toast.makeText(getApplicationContext(),"账号或者密码不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            //点击注册
            case R.id.txt_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            //点击忘记密码 找回
            case R.id.tv_forget:
                startActivity(new Intent(this,ModifyPasswordActivity.class));
                break;

        }
    }

    @Override
    public void showLogin(String phone,String password ,LoginBean logindata) {
        zhanghao = phone;
        mima = password;
        if (logindata != null) {
            int status = logindata.getStatus();
            if (status == 1) {
                //取消Loading
                LoadingDialog.cancel();
                Toast.makeText(getApplicationContext(),"登录成功···",Toast.LENGTH_SHORT).show();
                //登录成功后保存账号密码
                ShareUtils.putString(getApplicationContext(),"zhanghao",phone);
                ShareUtils.putString(getApplicationContext(),"mima",password);
                //获取 是否是管理员账号
                LoginBean.DataBean list = logindata.getData();
                int isroot = list.getIsroot();
                //保存 该字段 该字段判断是否为管理员权限
                ShareUtils.putInt(getApplicationContext(),"isroots",isroot);
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


}
