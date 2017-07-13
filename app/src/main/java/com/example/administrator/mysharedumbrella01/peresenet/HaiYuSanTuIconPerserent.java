package com.example.administrator.mysharedumbrella01.peresenet;

import com.autonavi.rtbt.IFrameForRTBT;
import com.example.administrator.mysharedumbrella01.Impl.HaiYuSanTuIconModelImpl;
import com.example.administrator.mysharedumbrella01.model.IsHaiYuSanTuIconModle;
import com.example.administrator.mysharedumbrella01.model.IsYuSanTuIconModel;
import com.example.administrator.mysharedumbrella01.view.IsHaiYuSanJiemianIconView;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class HaiYuSanTuIconPerserent {
    IsHaiYuSanTuIconModle haiYuSanTuIconModle;
    IsHaiYuSanJiemianIconView haiYuSanJiemianIconView;

    public HaiYuSanTuIconPerserent(IsHaiYuSanJiemianIconView haiYuSanJiemianIconView) {
        this.haiYuSanJiemianIconView = haiYuSanJiemianIconView;
        haiYuSanTuIconModle = new HaiYuSanTuIconModelImpl();
    }

    public void haisanIcon(String numbess) {
        if (haiYuSanTuIconModle != null) {
            haiYuSanTuIconModle.getYushanIcon(new IsYuSanTuIconModel.OnYuShanIconLinsetern() {
                @Override
                public void OnComptle(Object object) {
                    haiYuSanJiemianIconView.ShowHaiSanIcon(object);
                }
            },numbess);
        }
    }
}
