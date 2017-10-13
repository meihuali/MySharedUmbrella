package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefRecord;
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
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.ShoppingYajinDialog;
import com.example.administrator.mysharedumbrella01.entivity.MessageEvent;
import com.example.administrator.mysharedumbrella01.entivity.SanZuoSanBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingComfirBean;
import com.example.administrator.mysharedumbrella01.entivity.ZhifubaoBean;
import com.example.administrator.mysharedumbrella01.peresenet.AliPayYaJinPersernet;
import com.example.administrator.mysharedumbrella01.peresenet.GetShoppingSanZuoSanPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.ShenQingSanZuoPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.WeChatYaJinPersernet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsAliPayYaJinView;
import com.example.administrator.mysharedumbrella01.view.IsGetShoppingSanZuoSanView;
import com.example.administrator.mysharedumbrella01.view.IsShenQingSanZuoView;
import com.example.administrator.mysharedumbrella01.view.IsWeChatYajinView;
import com.example.administrator.mysharedumbrella01.wxapi.WXPayEntryActivity;
import com.example.administrator.mysharedumbrella01.wxapi.WxPayUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.whyalwaysmea.circular.AnimUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.Map;

import static com.example.administrator.mysharedumbrella01.ui.RechargeActivity.SDK_PAY_FLAG;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShanZuoSanActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 11:47
 * 描述： 伞座，跟伞的界面
 */
