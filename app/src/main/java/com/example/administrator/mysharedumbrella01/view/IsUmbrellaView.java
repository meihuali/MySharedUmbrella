package com.example.administrator.mysharedumbrella01.view;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.entivity.GetumbrellaBean;
import com.example.administrator.mysharedumbrella01.entivity.SaoYiSaoBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public interface IsUmbrellaView {
    void showUmbrella(List<GetumbrellaBean.DataBean> list, int types);

    //扫一扫
    void showSaoYiSao(SaoYiSaoBean syb,String mincdeID,String phone);
}

