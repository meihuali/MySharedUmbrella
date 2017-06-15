package com.example.administrator.mysharedumbrella01.peresenet;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.Adapter.DetailoFamountAdapter;
import com.example.administrator.mysharedumbrella01.Impl.DetailoFamountModelImpl;
import com.example.administrator.mysharedumbrella01.entivity.DetailoFamounBean;
import com.example.administrator.mysharedumbrella01.model.IsDetailoFamountModel;
import com.example.administrator.mysharedumbrella01.view.IsDetailoFamounView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class DetailoFamountPerserent {
    private IsDetailoFamountModel detailoFamountModel;
    private IsDetailoFamounView detailoFamounView;

    public DetailoFamountPerserent(IsDetailoFamounView detailoFamounView) {
        this.detailoFamounView = detailoFamounView;
        detailoFamountModel = new DetailoFamountModelImpl();
    }

    //绑定的方法
    public void fact(final Activity activity, final String limt, final String shangla, final DetailoFamountAdapter adapter,final int type) {
        if (detailoFamountModel != null) {
            detailoFamountModel.userDetailoFamount(new IsDetailoFamountModel.OnUserDetailoFamount() {
                @Override
                public void onCompelte(List<DetailoFamounBean.DataBean> list,int type) {
                    detailoFamounView.showData(limt,list,type);
                }
            },activity,limt,shangla,adapter,type);
        }
    }
}