public class ShanZuoSanActivity extends AppCompatActivity implements View.OnClickListener, IsGetShoppingSanZuoSanView, IsShenQingSanZuoView, IsAliPayYaJinView, IsWeChatYajinView {
    private Button btn_confirm;
    private ImageView imageView;
    private LinearLayout ll_layout_addres;
    private ImageView image_back;
    private TextView tv_sanzuo,tv_yusan;
    private ImageView imge_add,img_jian;
    private TextView tv_sums;
    private TextView tv_shoppingYJ;
    private int sum;
    private int cuont = 0 ;
    private ImageView img_yusan_add;
    private ImageView img_yusan_jian;
    private int yusanCuont = 0;
    private TextView tv_yusans;
    private TextView tv_addressName;
    private TextView tv_phone;
    private TextView tv_address;
    private View rl_back;
    private  LinearLayout btn_zhifubao,btn_weixin;
    private  ImageView btn_zhifubao_liang,btn_zhifubao_huise;
    private  ImageView btn_weixin_huise,btn_weixin_liang;
    private int type= 1;
    private  Button btn_shenqing;
    private String phone;
    private String dingdan;
    private ShenQingSanZuoPerserent perserent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanzuosan);
        AnimUtils.animhpel( this,R.id.ll_layout_aimn);
        EventBus.getDefault().register(this);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
        initdata();
    }
    /*
    * 发起网络请求
    * */
    private void initdata() {
        perserent = new ShenQingSanZuoPerserent(this);
        String shoppingid =  ShareUtils.getString(getApplicationContext(),"shoppingId","");
        GetShoppingSanZuoSanPerserent getsanzuosan = new GetShoppingSanZuoSanPerserent(this);
        getsanzuosan.getDatas(shoppingid);

    }

    @Subscribe
    public void onEvent(MessageEvent event) {
        //接受数据
        if (event.getMessage().equals("支付成功")) {
            perserent.shenqing(phone, cuont + "", yusanCuont + "");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        initdata();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /*
                * 初始化
                * */
    private void initView() {
        btn_shenqing = (Button) findViewById(R.id.btn_shenqing);
        btn_shenqing.setOnClickListener(this);
        btn_weixin_liang = (ImageView) findViewById(R.id.btn_weixin_liang);
        btn_weixin_huise = (ImageView) findViewById(R.id.btn_weixin_huise);
        btn_zhifubao_liang = (ImageView) findViewById(R.id.btn_zhifubao_liang);
        btn_zhifubao_huise = (ImageView) findViewById(R.id.btn_zhifubao_huise);
        btn_zhifubao = (LinearLayout) findViewById(R.id.btn_zhifubao);
        btn_zhifubao.setOnClickListener(this);
        btn_weixin = (LinearLayout) findViewById(R.id.btn_weixin);
        btn_weixin.setOnClickListener(this);


        tv_address  = (TextView) findViewById(R.id.tv_address);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_addressName = (TextView) findViewById(R.id.tv_addressName);

        tv_yusans = (TextView) findViewById(R.id.tv_yusans);

        img_yusan_jian = (ImageView) findViewById(R.id.img_yusan_jian);
        img_yusan_jian.setOnClickListener(this);
        img_yusan_add = (ImageView) findViewById(R.id.img_yusan_add);
        img_yusan_add.setOnClickListener(this);
        tv_shoppingYJ = (TextView) findViewById(R.id.tv_shoppingYJ);
        tv_sums = (TextView) findViewById(R.id.tv_sums);
        imge_add = (ImageView) findViewById(R.id.imge_add);
        imge_add.setOnClickListener(this);
        img_jian = (ImageView) findViewById(R.id.img_jian);
        img_jian.setOnClickListener(this);
        tv_sanzuo = (TextView) findViewById(R.id.tv_sanzuo);
        tv_yusan = (TextView) findViewById(R.id.tv_yusan);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
//        btn_confirm = (Button) findViewById(R.id.btn_confirm);
//        btn_confirm.setOnClickListener(this);
        ll_layout_addres = (LinearLayout) findViewById(R.id.ll_layout_addres);
        ll_layout_addres.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                // finish();
                AnimUtils.finishAmins((Activity) this,R.id.rl_back,v,R.id.ll_layout_aimn);

                break;
            case R.id.btn_confirm:
                ShoppingYajinDialog shangjiayajin = new ShoppingYajinDialog(this);
                //设置pop 为全屏
                shangjiayajin.setPopupWindowFullScreen(true);
                shangjiayajin.showPopupWindow();
                shangjiayajin.setDismissWhenTouchOuside(true);
                break;
            //选择地址
            case R.id.ll_layout_addres:
                //  startActivity(new Intent(getApplicationContext(),ShoppingHarvestAddress.class));
                Intent intent = new Intent(this,ShoppingHarvestAddress.class);
                AnimUtils.startIntent(intent,v, (Activity)this,R.id.ll_layout_addres);

                break;
            case R.id.imge_add:
                sum =  (cuont+=6) * 50;
                // 设置点击个数
                tv_sums.setText(cuont+"");
                //设置总金额
                tv_shoppingYJ.setText(sum+"");
                break;
            case R.id.img_jian:
                if (cuont > 0) {
                    sum = (cuont-=6) * 50;
                    //这里设置金额
                    tv_shoppingYJ.setText(sum + "");
                    //设置个数
                    tv_sums.setText(cuont+"");
                }
                break;
            case R.id.img_yusan_add:
                yusanCuont+=18;
                //设置申请的雨伞个数
                tv_yusans.setText(yusanCuont+"");
                break;
            case R.id.img_yusan_jian:
                if (yusanCuont > 0) {
                    yusanCuont -= 18;
                    tv_yusans.setText(yusanCuont + "");
                }
                break;

            case R.id.btn_zhifubao:
                btn_zhifubao_liang.setVisibility(View.VISIBLE);
                btn_zhifubao_huise.setVisibility(View.GONE);
                btn_weixin_huise.setVisibility(View.VISIBLE);
                btn_weixin_liang.setVisibility(View.GONE);
                //支付宝
                type = 1; //1 表示支付宝

                break;
            case R.id.btn_weixin:
                btn_weixin_huise.setVisibility(View.GONE);
                btn_weixin_liang.setVisibility(View.VISIBLE);
                btn_zhifubao_liang.setVisibility(View.GONE);
                btn_zhifubao_huise.setVisibility(View.VISIBLE);
                //微信
                type = 2; //2表示微信
                break;
            case R.id.btn_shenqing:
                if (cuont == 0) {
                    //申请 雨伞
                    shenqingyusan();
                } else {
                    shenqingsanzuo();
                }

                break;
        }
    }
    /*
    * 申请伞座
    * */
    private void shenqingsanzuo() {
        if (sum != 0) {
            if (type == 1) { //  1表示支付宝  2表示微信
                phone = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                AliPayYaJinPersernet apyhp = new AliPayYaJinPersernet(this);
                apyhp.fach("2", 0.01 + "", phone, "1","1");
            } else {
                phone = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
                //这里表示 微信支付的 网络请求 获取到服务器返回的 订单号 这里看的明白吗？
                WeChatYaJinPersernet weixinyajin = new WeChatYaJinPersernet(this);
                weixinyajin.wechatyajin("1",0.01,"2",phone,"1");
            }
        }
    }

    /*
    * 申请雨伞的网络请求
    * */
    private void shenqingyusan() {
        if (cuont == 0) {
            String phone =  ShareUtils.getString(ShanZuoSanActivity.this,"zhanghao","");
            perserent.shenqing(phone,cuont+"",yusanCuont+"");
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
                        Toast.makeText(ShanZuoSanActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        perserent.shenqing(phone,cuont+"",yusanCuont+"");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ShanZuoSanActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
                PayTask alipay = new PayTask(ShanZuoSanActivity.this);
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
    * 获取伞座跟伞商家的哦
    * */
    @Override
    public void onShowRelurt(Object object) {
        SanZuoSanBean sanzuosan = (SanZuoSanBean) object;
        int status = sanzuosan.getStatus();
        if (status == 1) {
            SanZuoSanBean.DataBean sz = sanzuosan.getData();
            //获取伞座的个数
            int sanzuo =  sz.getSeat();
            //设置伞座到textview
            tv_sanzuo.setText(sanzuo+"");
            //获取伞的数量
            String umbrella= sz.getUmbrella();
            //设置雨伞到textview
            tv_yusan.setText(umbrella);

            if (sz.getAddress() != null) {
                //获取收货地址联系人
                tv_addressName.setText(sz.getAddress().getName());
                //获取收货地址 电话号码
                tv_phone.setText(sz.getAddress().getPhone());

                //获取详细地址
                tv_address.setText(sz.getAddress().getRegion() + sz.getAddress().getAddress());
            } else {
                //获取收货地址联系人
                tv_addressName.setText("张三");
                //获取收货地址 电话号码
                tv_phone.setText("13725203941");

                //获取详细地址
                tv_address.setText("广东省广州市天河区，中山大道，珠村");
            }


        }
    }
    /*
    * 这个是申请的雨伞并非伞座
    * */
    @Override
    public void showReslus(Object object) {
        ShoppingComfirBean comfirBean = (ShoppingComfirBean) object;
        int status = comfirBean.getStatus();
        if (status == 1) {
            StyledDialog.buildIosAlert("申请", comfirBean.getData().getSuccess(), new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "").show();
        } else {
            StyledDialog.buildIosAlert("申请", comfirBean.getData().getError_reason(), new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "").show();
        }
    }
    /*
    * 商家支付宝充值
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
    * 掉起微信支付的接口回调
    * */
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
