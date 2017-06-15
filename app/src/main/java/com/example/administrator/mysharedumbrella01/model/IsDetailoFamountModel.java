package com.example.administrator.mysharedumbrella01.model;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.entivity.DetailoFamounBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12 0012.
 * // 用户明细 接口
 */

public interface IsDetailoFamountModel {
    //明细接口
    void userDetailoFamount(OnUserDetailoFamount detailoFamount, Activity activity, String limt, String shangla, DetailoFamountAdapter adapter,int type);
    //明细接口回调
    interface OnUserDetailoFamount{
        void onCompelte(List<DetailoFamounBean.DataBean> list,int type);
    }
}
