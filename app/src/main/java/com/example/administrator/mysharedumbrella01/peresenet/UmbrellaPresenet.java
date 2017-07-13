package com.example.administrator.mysharedumbrella01.peresenet;

import android.app.Activity;

import com.example.administrator.mysharedumbrella01.Impl.GetUmberllaModeImpl;
import com.example.administrator.mysharedumbrella01.entivity.GetumbrellaBean;
import com.example.administrator.mysharedumbrella01.entivity.SaoYiSaoBean;
import com.example.administrator.mysharedumbrella01.model.IsUmbrellaStandMode;
import com.example.administrator.mysharedumbrella01.view.IsUmbrellaView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class UmbrellaPresenet {
    IsUmbrellaView isUmbrellaViewl;
    IsUmbrellaStandMode isUmbrellaStand;

    public UmbrellaPresenet(IsUmbrellaView isUmbrellaViewl) {
        this.isUmbrellaViewl = isUmbrellaViewl;
        isUmbrellaStand  = new GetUmberllaModeImpl();
    }
    /*
    * 雨伞分布系列·
    * */
    public void fech(Activity activity, double longitude, double latitude, final int types) {
        isUmbrellaStand.GetUmbrellaStand(new IsUmbrellaStandMode.OnGetUmbrellaLiseners() {
            @Override
            public void onComlete(List<GetumbrellaBean.DataBean> list) {
                isUmbrellaViewl.showUmbrella(list,types);
            }
        },activity,longitude,latitude);


    }

    public void binds(String mincdeID, final String phone,Activity activity) {
        if (isUmbrellaStand != null) {
            isUmbrellaStand.SaoYiSao(new IsUmbrellaStandMode.OnSaoYiSaoListeners() {
                @Override
                public void onComplete(SaoYiSaoBean syb,String mincdeID) {
                    isUmbrellaViewl.showSaoYiSao(syb,mincdeID,phone);
                }
            },mincdeID,phone,activity);
        }
    }

}
