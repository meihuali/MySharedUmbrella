package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.appliction.BaseAppliction;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.igexin.sdk.PushManager;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/3 0003.
 * //最后的那个 设置界面 ·包括 退出 功能
 */

public class settingsssssActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_back;
    private Button btn_exits;
    private RelativeLayout rl_guanyuwomen;
    private RelativeLayout rll_user_xieyi;
    private RelativeLayout rll_user_chongzhixieyi;
    private RelativeLayout rl_layout_settings;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sttingsssss);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        initView();
    }

    private void initView() {
        rl_guanyuwomen = (RelativeLayout) findViewById(R.id.rl_guanyuwomen);
        rl_guanyuwomen.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        btn_exits = (Button) findViewById(R.id.btn_exits);
        btn_exits.setOnClickListener(this);
        rll_user_xieyi = (RelativeLayout)findViewById(R.id.rll_user_xieyi);
        rll_user_xieyi.setOnClickListener(this);
        rll_user_chongzhixieyi = (RelativeLayout) findViewById(R.id.rll_user_chongzhixieyi);
        rll_user_chongzhixieyi.setOnClickListener(this);
        rl_layout_settings = (RelativeLayout) findViewById(R.id.rl_layout_settings);
        rl_layout_settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_exits:
                //这里取出账号
                String zh =  ShareUtils.getString(getApplicationContext(),"zhanghao","");
                //解除绑定个推别名
                PushManager.getInstance().unBindAlias(settingsssssActivity.this, zh, false);

                //清空账号
                ShareUtils.deleShare(getApplicationContext(),"zhanghao");
                //清空微信的昵称
                ShareUtils.deleShare(getApplicationContext(),"username");
                //退出登录 删除微信授权
                UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, authListener);
                //清空本地 头像
                ShareUtils.deleShare(getApplicationContext(),"touxiangURL");
                // 销毁上一个界面的activit
                BaseAppliction.destoryActivity("SettingsYusanActivity");


                finish();
                break;
            case R.id.rl_guanyuwomen:
                startActivity(new Intent(this,AboutusActivity.class));
                break;
            case R.id.rll_user_xieyi:
                startActivity(new Intent(getApplicationContext(),UserXieYiActivity.class));
                break;
            case R.id.rll_user_chongzhixieyi:
                startActivity(new Intent(getApplicationContext(),User_chongzhixieyiActivity.class));
                break;
            case R.id.rl_layout_settings:
                startActivity(new Intent(getApplicationContext(),User_chongzhiyajinxieyiActivity.class));
                break;
        }


    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
