package com.example.administrator.mysharedumbrella01.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dyhdyh.widget.loading.dialog.LoadingDialog;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.CustomDialogFactory;
import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.peresenet.LoginPeresenet;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.MD5Util;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.leefeng.promptlibrary.PromptDialog;



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
    private LinearLayout btn_weixinLogin;
    private PromptDialog promptDialog;
    private SHARE_MEDIA share_media;
    private boolean isauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        isauth = UMShareAPI.get(this).isAuthorize(this, SHARE_MEDIA.WEIXIN);
    }

    // 初始化
    private void initView() {
        btn_weixinLogin = (LinearLayout) findViewById(R.id.btn_weixinLogin);
        btn_weixinLogin.setOnClickListener(this);
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
            //第三方微信登录
            case R.id.btn_weixinLogin:
                promptDialog = new PromptDialog(this);
                //微信授权以及登录
//                if (isauth) {
//                    UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, authListener);
//                } else {
//                    UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
                    UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
//                }
                break;
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

//            for (String key : data.keySet()) {
//                //取出所有的key对应的value 但是这里不包括key，key 需要单独打印出来
//                String all = data.get(key);
//                Log.e("微信登录",""+key+all);
//            }
            if (data != null) {
                //微信用户名字
                String str = data.get("name");
                //微信用户opid
                String openID = data.get("openid");
                //微信用户头像
                String profile_image_url = data.get("profile_image_url");
                Log.e("微信登录",str);
                //授权成功保存openID 这里跟真实手机号码一样保存同一个key
                ShareUtils.putString(getApplicationContext(),"zhanghao",openID);
                //然后跳转到主界面
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(),"错误信息"+t,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {

        }
    };



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
                String  url = list.getPhoto();
                if (!TextUtils.isEmpty(url)) {
                    String urls =  ConfigUtils.ZHU_YU_MING+"public/avatar/"+url;
                    ShareUtils.putString(getApplicationContext(),"touxiangURL",urls);
                }
                int isroot = list.getIsroot();
                //保存 该字段 该字段判断是否为管理员权限
                ShareUtils.putInt(getApplicationContext(),"isroots",isroot);
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

}
