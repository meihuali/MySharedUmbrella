package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/6/17 0017. \
 *
 * 支付宝的 实体类
 */

public class ZhifubaoBean {
    /**
     * status : 1
     * data : alipay_sdk=alipay-sdk-php-20161101&app_id=2017061607503315&biz_content=%7B%22body%22%3A%221%22%2C%22subject%22%3A2%2C%22out_trade_no%22%3A%222017061740638988%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fu.sunyie.com%2FAlipay%2FCompletePayment.php&sign_type=RSA2&timestamp=2017-06-17+17%3A40%3A28&version=1.0&sign=oKEoCvwpQ563%2FLfeRxVLAHZGZZg7YhKD76qKb8sEHFMVWcuwKaiv956dd7W1wW1sSszCf6vB%2FQ6xA1q5GIQqAKvrUc55FBzi03GUSxi62vdAA1PdsbcUdHMb4XH9w31dj83EnwPa86Wse5ekm7BYOQvQKnYNQKehfmAWOc%2BoH%2B83DmM%2F2Bgy1S8o%2BM2Tn6QMt0pljyvUl6nfkdv6Mhls2iBvkgltiwzM9yU0dZqHPV98O2Xz3kaOjhAbUszzaXRlGJOds9fGC%2B7a8YozElKpAt3Rt2z1MOHACVr4QanQTTUlNVsX7xxZyBLBm%2Feq5tPcVe1sI%2FjR0X7IuXKfoe4Gjg%3D%3D
     */
    private int status;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
