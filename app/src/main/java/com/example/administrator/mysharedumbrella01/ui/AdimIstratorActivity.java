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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.SaoYiSao.ScannerActivity;
import com.example.administrator.mysharedumbrella01.entivity.AdminAuthenticationBean;
import com.example.administrator.mysharedumbrella01.entivity.AdminSearchAllBean;
import com.example.administrator.mysharedumbrella01.peresenet.AdminAuthenticationPerensert;
import com.example.administrator.mysharedumbrella01.peresenet.AdminSearchAllPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.AdminSearchPerenset;
import com.example.administrator.mysharedumbrella01.utils.MyDialog;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsAdminAuthenticationView;
import com.example.administrator.mysharedumbrella01.view.IsAdminSearchAllView;
import com.example.administrator.mysharedumbrella01.view.IsAdminSearchView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.mylhyl.zxing.scanner.common.Intents;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：AdimIstratorActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 12:16
 * 描述：管理操作界面
 */
public class AdimIstratorActivity extends AppCompatActivity implements View.OnClickListener, IsAdminSearchAllView, IsAdminSearchView, IsAdminAuthenticationView {
    // 扫一扫相关 颜色  如果不赋值的话· 扫描上下滚动的就是绿色这里默认 赋值为 支付宝 那种网格的
    private int laserMode = ScannerActivity.EXTRA_LASER_LINE_MODE_0;
    private ImageView image_back;
    private LinearLayout ll_layout_search;
    private LinearLayout ll_layout_authentication;
    private int type;
    private String result;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();

    }

    private void initView() {
        ll_layout_authentication = (LinearLayout) findViewById(R.id.ll_layout_authentication);
        ll_layout_authentication.setOnClickListener(this);
        ll_layout_search  = (LinearLayout) findViewById(R.id.ll_layout_search);
        ll_layout_search.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.ll_layout_search:
                type = 1;
                initViewes();
                break;
            case R.id.ll_layout_authentication:
                type = 2;
                initViewes();
                break;
        }
    }
    /*
    * 扫描二维码
    * */
    private void initViewes() {
        if (ContextCompat.checkSelfPermission(AdimIstratorActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(AdimIstratorActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            ScannerActivity.gotoActivity(AdimIstratorActivity.this,
                    true, laserMode);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
                if (type == 1) {
                    if (data != null) {
                        String stringExtra = data.getStringExtra(Intents.Scan.RESULT);
                        Log.e("扫描结果 ", "" + stringExtra);

                        if (!TextUtils.isEmpty(stringExtra)) {
                            if (stringExtra.contains("CS") && stringExtra.length() == 20) {
                                StyledDialog.buildLoading("请求数据···").show();
                                String jiequhou = stringExtra.substring(stringExtra.length() - 18);
                                AdminSearchPerenset searchPerenset = new AdminSearchPerenset(this);
                                searchPerenset.adminsearch(jiequhou);

                            } else if (stringExtra.length() > 19) {
                                StyledDialog.buildLoading("请求数据···").show();
                                String jiequhou = stringExtra.substring(stringExtra.length() - 19);
                                AdminSearchAllPerserent searchPerserent = new AdminSearchAllPerserent(this);
                                String phone = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                                searchPerserent.adminSearchAll(phone, jiequhou);
                            } else {
                                MyDialog.dialog("警告", "无效的二维码", "确定", "");
                            }

                        } else {
                            MyDialog.dialog("警告", "二维码不能为空", "确定", "");
                        }
                    }
                } else if (type == 2) {
                    StyledDialog.buildLoading("加载中···").show();
                    if (data != null) {
                        String stringExtra = data.getStringExtra(Intents.Scan.RESULT);
                        Log.e("扫描结果 ", "" + stringExtra);
                        if (!TextUtils.isEmpty(stringExtra)) {
                            result = stringExtra.substring(stringExtra.length() - 19);
                            AdminAuthenticationPerensert authenticationPerensert = new AdminAuthenticationPerensert(AdimIstratorActivity.this);
                            String phone  = ShareUtils.getString(getApplicationContext(),"zhanghao","");
                            authenticationPerensert.authentiact(phone);
                        }
                    }
                }



            }
        }
    }

    /*
    *  这个回调就是管理 扫描开锁 所有的回调那个 特殊的二维码
    * */
    @Override
    public void onAdminSearch(Object object) {
       StyledDialog.dismissLoading();
        AdminSearchAllBean allBean = (AdminSearchAllBean) object;
        int state = allBean.getStatus();
        if (state == 1) {
            MyDialog.dialog("提示", allBean.getData(), "确定", "");
        } else {
            MyDialog.dialog("提示", allBean.getError_reason(), "确定", "");
        }
    }
    /*
    *  这个接口回调就是扫描 那个带CS 二维码 的
    * */
    @Override
    public void showRelust(Object object) {
       StyledDialog.dismissLoading();
        AdminSearchAllBean allBean = (AdminSearchAllBean) object;
        int stare = allBean.getStatus();
        if (stare == 1) {
            MyDialog.dialog("提示", allBean.getData(), "确定", "");
        } else {
            MyDialog.dialog("提示", allBean.getError_reason(), "确定", "");
        }
    }
    /*
    * 这个回调就是 管理员界面 信息认证 扫描二维码 后的回调
    * */
    @Override
    public void showRestultAuthenticon(Object object) {
        StyledDialog.dismissLoading();
        AdminAuthenticationBean bean = (AdminAuthenticationBean) object;
        int state = bean.getStatus();
        if (state == 1) {
            Intent intent = new Intent(getApplicationContext(),AdministratorInformation.class);
            intent.putExtra("sanzuoID",result);
            startActivity(intent);
        } else {
            MyDialog.dialog("提示","服务器挂了","确定","");
        }
    }
}
