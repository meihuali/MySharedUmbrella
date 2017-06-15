
package com.example.administrator.mysharedumbrella01.wxapi;

import java.io.Serializable;

public final class PayInfo implements Serializable {
	
	private static final String TAG = PayInfo.class.getName();

	private static final long serialVersionUID = 1L;

	/** 商品名称*/
    private String subject;

    /** 商品详细信息  商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字*/
    private String body;

    /** 商品价格*/
    private String price;

    /** 商品订单号*/
    private String orderNo;
    
    /** 支付通知地址*/
    private String notifyUrl;
    //以下是微信支付专用
    private String prepayId;
    private String nonceStr;
    private String timeStamp;
    private String packageValue;
    private String sign;
    

    public String getBody() {
        return body;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getPrice() {
        return price;
    }

    public String getSubject() {
        return subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

	public String getNotifyUrl() {
		return notifyUrl;
	}


	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPackageValue() {
		return packageValue;
	}

	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "PayInfo{" +
				"notifyUrl='" + notifyUrl + '\'' +
				", prepayId='" + prepayId + '\'' +
				", nonceStr='" + nonceStr + '\'' +
				", timeStamp='" + timeStamp + '\'' +
				", packageValue='" + packageValue + '\'' +
				", sign='" + sign + '\'' +
				'}';
	}
}
