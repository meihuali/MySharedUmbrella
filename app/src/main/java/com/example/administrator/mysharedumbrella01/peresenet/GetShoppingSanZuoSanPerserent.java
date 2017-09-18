package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.GetShoppingSanZuoSanImpl;
import com.example.administrator.mysharedumbrella01.model.IsGetShoppingSanzuoSanModel;
import com.example.administrator.mysharedumbrella01.view.IsGetShoppingSanZuoSanView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：GetShoppingSanZuoSanPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/18 0018 18:37
 * 描述：
 */
public class GetShoppingSanZuoSanPerserent {
    IsGetShoppingSanzuoSanModel getShoppingSanzuoSanModel;
    IsGetShoppingSanZuoSanView getShoppingSanZuoSanView;

    public GetShoppingSanZuoSanPerserent(IsGetShoppingSanZuoSanView getShoppingSanZuoSanView) {
        this.getShoppingSanZuoSanView = getShoppingSanZuoSanView;
        getShoppingSanzuoSanModel = new GetShoppingSanZuoSanImpl();
    }

    public void getDatas(String merchant_id) {
        if (getShoppingSanzuoSanModel != null) {
            getShoppingSanzuoSanModel.getSanZuoSan(new IsGetShoppingSanzuoSanModel.onGetSanZuoSanLinerst() {
                @Override
                public void onComplte(Object object) {
                   getShoppingSanZuoSanView.onShowRelurt(object);
                }
            },merchant_id);
        }

    }
}
