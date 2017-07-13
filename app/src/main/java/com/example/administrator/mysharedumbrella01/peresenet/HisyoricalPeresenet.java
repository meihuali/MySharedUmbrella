package com.example.administrator.mysharedumbrella01.peresenet;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.Impl.HistoricalRecordModelImpl;
import com.example.administrator.mysharedumbrella01.entivity.HistoryBean;
import com.example.administrator.mysharedumbrella01.model.HistoricalRecordModel;
import com.example.administrator.mysharedumbrella01.view.HisyoricalRecordView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class HisyoricalPeresenet {
    HistoricalRecordModel historicalRecordModel;
    HisyoricalRecordView hisyoricalRecordView;

    public HisyoricalPeresenet(HisyoricalRecordView hisyoricalRecordView) {
        this.hisyoricalRecordView = hisyoricalRecordView;
        historicalRecordModel = new HistoricalRecordModelImpl();
    }
        //view 掉他 整个方法
    public void fach(Activity activity, final int isroot,final String phone,final String ptuser,String limt, String shangla) {
        if (historicalRecordModel != null) {
            historicalRecordModel.historicalrecord(new HistoricalRecordModel.OnHistoricalrecordListener() {
                @Override
                public void onComplete(List<HistoryBean.DataBean> list) {
                    hisyoricalRecordView.showHisyor(list,isroot,phone);
                }
            },activity,isroot,phone,ptuser,limt,shangla);
        }
    }
}
