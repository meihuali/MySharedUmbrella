package com.example.administrator.mysharedumbrella01.entivity;

/**
 * Created by Administrator on 2017/6/26 0026.
 * 更新APP 版本用的实体类
 */

public class UpdataBean {

    /**
     * versionName : 2.0
     * versionCode : 4
     * content : 修复多项bug
     * url : http://kxy.sunyie.com/android/app-release.apk
     * qiangzhi : 0
     */

    private String versionName;
    private int versionCode;
    private String content;
    private String url;
    private int qiangzhi;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQiangzhi() {
        return qiangzhi;
    }

    public void setQiangzhi(int qiangzhi) {
        this.qiangzhi = qiangzhi;
    }
}
