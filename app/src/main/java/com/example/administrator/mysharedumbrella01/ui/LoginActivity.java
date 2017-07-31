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
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.dialog.CustomDialogFactory;
import com.example.administrator.mysharedumbrella01.entivity.LoginBean;
import com.example.administrator.mysharedumbrella01.entivity.WechatLoginBean;
import com.example.administrator.mysharedumbrella01.peresenet.LoginPeresenet;
import com.example.administrator.mysharedumbrella01.peresenet.WechatPerenest;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.MD5Util;
import com.example.administrator.mysharedumbrella01.utils.MyToast;
import com.example.administrator.mysharedumbrella01.utils.RegularUtil;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;
import com.example.administrator.mysharedumbrella01.view.IsWechatLoginView;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IsLoginView, IsWechatLoginView {
    private Button btn_login;
    private EditTextWithDelete edit_phone; //手机号码
    private EditText edit_pwd; //密码
    private LoginPeresenet lp;
    private String zhanghao,mima;
    private TextView txt_register;
    //忘记密码
    private TextView tv_forget;
    private LinearLayout btn_weixinLogin;
    public  PromptDialog promptDialog;
    private SHARE_MEDIA share_media;
    private boolean isauth;
    private LinearLayout btn_login_QQ;
    private String userImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        BaseAppliction.addDestoryActivity(this,"LoginActivity");
        //初始化下dialog
        promptDialog = new PromptDialog(this);
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
        btn_login_QQ = (LinearLayout) findViewById(R.id.btn_login_QQ);
        btn_login_QQ.setOnClickListener(this);
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
            //点击普通账号登录
            case R.id.btn_login:
                final String phone = edit_phone.getText().toString().trim();
                final String pwd = edit_pwd.getText().toString().trim();
                if (RegularUtil.isMobile(phone)) {
                    if (RegularUtil.isPassword(pwd)) {
                        promptDialog.showLoading("正在登录中···");
//                    promptDialog.showLoading("正在登录中");
                        //用MD5 加密工具 加密
                        String pwdone = MD5Util.getStringMD5(pwd);
                        String pwdtwo = MD5Util.getStringMD5(pwdone);
                        lp.fach(phone, pwdtwo,this);
                    } else {
                        MyToast.toast(getApplicationContext(),"请输入6到20位密码");
                    }
                } else {
                    MyToast.toast(getApplicationContext(),"请输入正确的手机号码");
                }
        /*        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
                    promptDialog.showLoading("正在登录中···");
//                    promptDialog.showLoading("正在登录中");
                    //用MD5 加密工具 加密
                    String pwdone = MD5Util.getStringMD5(pwd);
                    String pwdtwo = MD5Util.getStringMD5(pwdone);
                    lp.fach(phone, pwdtwo,this);
                } else {
                    promptDialog.showError("账号密码不能为空");
                    //Toast.makeText(getApplicationContext(),"账号或者密码不能为空",Toast.LENGTH_SHORT).show();
                }*/
                break;
            //点击注册
            case R.id.txt_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            //点击忘记密码 找回
            case R.id.tv_forget:
                startActivity(new Intent(this,ModifyPasswordActivity.class));
                break;
            /*
            * 第三方微信登录
            * */
            case R.id.btn_weixinLogin:
                promptDialog = new PromptDialog(this);
                //微信授权以及登录
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            /*
            * 第三方QQ 登录
            * */
            case R.id.btn_login_QQ:
                //QQ授权以及登录
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                break;
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            for (String key : data.keySet()) {
                //取出所有的key对应的value 但是这里不包括key，key 需要单独打印出来
                String all = data.get(key);
                Log.e("微信登录1",""+key+all);
            }
            if (data != null) {
                //微信用户名字
                String str = data.get("name");
                //微信用户opid
                String openID = data.get("openid");
                //微信用户头像
                String profile_image_url = data.get("profile_image_url");
                Log.e("微信登录",str);
                //MVP 调用网络请求
                WechatPerenest wp = new WechatPerenest(LoginActivity.this);
                wp.fach(str,profile_image_url,openID);
                promptDialog.showLoading("正在登录···");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            L.e("错误信息  "+t);
            Toast.makeText(getApplicationContext(),"错误信息"+t,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {

        }
    };

    /*
* 微信 QQ 登录接口回调的 结果
* */
    @Override
    public void showLogin(WechatLoginBean wlb) {
        int status = wlb.getStatus();
        if (status == 1) {
            promptDialog.showSuccess("登录成功");
            WechatLoginBean.DataBean wchatbean = wlb.getData();
            //用户名
            String username = wchatbean.getUsername();
            //用户标识符
            String openid = wchatbean.getPhone();
            //用户头像
             userImg = wchatbean.getPhoto();
            //用户微信登录后 的金额
            String money = wchatbean.getMoney();
            //获取用户登录的openid
            String phones = wchatbean.getPhone();
            //授权成功保存openID 这里跟真实手机号码一样保存同一个key
         //   ShareUtils.putString(getApplicationContext(), "zhanghao", openid);
            //保存用户名字
            ShareUtils.putString(getApplicationContext(), "username", username);
            //保存用户头像
            ShareUtils.putString(getApplicationContext(), "touxiangURL", userImg);
            //保存用户微信登录号上的金额
            ShareUtils.putString(getApplicationContext(),"wechatMoney",money);
            //获取mobileID
            String mobileID = wchatbean.getMobilephone();
            //获取r_id
            String r_id = wchatbean.getR_id();
            if (mobileID.length() == 11) {
                ShareUtils.putString(getApplicationContext(),"zhanghao",phones);
                finish();
            } else {
                //然后跳转到手机验证界面
                Intent intent = new Intent(LoginActivity.this, BangDingZhangHaoActivity.class);
                intent.putExtra("r_id", r_id);
                intent.putExtra("userImg",userImg);
                //特殊处理
                intent.putExtra("zhanghao",phones);
                startActivity(intent);
            }
//            finish();
        } else {
            promptDialog.showError("登录失败···");
        }
    }


    /*
    * 这里是普通账号登录返回的 接口 回调
    * */
    @Override
    public void showLogin(String phone,String password ,LoginBean logindata) {
        zhanghao = phone;
        mima = password;
        if (logindata != null) {
            int status = logindata.getStatus();
            L.e("普通账号返回接口 "+status);

            if (status == 1) {
                promptDialog.dismiss();
                //登录成功后保存账号密码
                ShareUtils.putString(getApplicationContext(),"zhanghao",phone);
                ShareUtils.putString(getApplicationContext(),"mima",password);
                //获取 是否是管理员账号
                LoginBean.DataBean list = logindata.getData();
                //获取登录返回的用户名
                String username = list.getUsername();
                if (!TextUtils.isEmpty(username)) {
                    //保存用户名
                    ShareUtils.putString(getApplicationContext(), "username", username);
                } else {
                    ShareUtils.putString(getApplicationContext(), "username", "");
                }
                //获取登录后返回的头像
                String  url = list.getPhoto();
                if (!url.equals("0")) {
                    String urls =  ConfigUtils.ZHU_YU_MING+"public/avatar/"+url;
                    ShareUtils.putString(getApplicationContext(),"touxiangURL",urls);
                }
                int isroot = list.getIsroot();
                //保存 该字段 该字段判断是否为管理员权限
                ShareUtils.putInt(getApplicationContext(),"isroots",isroot);
//                Intent intent = new Intent(this,MainActivity.class);
//                startActivity(intent);
                finish();
            } else if (status == 2) {
                promptDialog.dismiss();
                promptDialog.showError("密码错误");
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
