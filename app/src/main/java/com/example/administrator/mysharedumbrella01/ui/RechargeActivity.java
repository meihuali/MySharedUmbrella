package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ZhiFuBaoYaJinBean;
import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;
import com.example.administrator.mysharedumbrella01.peresenet.AlipayPerserent;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsAliPayView;
import com.gyf.barlibrary.ImmersionBar;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/9 0009.
 *   充值界面
 */

public class RechargeActivity extends AppCompatActivity implements View.OnClickListener, IsAliPayView {
    private ImageView image_back;
    private ImageView image_weixin_gouxuan;
    private ImageView image_zhifubao_weigouxuan;
    //金额选择的四个button
    private Button btn_yibaiyuan,btn_wushiyuan,btn_ershiyuan,btn_shiyuan;
    //声明选择的金额
    private double moneys = 100;
    //选择支付方式的变量
    private int types = 1;
    private Button btn_Recharge;
    private String dingdan;
    public static final int SDK_PAY_FLAG = 0;
    private String bodyZhuTi = "充值100元";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }

    private void initView() {
        btn_Recharge = (Button) findViewById(R.id.btn_Recharge);
        btn_Recharge.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        image_weixin_gouxuan = (ImageView) findViewById(R.id.image_weixin_gouxuan);
        image_weixin_gouxuan.setOnClickListener(this);
        image_zhifubao_weigouxuan = (ImageView) findViewById(R.id.image_zhifubao_weigouxuan);
        image_zhifubao_weigouxuan.setOnClickListener(this);

        btn_yibaiyuan = (Button) findViewById(R.id.btn_yibaiyuan);
        btn_yibaiyuan.setOnClickListener(this);
        btn_wushiyuan = (Button) findViewById(R.id.btn_wushiyuan);
        btn_wushiyuan.setOnClickListener(this);
        btn_ershiyuan = (Button) findViewById(R.id.btn_ershiyuan);
        btn_ershiyuan.setOnClickListener(this);
        btn_shiyuan = (Button) findViewById(R.id.btn_shiyuan);
        btn_shiyuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.btn_yibaiyuan:
                btn_yibaiyuan.setTextColor(getResources().getColor(R.color.white));
                btn_wushiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_shiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_ershiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_yibaiyuan.setBackgroundColor(getResources().getColor(R.color.qianjuse));
                btn_wushiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_ershiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_shiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                //点击一百元 赋值为100
                moneys = 100;
                //充值内容主题
                bodyZhuTi = "充值100元";
                break;
            case R.id.btn_wushiyuan:
                btn_wushiyuan.setTextColor(getResources().getColor(R.color.white));
                btn_yibaiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_ershiyuan.setTextColor(getResources().getColor(R.color.black));
                btn_shiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_wushiyuan.setBackgroundColor(getResources().getColor(R.color.qianjuse));
                btn_yibaiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_ershiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_shiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                // 点击五十元 赋值为50；
                moneys = 50;
                //充值内容主题
                bodyZhuTi = "充值50元";
                break;
            case R.id.btn_ershiyuan:
                btn_ershiyuan.setTextColor(getResources().getColor(R.color.white));
                btn_yibaiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_wushiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_shiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_ershiyuan.setBackgroundColor(getResources().getColor(R.color.qianjuse));
                btn_yibaiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_wushiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_shiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                moneys = 20;
                //充值内容主题
                bodyZhuTi = "充值20元";
                break;
            case R.id.btn_shiyuan:
                btn_shiyuan.setTextColor(getResources().getColor(R.color.white));
                btn_yibaiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_wushiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_ershiyuan.setTextColor(getResources().getColor(R.color.txt_balck));
                btn_shiyuan.setBackgroundColor(getResources().getColor(R.color.qianjuse));
                btn_yibaiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_wushiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                btn_ershiyuan.setBackgroundColor(getResources().getColor(R.color.huise));
                moneys = 10;
                //充值内容主题
                bodyZhuTi = "充值10元";
                break;
            //点击支付宝勾选按钮
            case R.id.image_zhifubao_weigouxuan:
                image_zhifubao_weigouxuan.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.dagou));
                image_weixin_gouxuan.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.budagou));
                types = 2; //代表用户选择的是支付宝
                break;
            //点击微信勾选按钮
            case R.id.image_weixin_gouxuan:
//                image_weixin_gouxuan.setImageDrawable(getDrawable(R.drawable.gouxuan));
//                image_zhifubao_weigouxuan.setImageDrawable(getDrawable(R.drawable.weigouxuan));
                image_weixin_gouxuan.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.dagou));
                image_zhifubao_weigouxuan.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.budagou));
                types = 1; //代表用户选择的是微信
                break;
            /*
            * 这里是点击提交支付按钮 判断具体是什么方式支付
            * */
            case R.id.btn_Recharge:
                if (types == 1) { //等于1 代表选择选择的是 微信支付 这里掉微信的支付接口 然后在把moneys 金额带过去
                    Toast.makeText(getApplicationContext(), "微信支付暂未开通 " + moneys + " 元", Toast.LENGTH_SHORT).show();
                } else if (types == 2) { //等于2 代表选择选择的是 支付宝支付 这里掉微信的支付接口 然后在把moneys 金额带过去
                    String zh = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                    AlipayPerserent ap = new AlipayPerserent(this);
                    ap.fach("2", moneys + "", zh, "1");
                }
                break;
        }
    }



    /*该方法是掉起支付宝 支付界面的 */
    private void AlipayZhifu() {
        final String orderInfo = dingdan;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /*
    * 支付宝支付成功后的结果
    * */
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG :
                {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

            }
        }
    };

    /*支付宝 接口回调的 结果*/
    @Override
    public void showRestuel(ZhifubaoBean zhifubao) {
        int status = zhifubao.getStatus();
        if (status == 1) { //请求成功 拿到订单号
            dingdan = zhifubao.getData();
            L.e("dingdanhao 订单号 " + dingdan);
            //这里掉起 支付宝 界面 支付
            AlipayZhifu();
        } else {
            Toast.makeText(getApplicationContext(), "请求顶号服务器接口挂了···", Toast.LENGTH_SHORT).show();
        }

    }



}
