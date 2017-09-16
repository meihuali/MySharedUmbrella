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
import com.example.administrator.mysharedumbrella01.entivity.ShoppingLoginBean;
import com.example.administrator.mysharedumbrella01.entivity.WechatLoginBean;
import com.example.administrator.mysharedumbrella01.peresenet.LoginPeresenet;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingLoginPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.WechatPerenest;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.MD5Util;
import com.example.administrator.mysharedumbrella01.utils.MyToast;
import com.example.administrator.mysharedumbrella01.utils.RegularUtil;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.StaticClass;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsLoginView;
import com.example.administrator.mysharedumbrella01.view.IsShoppingLoginView;
import com.example.administrator.mysharedumbrella01.view.IsWechatLoginView;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.igexin.sdk.PushManager;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.leefeng.promptlibrary.PromptDialog;



/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IsLoginView, IsWechatLoginView, IsShoppingLoginView {
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
    private String r_id;
    //商家注册按钮
    private TextView txt_shpngregister;
    private String phone,pwd;
    //商家登录
    private Button btn_shpoinglogin;
    private String type;
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
        btn_shpoinglogin = (Button) findViewById(R.id.btn_shpoinglogin);
        btn_shpoinglogin.setOnClickListener(this);
        txt_shpngregister = (TextView) findViewById(R.id.txt_shpngregister);
        txt_shpngregister.setOnClickListener(this);
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
                phone = edit_phone.getText().toString().trim();
                pwd = edit_pwd.getText().toString().trim();

                if (RegularUtil.isMobile(phone)) {
                    if (RegularUtil.isPassword(pwd)) {
                        promptDialog.showLoading("正在登录中···");

                        lp.fach(phone, pwd,this);
                    } else {
                        MyToast.toast(getApplicationContext(),"请输入6到20位密码");
                    }
                } else {
                    MyToast.toast(getApplicationContext(),"请输入正确的手机号码");
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
            /*
            * 第三方微信登录
            * */
            case R.id.btn_weixinLogin:
                promptDialog = new PromptDialog(this);
                //微信授权以及登录
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
                // type = 1表示微信
                type = "1";
                break;
            /*
            * 第三方QQ 登录
            * */
            case R.id.btn_login_QQ:
                //QQ授权以及登录
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                // type = 2表示QQ
                type = "2";
                break;
            //商家注册按钮
            case R.id.txt_shpngregister:
                startActivity(new Intent(getApplicationContext(), ShoppingUserRegister.class));
                break;
            //商家登录按钮
            case R.id.btn_shpoinglogin:
                phone = edit_phone.getText().toString().trim();
                pwd = edit_pwd.getText().toString().trim();
                ShoppingLoginPerserent shangjiaLogin = new ShoppingLoginPerserent(this);
                shangjiaLogin.shoppinglogin(phone,pwd);
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
                String unionid =  data.get("unionid");

                //MVP 调用网络请求
                WechatPerenest wp = new WechatPerenest(LoginActivity.this);
                wp.fach(str,profile_image_url,openID, unionid,type);
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
            r_id = wchatbean.getR_id();
            ShareUtils.putString(getApplicationContext(),"r_id",r_id);
            //用户名
            String username = wchatbean.getR_username();
            //获取用户登录的openid
            String openid = wchatbean.getMobilephone();
            //登录成功后绑定个推推送别名
            PushManager.getInstance().bindAlias(LoginActivity.this, openid);

            //用户头像
            userImg = wchatbean.getR_img();
            //用户微信登录后 的金额
            String money = wchatbean.getR_money();

            // String phones = wchatbean.getPhone();0
            //授权成功保存openID 这里跟真实手机号码一样保存同一个key
            //   ShareUtils.putString(getApplicationContext(), "zhanghao", openid);
            //保存用户名字
            ShareUtils.putString(getApplicationContext(), "username", username);
            //保存用户头像
            ShareUtils.putString(getApplicationContext(), "touxiangURL", userImg);
            //保存用户微信登录号上的金额
            ShareUtils.putString(getApplicationContext(),"wechatMoney",money);
            //获取mobileID
            //  String mobileID = wchatbean.getMobilephone();
            String pwd =  wchatbean.getR_password();
            String r_appi = wchatbean.getR_appid();
            //这里取出商家入驻的那个字段
            String is_aut = wchatbean.getIs_Authentication();
            ShareUtils.putString(getApplicationContext(),"is_aut",is_aut);
            //获取r_id
            if (!pwd.equals("")) {
                ShareUtils.putString(getApplicationContext(),"zhanghao",r_appi);
                finish();
            } else {
                //然后跳转到手机验证界面
                Intent intent = new Intent(LoginActivity.this, BangDingZhangHaoActivity.class);
                intent.putExtra("r_id", r_id);
                intent.putExtra("userImg",userImg);
                //特殊处理
                intent.putExtra("zhanghao",openid);
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
                // 这里获取 r_id 并且保存后到MainActivity 取出来然后请求 获取用户是否正在使用雨伞
                String r_id =  logindata.getData().getR_id();
                ShareUtils.putString(getApplicationContext(),"r_id",r_id);
                //登录成功后保存账号密码
                ShareUtils.putString(getApplicationContext(),"zhanghao",phone);
                ShareUtils.putString(getApplicationContext(),"mima",password);
                //登录成功后绑定个推推送 别名
                PushManager.getInstance().bindAlias(LoginActivity.this, phone);

                //获取 是否是管理员账号
                LoginBean.DataBean list = logindata.getData();
                //获取登录返回的用户名
                //   String username = list.getUsername();
                String username =  logindata.getData().getR_username();
                if (!TextUtils.isEmpty(username)) {
                    //保存用户名
                    ShareUtils.putString(getApplicationContext(), "username", username);
                } else {
                    ShareUtils.putString(getApplicationContext(), "username", "");
                }
                //获取登录后返回的头像
                //    String  url = list.getPhoto();
                String url = logindata.getData().getR_img();
                if (!TextUtils.isEmpty(url)) {
                    String urls =  ConfigUtils.ZHU_YU_MING+"public/avatar/"+url;
                    ShareUtils.putString(getApplicationContext(),"touxiangURL",urls);
                }
                int isroot = list.getIsroot();
                //保存 该字段 该字段判断是否为管理员权限
                ShareUtils.putInt(getApplicationContext(),"isroots",isroot);
//                Intent intent = new Intent(this,MainActivity.class);
//                startActivity(intent);
                //这里取出商家入驻的那个字段
                String is_aut = logindata.getData().getIs_Authentication();
                ShareUtils.putString(getApplicationContext(),"is_aut",is_aut);
                //取出微信绑定状态
                String wechat = logindata.getData().getWechat();
                ShareUtils.putString(getApplicationContext(),"wechat",wechat);
                //取出QQ绑定状态
                String qq = logindata.getData().getQQ();
                ShareUtils.putString(getApplicationContext(),"qq",qq);

                finish();
            } else if (status == 2) {
                promptDialog.dismiss();
                promptDialog.showError("密码错误");
            }
        }
    }
    /*
    * 普通账号登录失败的接口回调 这里就是网络请求失败返回status = 0 然后走这个接口
    * */

    @Override
    public void showLoginError() {
        promptDialog.dismiss();
        StyledDialog.buildIosAlert("登录", "您的账号或者密码错误，请输入正常的密码或者去修改！", new MyDialogListener() {
            @Override
            public void onFirst() {

            }

            @Override
            public void onSecond() {

            }
        }).setBtnText("确定","").show();
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

    /*
    * 商家普通登录结果回调
    * */
    @Override
    public void ShowLoginRelust(Object object) {
        ShoppingLoginBean slb = (ShoppingLoginBean) object;
        int status =  slb.getStatus();
        if (status == 1) {
            //取出QQ 跟微信 绑定的状态
            ShoppingLoginBean.DataBean slbdb = slb.getData();
            String qqsatua = slbdb.getQq();
            String WeChatStatus = slbdb.getWechat();
            //保存商家登录账号e
            String phone = slbdb.getPhone();
            ShareUtils.putString(getApplicationContext(),"phone",phone);
            //保存id然后在认证界面要用到
            String id = slbdb.getId();
            ShareUtils.putString(getApplicationContext(),"id",id);
            //这里保存状态为true  下次登录直接 跳转到主界面
            // ShareUtils.putBoolean(LoginActivity.this, StaticClass.SHARE_IS_FIRSTS, true);
            startActivity(new Intent(getApplicationContext(),ShoppingShangjiaxinxiActivity.class));
            BaseAppliction.destoryActivity("MainActivity");
            finish();
        } else {
            ToastUtil.showShortToast(getApplicationContext(),"登录失败");
        }
    }
}
