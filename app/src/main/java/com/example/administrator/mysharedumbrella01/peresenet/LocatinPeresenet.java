package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShangChuanLocationImpl;
import com.example.administrator.mysharedumbrella01.model.IsShangChuanLoncationModel;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanLocationView;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class LocatinPeresenet {
    IsShangChuanLocationView shangChuanLocationView;
    IsShangChuanLoncationModel ShangChuanLoncationModel;

    public LocatinPeresenet(IsShangChuanLocationView shangChuanLocationView) {
        this.shangChuanLocationView = shangChuanLocationView;
        ShangChuanLoncationModel = new ShangChuanLocationImpl();
    }

    public void fact(final double laitudes, final double longitudes,  String zhanghao, String scanResult) {
        final  String zh ;
        final String sr;
        zh = zhanghao;
        sr = scanResult;
        if (ShangChuanLoncationModel != null) {
            ShangChuanLoncationModel.shanghuanweizi(new IsShangChuanLoncationModel.onShangchuanListenset() {
                @Override
                public void onCompelte() {
                    shangChuanLocationView.showLoction(laitudes, longitudes, zh,sr);
                }
            },  laitudes,   longitudes,  zhanghao,  scanResult);
        }
    }
}
