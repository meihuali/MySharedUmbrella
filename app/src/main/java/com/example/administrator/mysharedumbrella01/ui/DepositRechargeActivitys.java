package com.example.administrator.mysharedumbrella01.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.DraAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.DepositRechargeBean;
import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;
import com.example.administrator.mysharedumbrella01.peresenet.AliPayYaJinPersernet;
import com.example.administrator.mysharedumbrella01.peresenet.WeChatYaJinPersernet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsAliPayYaJinView;
import com.example.administrator.mysharedumbrella01.view.IsWeChatYajinView;
import com.example.administrator.mysharedumbrella01.wxapi.WxPayUtils;
import com.gyf.barlibrary.ImmersionBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.administrator.mysharedumbrella01.ui.RechargeActivity.SDK_PAY_FLAG;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：DepositRechargeActivitys
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/4 0004 10:01
 * 描述：修改过的 押金充值界面
 */
public class DepositRechargeActivitys extends AppCompatActivity implements View.OnClickListener, IsAliPayYaJinView, IsWeChatYajinView {
    // 声明假集合
    private List<DepositRechargeBean> mlist = new ArrayList<>();
    private DepositRechargeBean drb;
    private RecyclerView mRecyclerView;
    private DraAdapter myAdapter;
    private ImageView image_back;
    private double myMoney;
    private int type;
    private ImageView image_zhifubao_weigouxuan;
    private ImageView image_weixin_gouxuan;
    private String zh;
    private String dingdan;
    private Button btn_Recharge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitys_depositrecharge);
        //沉浸式
        ImmersionBar.with(this)
                .transparentBar()
                .init();
        initView();
        //这里设置假数据源
        initData();

    }
    /*
    * 初始化控件
    * */
    private void initView() {
        btn_Recharge = (Button) findViewById(R.id.btn_Recharge);
        btn_Recharge.setOnClickListener(this);
        image_weixin_gouxuan = (ImageView) findViewById(R.id.image_weixin_gouxuan);
        image_weixin_gouxuan.setOnClickListener(this);
        image_zhifubao_weigouxuan = (ImageView) findViewById(R.id.image_zhifubao_weigouxuan);
        image_zhifubao_weigouxuan.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        initAdapter();
    }
    /*
    * 这里是关于设置适配器系列
    * */
    private void initAdapter() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //这一句是设置横向 两列
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        myAdapter = new DraAdapter(R.layout.deprech_item,mlist,getApplicationContext());
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int postion) {

                for (int j = 0; j < mlist.size(); j++) {
                    if (j == postion) {
                        //这里两句有重大意义 取反和直接设置为true效果是不一样的
                        // mlist.get(j).setSeceket(!mlist.get(j).isSeceket());
                        mlist.get(j).setSeceket(true);
                        myMoney = Double.parseDouble(mlist.get(postion).getMoney());
                        L.e("被选择中的金额 "+myMoney);
                    } else {
                        mlist.get(j).setSeceket(false);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }
        });

    }

    /*
    * 假数据源
    * */
    private void initData() {
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                drb = new DepositRechargeBean();
                drb.setMoney("80");
                drb.setSeceket(true);
            } else if (i == 1) {
                drb = new DepositRechargeBean();
                drb.setMoney("60");
            } else if (i == 2) {
                drb = new DepositRechargeBean();
                drb.setMoney("40");
            } else if (i == 3) {
                drb = new DepositRechargeBean();
                drb.setMoney("20");
            }
            mlist.add(drb);
        }
        myAdapter.setNewData(mlist);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;

            //点击支付宝勾选按钮
            case R.id.image_zhifubao_weigouxuan:
                type = 2;
                image_zhifubao_weigouxuan.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.dagou_x));
                image_weixin_gouxuan.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.budagou_x));

                break;
            //点击微信勾选按钮
            case R.id.image_weixin_gouxuan:
                type = 1;
                image_weixin_gouxuan.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.dagou_x));
                image_zhifubao_weigouxuan.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.budagou_x));
                break;

            //充值押金 按钮
            case R.id.btn_Recharge:
                /*
                * 这里点击按钮 去 区分 用户是微信支付还是 支付宝支付
                * 看的明白吗？
                *  1表示 微信 2 表示 支付宝
                * */
                //type 2 表示支付宝 1 表示微信支付

                if (type == 2) {
                    zh = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                    AliPayYaJinPersernet apyhp = new AliPayYaJinPersernet(this);
                    apyhp.fach("2", myMoney + "", zh, "1");

                } else {

                    zh = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                    //这里表示 微信支付的 网络请求 获取到服务器返回的 订单号 这里看的明白吗？
                    WeChatYaJinPersernet weixinyajin = new WeChatYaJinPersernet(this);
                    weixinyajin.wechatyajin("1",myMoney,"2",zh);
                }

                break;

        }
    }


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
                        Toast.makeText(DepositRechargeActivitys.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(DepositRechargeActivitys.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

            }
        }
    };

    /*该方法是掉起支付宝 支付界面的 */
    private void AlipayZhifu() {
        final String orderInfo = dingdan;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(DepositRechargeActivitys.this);
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
    * 支付宝 充值押金回调
    * */
    @Override
    public void showRestuelYaJin(ZhifubaoBean zhiFuBaoYaJinBean) {
        int status = zhiFuBaoYaJinBean.getStatus();
        if (status == 1) { //请求成功 拿到订单号
            dingdan = zhiFuBaoYaJinBean.getData();
            L.e("dingdanhao 订单号 " + dingdan);
            //这里掉起 支付宝 界面 支付
            AlipayZhifu();
        } else {
            ToastUtil.showShortToast(getApplicationContext(),"支付宝押金充值回调status不等于1");
        }
    }
    /*
    * 微信充值押金回调
    **/
    @Override
    public void showWechatYajin(Object object) {

        JSONObject obj = (JSONObject) object;
        //获取appid
        String appid = obj.optString("appid");
        //获取mch_id
        String mch_id = obj.optString("mch_id");
        String nonce_str = obj.optString("nonce_str");
        String prepay_id = obj.optString("prepay_id");
        //这个参数没卵用
        String result_code = obj.optString("result_code");
        //这个参数没卵用
        String return_code = obj.optString("return_code");
        //这个参数没卵用
        String return_msg = obj.optString("return_msg");
        String sign = obj.optString("sign");
        //这个参数没卵用
        String trade_type = obj.optString("trade_type");
        String time = obj.optString("time");
        //将int 型时间 转成字符串
        String times =  String.valueOf(time);
        //当你拿到上面的 这些字段后·然后就 来new 我给你的微信支付工具类 ·把上面的这些字段
        // 通过 构造方法 传到工具类·这样就可以掉起支付了·
        //在这里掉起微信支付界面
        WxPayUtils wxPayUtils = new WxPayUtils(this);
        wxPayUtils.pay_wechat(appid,mch_id,prepay_id,nonce_str,times,"Sign=WXPay",sign);
    }
}
