package com.example.administrator.mysharedumbrella01.view;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.entivity.DetailoFamounBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public interface IsDetailoFamounView {
    void showData(String limt, List<DetailoFamounBean.DataBean> list,int type);
}
