package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.BangDingZhangHaoModelImpl;
import com.example.administrator.mysharedumbrella01.model.IsBangDingZhangHaoModel;
import com.example.administrator.mysharedumbrella01.view.IsBangdingZhangHaoView;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class BangDingZhangHaoPeresent {
    IsBangDingZhangHaoModel bangDingZhangHaoModel;
    IsBangdingZhangHaoView bangdingZhangHaoView;

    public BangDingZhangHaoPeresent(IsBangdingZhangHaoView bangdingZhangHaoView) {
        this.bangdingZhangHaoView = bangdingZhangHaoView;
        bangDingZhangHaoModel = new BangDingZhangHaoModelImpl();
    }

    /*
    * 调用方法
    * */
    public void bangzhanghao(String zh,String yzm,String r_id) {
        if (bangDingZhangHaoModel != null) {
            bangDingZhangHaoModel.Bangding(new IsBangDingZhangHaoModel.OnBangdingLinerest() {
                @Override
                public void onComplet(Object object) {
                    bangdingZhangHaoView.showReuslt(object);
                }
            },zh,yzm,r_id);
        }
    }
}
