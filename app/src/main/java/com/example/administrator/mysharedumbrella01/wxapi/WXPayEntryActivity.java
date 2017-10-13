package com.example.administrator.mysharedumbrella01.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.MessageEvent;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.wx_activity);
        api = WXAPIFactory.createWXAPI(this, "wxac2d038a3a418057");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }
        // 支付结果码
    @Override
    public void onResp(BaseResp resp) {
        /**
         * 这里是 你调微信支付的回调
         *  重点是·我这个类·在哪里掉了啊·我都没看到·你那边是自己在 主界面掉的·我看你
         *  没有调 只不过 加一点属性而已 哦·
         * */
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            if (resp.errCode == 0) {
//                payCallBack.success();
//                Toast.makeText(WXPayEntryActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                finish();
//            } else {
//                payCallBack.success();
//                Toast.makeText(WXPayEntryActivity.this, "支付失败" , Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
        String str = "";
        switch (resp.errCode){
            case BaseResp.ErrCode.ERR_OK:
                str = "支付成功";

                break;
            case BaseResp.ErrCode.ERR_COMM:
                str = "出现错误,请稍后再试";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                str = "用户取消";
                break;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new MessageEvent(str));
        this.finish();
    }

    public PayCallBack payCallBack;
    public interface PayCallBack {
        void success(String str);
        void failed();
    }
    public void weChatpay(PayCallBack payCallBack) {
        this.payCallBack = payCallBack;
    }
}