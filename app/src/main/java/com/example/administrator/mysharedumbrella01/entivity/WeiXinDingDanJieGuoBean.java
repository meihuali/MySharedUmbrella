package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/7/15 0015.
 *  微信请求订单结果 实体
 */

public class WeiXinDingDanJieGuoBean {


    /**
     * appid : wxb09d59ac83427383
     * mch_id : 1483122862
     * nonce_str : KFaiTm1jKLcYpFXW
     * prepay_id : wx201707171230419786cee5110114809092
     * result_code : SUCCESS
     * return_code : SUCCESS
     * return_msg : OK
     * sign : 55DFB493334B6A3E5B34474862517497
     * trade_type : APP
     * time : 1500265841
     */

    private String appid;
    private String mch_id;
    private String nonce_str;
    private String prepay_id;
    private String result_code;
    private String return_code;
    private String return_msg;
    private String sign;
    private String trade_type;
    private int time;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
