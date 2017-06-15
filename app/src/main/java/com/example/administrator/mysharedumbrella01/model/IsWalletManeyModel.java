package com.example.administrator.mysharedumbrella01.model;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.entivity.ManeyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 * 钱包金额
 */

public interface IsWalletManeyModel {
    //金额接口
    void walletMeney(OnWalletmyListener listener, Activity activity);
    //接口回调
    interface OnWalletmyListener{
        void onCompelte(ManeyBean.DataBean mbdb);
    }
}
