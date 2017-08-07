package com.example.administrator.mysharedumbrella01.view;

import com.example.administrator.mysharedumbrella01.entivity.HistoryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 *
 */

public interface HisyoricalRecordView {
     void showHisyor(List<HistoryBean.DataBean> list,int isroot,String phone);

}
