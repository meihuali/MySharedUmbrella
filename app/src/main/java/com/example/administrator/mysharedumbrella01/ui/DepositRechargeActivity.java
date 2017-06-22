package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ZhiFuBaoYaJinBean;
import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;
import com.example.administrator.mysharedumbrella01.peresenet.AliPayYaJinPersernet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.view.IsAliPayYaJinView;
import com.gyf.barlibrary.ImmersionBar;

import java.util.Map;

import static com.example.administrator.mysharedumbrella01.ui.RechargeActivity.SDK_PAY_FLAG;

/**
 * Created by Administrator on 2017/6/8 0008.
 * //充值押金的界面
 */

public class DepositRechargeActivity extends AppCompatActivity implements View.OnClickListener, IsAliPayYaJinView {
    //返回箭头
    private ImageView img_back;
    private LinearLayout ll_llay_weixin;
    private LinearLayout ll_llay_zhifubao;
    //勾选 （微信的）
    private ImageView image_weixin_gouxuan,image_weixin_weigouxuan;
    //未勾选（支付宝的）
    private ImageView image_zhifubao_weigouxuan,image_zhifubao_gouxuan;
    //定义被勾选按钮的 金额
    private double moneys = 20.00;
    private Button btn_chongzhiYaJin;
    private String dingdan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositrecharge);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();

    }

    private void initView() {
        btn_chongzhiYaJin = (Button) findViewById(R.id.btn_chongzhiYaJin);
        btn_chongzhiYaJin.setOnClickListener(this);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
        image_weixin_gouxuan = (ImageView) findViewById(R.id.image_weixin_gouxuan);
        image_weixin_gouxuan.setOnClickListener(this);
        image_weixin_weigouxuan = (ImageView) findViewById(R.id.image_weixin_weigouxuan);
        image_weixin_weigouxuan.setOnClickListener(this);
        ll_llay_weixin = (LinearLayout) findViewById(R.id.ll_llay_weixin);
        ll_llay_weixin.setOnClickListener(this);

        image_zhifubao_weigouxuan = (ImageView) findViewById(R.id.image_zhifubao_weigouxuan);
        image_zhifubao_weigouxuan.setOnClickListener(this);
        image_zhifubao_gouxuan = (ImageView) findViewById(R.id.image_zhifubao_gouxuan);
        image_zhifubao_gouxuan.setOnClickListener(this);
        ll_llay_zhifubao = (LinearLayout) findViewById(R.id.ll_llay_zhifubao);
        ll_llay_zhifubao.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            //点击支付宝勾选按钮
            case R.id.image_zhifubao_weigouxuan:
                image_zhifubao_weigouxuan.setImageDrawable(getDrawable(R.drawable.gouxuan));
                image_weixin_gouxuan.setImageDrawable(getDrawable(R.drawable.weigouxuan));
                moneys = 20.00; //暂时未了测试 写成 0.01
                break;
            //点击微信勾选按钮
            case R.id.image_weixin_gouxuan:
                image_weixin_gouxuan.setImageDrawable(getDrawable(R.drawable.gouxuan));
                image_zhifubao_weigouxuan.setImageDrawable(getDrawable(R.drawable.weigouxuan));
                moneys = 20.00;
                break;
            //充值押金 按钮
            case R.id.btn_chongzhiYaJin:
                String zh = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                AliPayYaJinPersernet apyhp = new AliPayYaJinPersernet(this);
                apyhp.fach("2",moneys+"",zh,"1");
                break;
        }
    }

    /*该方法是掉起支付宝 支付界面的 */
    private void AlipayZhifu() {
        final String orderInfo = dingdan;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(DepositRechargeActivity.this);
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
    * 支付宝结果回调
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
                        Toast.makeText(DepositRechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(DepositRechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

            }
        }
    };


        /*充值押金 结果回调*/
    @Override
    public void showRestuelYaJin(ZhifubaoBean zhiFuBaoYaJinBean) {
        int status = zhiFuBaoYaJinBean.getStatus();
        if (status == 1) { //请求成功 拿到订单号
            dingdan = zhiFuBaoYaJinBean.getData();
            L.e("dingdanhao 订单号 " + dingdan);
            //这里掉起 支付宝 界面 支付
            AlipayZhifu();
        } else {
            Toast.makeText(getApplicationContext(), "请求顶号服务器接口挂了···", Toast.LENGTH_SHORT).show();
        }
    }
}
