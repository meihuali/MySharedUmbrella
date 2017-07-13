package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.entivity.BangDingStatusBean;
import com.example.administrator.mysharedumbrella01.entivity.WechatLoginBean;
import com.example.administrator.mysharedumbrella01.peresenet.BangDingZhangHaoPeresent;
import com.example.administrator.mysharedumbrella01.peresenet.WechatPerenest;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.utils.GlideUtils;
import com.example.administrator.mysharedumbrella01.view.IsBangdingZhangHaoView;
import com.example.administrator.mysharedumbrella01.view.IsWechatLoginView;
import com.gyf.barlibrary.ImmersionBar;

import cn.smssdk.SMSSDK;
import de.hdodenhof.circleimageview.CircleImageView;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/7/13 0013.
 *  这个类是 第三方微信 或者Q 登录的时候跳转到这个类·然后
 *  获取用户账号 后 传给服务器·
 */

public class BangDingZhangHaoActivity extends AppCompatActivity implements View.OnClickListener, IsWechatLoginView, IsBangdingZhangHaoView {
    private ImageView image_back;
    private CircleImageView image_yuanxing;
    private TextView tv_sanfangName;
    private EditTextWithDelete edit_phone;
    private EditText edit_verifycode;
    private TextView tv_hqyzm;
    private Button btn_register;
    private String r_id;
    private PromptDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangdingzhanghao);
         pd = new PromptDialog(this);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
        initData();

    }

    private void initData() {
        Intent intent =  getIntent();
        String str = intent.getStringExtra("str");
        String profile_image_url = intent.getStringExtra("userImg");
//        String openID = intent.getStringExtra("openID");
         r_id = intent.getStringExtra("r_id");
        //设置 头像
        GlideUtils.loadImageView(getApplicationContext(),profile_image_url,image_yuanxing);
        //设置用户名字
        tv_sanfangName.setText(str);
    }

    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        image_yuanxing = (CircleImageView) findViewById(R.id.image_yuanxing);
        tv_sanfangName = (TextView)findViewById(R.id.tv_sanfangName);
        edit_phone = (EditTextWithDelete)findViewById(R.id.edit_phone);
        edit_verifycode = (EditText)findViewById(R.id.edit_verifycode);
        tv_hqyzm = (TextView)findViewById(R.id.tv_hqyzm);
        tv_hqyzm.setOnClickListener(this);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_hqyzm:
                getSMS();
                break;
            case R.id.btn_register:
                //获取手机号码
                String phone =  edit_phone.getText().toString().trim();
                String yzm = edit_verifycode.getText().toString().trim();
                //这里请求网络
                BangDingZhangHaoPeresent bangding = new BangDingZhangHaoPeresent(this);
                bangding.bangzhanghao(phone,yzm,r_id);
                break;
        }
    }
    /*
    * 获取短信验证码
    * */
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

    /*
    *  QQ 微信 登录接口回调
    * */
    @Override
    public void showLogin(WechatLoginBean wlb) {

    }
    /*
    * 这个是第三放登录 的时候 让用户 输入手机号码后 请求网络后的 回调
    * */
    @Override
    public void showReuslt(Object object) {
       BangDingStatusBean bangdingzhuangtai = (BangDingStatusBean) object;
       int status =  bangdingzhuangtai.getStatus();
        if (status == 1) {
            pd.showSuccess("绑定成功");
            BaseAppliction.destoryActivity("SettingsYusanActivity");
            finish();
        } else {
            pd.showError("绑定失败");
        }
    }
}
