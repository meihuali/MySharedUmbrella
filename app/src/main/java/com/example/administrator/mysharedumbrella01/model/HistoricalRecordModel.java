package com.example.administrator.mysharedumbrella01.model;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.entivity.HistoryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 * //借伞 历史记录
 */

public interface HistoricalRecordModel {
    //借伞历史记录 接口
    void historicalrecord(OnHistoricalrecordListener listener, Activity activity,int isroot,String phone,String ptuser,String limt, String shangla);
    //借伞历史记录 接口调调
    interface OnHistoricalrecordListener{
        void onComplete(List<HistoryBean.DataBean> list);
    }
}
