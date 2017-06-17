package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ModifyPasswordBean;
import com.example.administrator.mysharedumbrella01.peresenet.ModifyPasswordPerserent;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.view.IsModifyPasswordView;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/6/7 0007.
 *  修改密码界面
 */

public class ModifyPasswordActivity extends AppCompatActivity implements View.OnClickListener, IsModifyPasswordView {
    private TextView myback;
    private EditTextWithDelete edit_phone,zym_sznew,new_serpass;
    private Button szpass_button;
    private TextView hqmm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypassword);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.white) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }

    private void initView() {
        hqmm = (TextView) findViewById(R.id.hqmm);
        hqmm.setOnClickListener(this);
        myback = (TextView) findViewById(R.id.myback);
        myback.setOnClickListener(this);
        //获取手机号码
        edit_phone = (EditTextWithDelete) findViewById(R.id.edit_phone);
        //获取验证码
        zym_sznew = (EditTextWithDelete) findViewById(R.id.zym_sznew);
        //获取用户的新密码
        new_serpass = (EditTextWithDelete) findViewById(R.id.new_serpass);
        //点击修改密码按钮
        szpass_button = (Button) findViewById(R.id.szpass_button);
        szpass_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myback:
                finish();
                break;
            case R.id.szpass_button:
                String phones = edit_phone.getText().toString().trim();
                String smsYZM = zym_sznew.getText().toString().trim();
                String newPassWord = new_serpass.getText().toString().trim();
                if (!TextUtils.isEmpty(phones) && !TextUtils.isEmpty(smsYZM) && !TextUtils.isEmpty(newPassWord)) {
                    //这里掉中间者的类
                    ModifyPasswordPerserent mpp = new ModifyPasswordPerserent(this);
                    mpp.fach(phones,newPassWord,smsYZM);
                } else {
                    Toast.makeText(getApplicationContext(),"资料填写不对···",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.hqmm:
                getSMS();
                break;
        }
    }

    @Override
    public void showPassWord(ModifyPasswordBean list) {
        int status = list.getStatus();
        if (status == 1) {
            Toast.makeText(getApplicationContext(), "恭喜您，修改成功！", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"密码修改失败···",Toast.LENGTH_SHORT).show();
        }

    }
        /*获取短信验证码*/
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
}
