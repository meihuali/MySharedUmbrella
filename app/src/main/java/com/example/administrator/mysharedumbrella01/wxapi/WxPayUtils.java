package com.example.administrator.mysharedumbrella01.wxapi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.utils.L;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by 梅华黎 on 2016/11/17.
 * Version 1.0
 */
public class WxPayUtils {

    IWXAPI api;

    public WxPayUtils(Context context) {
        this.context = context;
    }

    private Context context;

    /*
    * appid  APP应用上 有
    * partnerid 商家ID
    * prepayid  预支付订单
    * noncestr  随机串，防重发
    * timestamp  时间戳，防重发
    * packageValue 商家根据财付通文档填写的数据和签名
    * sing 签名  商家根据微信开放平台文档对数据做的签名

 * */
    public void pay_wechat(String appid, String partnerid, String prepayid,
                           String noncestr, String timestamp, String packageValue, String sign) {
        if (null == api) {
            registerWechatApi(context);
        }
        if (!api.isWXAppInstalled()) {
            Toast.makeText(context.getApplicationContext(), "未安装微信客户端", Toast.LENGTH_SHORT).show();
        }

        PayReq req = new PayReq();
        req.appId = appid;
        req.partnerId = partnerid;
        req.prepayId = prepayid;
        req.nonceStr = noncestr;
        req.timeStamp = timestamp;
        req.packageValue = packageValue;
        req.sign = sign;
        api.sendReq(req);
        Log.e("TAG", "发起微信支付请求"+req.appId+"-"+req.partnerId+"-"+ req.prepayId + "-" +req.nonceStr +"-"+req.timeStamp+"-"+req.packageValue+"-"+req.sign);
    }

    /**
     * 注册 微信sdk 到app
     */
    public boolean registerWechatApi(Context context) {
        if (null == api) {

            api = WXAPIFactory.createWXAPI(context, "wxac2d038a3a418057", false);
        }
        //wxac2d038a3a418057
        boolean soses =  api.registerApp("wxac2d038a3a418057");
        return soses;
    }

}


