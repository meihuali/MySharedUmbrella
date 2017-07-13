package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.YuSanTuIconModelImpl;
import com.example.administrator.mysharedumbrella01.model.IsYuSanTuIconModel;
import com.example.administrator.mysharedumbrella01.view.IsYuSanTuIconView;

/**
 * Created by Administrator on 2017/7/8 0008.
 */

public class YuSanTuIconPerserent {
    IsYuSanTuIconModel yuSanTuIconModel;
    IsYuSanTuIconView yuSanTuIconView;

    public YuSanTuIconPerserent(IsYuSanTuIconView yuSanTuIconView) {
        this.yuSanTuIconView = yuSanTuIconView;
        yuSanTuIconModel = new YuSanTuIconModelImpl();
    }

    public void fach(String imgid) {
        if (yuSanTuIconModel != null) {
            yuSanTuIconModel.getYushanIcon(new IsYuSanTuIconModel.OnYuShanIconLinsetern() {
                @Override
                public void OnComptle(Object object) {
                    yuSanTuIconView.ShowYuSanIcon(object);
                }
            },imgid);
        }
    }
}
