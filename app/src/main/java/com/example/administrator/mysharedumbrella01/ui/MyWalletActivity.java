package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.PopupWindowCenter;
import com.example.administrator.mysharedumbrella01.dialog.TuiKuanPopupWindow;
import com.example.administrator.mysharedumbrella01.entivity.ManeyBean;
import com.example.administrator.mysharedumbrella01.entivity.TuikuanBean;
import com.example.administrator.mysharedumbrella01.peresenet.TuikuanPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.WalletManeyPerserent;
import com.example.administrator.mysharedumbrella01.utils.MyToast;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsTuikuanView;
import com.example.administrator.mysharedumbrella01.view.IsWalletManeyView;
import com.google.zxing.common.StringUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.whyalwaysmea.circular.AnimUtils;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/6/8 0008.
 *  我的钱包
 */

public class MyWalletActivity extends AppCompatActivity implements View.OnClickListener, IsTuikuanView, IsWalletManeyView {
    //返回键
    private ImageView image_back;
    //点击按钮充值
    private Button btn_Recharge;
    //充值明细
    private TextView tv_mingxi;
    //押金 jine
    private TextView tv_yajin,tv_yuer;
    private Button btn_chongzhiYaJin;
    private String yajin;
    private TextView tv_tuikuan;
    private PromptDialog promptDialog;
    private String datas;
    private String yuer;
    private double yajinmoney;
    private String money,deposit;
    private View fl_layout;
    private RelativeLayout rl_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        promptDialog = new PromptDialog(this);
        //沉浸式
/*        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();*/
        ImmersionBar.with(this)
                .transparentBar()
                .init();
        //过渡动画
        initTartrser();
        //获取服务器返回的押金跟金额
        getHttpMoney();

        //初始化控件
        initView();

    }
    /*
    * 过渡动画
    * */
    private void initTartrser() {
        AnimUtils.animhpel(this,R.id.fl_layout);
    }

    /*
    * 获取服务器返回的金额跟押金
    * */
    public void getHttpMoney() {
        WalletManeyPerserent wmp = new WalletManeyPerserent(this);
        wmp.fach(this);
    }

    private void initView() {
        tv_tuikuan = (TextView)findViewById(R.id.tv_tuikuan);
        tv_tuikuan.setOnClickListener(this);
        btn_chongzhiYaJin = (Button)findViewById(R.id.btn_chongzhiYaJin);
        btn_chongzhiYaJin.setOnClickListener(this);
        tv_yajin = (TextView) findViewById(R.id.tv_yajin);
        tv_yuer = (TextView) findViewById(R.id.tv_yuer);
        tv_mingxi = (TextView) findViewById(R.id.tv_mingxi);
        tv_mingxi.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        btn_Recharge = (Button) findViewById(R.id.btn_Recharge);
        btn_Recharge.setOnClickListener(this);
        //获取从上一个界面传过来的押金
        initDatas();
    }

    private void initDatas() {
 /*       Intent intent =   getIntent();
        yuer = intent.getStringExtra("yuer");
        yajin = intent.getStringExtra("yajin");
        //账户余额
        tv_yuer.setText(yuer+"元");
        //账户押金
        tv_yajin.setText("账户押金"+yajin+"元");*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                //finish();
                AnimUtils.finishAmins(this,R.id.rl_layout,view,R.id.fl_layout);
                break;
            case R.id.btn_Recharge:
                if (!TextUtils.isEmpty(deposit)) {
                    yajinmoney = Double.parseDouble(deposit);
                }
                if (yajinmoney < 20.00) {
                    PopupWindowCenter pwc = new PopupWindowCenter(this);
                    pwc.showPopupWindow();
                } else {
                    //充值余额
                    startActivity(new Intent(getApplicationContext(),RechargeActivity.class));
                }

                break;
            //明细
            case R.id.tv_mingxi:
                startActivity(new Intent(this,DetailofamountActivity.class));
                break;
            //充值押金
            case R.id.btn_chongzhiYaJin:
                startActivity(new Intent(getApplicationContext(), DepositRechargeActivitys.class));
                break;
            case R.id.tv_tuikuan:
                //退款弹窗
                tuikuantanchuang();

                break;
        }
    }

    /*
    * 退款押金
    * */
    private void tuikuanyajin() {

        TuiKuanPopupWindow tuikuanyajin = new TuiKuanPopupWindow(this,deposit);
        tuikuanyajin.showPopupWindow();

    }

    private void tuikuantanchuang() {
        //按钮的定义
        PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                double yuers = Double.parseDouble(money);
                double yajins = Double.parseDouble(deposit);
                if (yuers <=0.0 && yajins<20) {
                    ToastUtil.showShortToast(getApplicationContext(),"您的余额小于零不可以退款");
                } else {
                    //退款弹窗
                    tuikuanyajin();
                }

            }
        });
        confirm.setFocusBacColor(getResources().getColor(R.color.top_red));
        //Alert的调用
        promptDialog.showWarnAlert("你确定要退款吗？", new PromptButton("取消", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                Toast.makeText(MyWalletActivity.this, button.getText(), Toast.LENGTH_SHORT).show();
            }
        }), confirm);
    }

    /*
    * 充值成功后回调的时候再次掉一下网络请求 获取接口返回的 金额
    * */

    @Override
    protected void onResume() {
        super.onResume();
        getHttpMoney();
    }

    /*
        * 退款接口回调结果
        * */
    @Override
    public void showRrult(Object object) {
        TuikuanBean tuikuanBean = (TuikuanBean) object;
        int status = tuikuanBean.getStatus();
        datas = tuikuanBean.getData();
        if (status == 1) {
            Toast.makeText(getApplicationContext(), datas, Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getApplicationContext(), "退款失败，"+datas, Toast.LENGTH_SHORT).show();
        }
    }


    /*
    * 获取钱包金额 回调
    * */
    @Override
    public void showManey(ManeyBean.DataBean list) {
        //账户余额
        money =  list.getMoney(); //余额
        tv_yuer.setText(money+"元");
        //账户押金
        deposit = list.getDeposit(); // 押金
        tv_yajin.setText("账户押金"+deposit+"元");
    }

    /*
    * 返回键动画
    * */

    @Override
    public void onBackPressed() {
        AnimUtils.finishonBackPressed(MyWalletActivity.this,R.id.fl_layout);
    }
}
