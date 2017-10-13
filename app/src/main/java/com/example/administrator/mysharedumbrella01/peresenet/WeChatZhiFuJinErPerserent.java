package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.WeChatPayZhiFuJinErModelImpl;
import com.example.administrator.mysharedumbrella01.model.IsWeChatPayZhiFuJinErModel;
import com.example.administrator.mysharedumbrella01.model.IsWechaLoginModel;
import com.example.administrator.mysharedumbrella01.view.IsWeChatPayJinErView;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class WeChatZhiFuJinErPerserent {
    IsWeChatPayZhiFuJinErModel weChatPayZhiFuJinErModel;
    IsWeChatPayJinErView weChatPayJinErView;

    public WeChatZhiFuJinErPerserent(IsWeChatPayJinErView weChatPayJinErView) {
        this.weChatPayJinErView = weChatPayJinErView;
        weChatPayZhiFuJinErModel = new WeChatPayZhiFuJinErModelImpl();
    }

    public void weixinjiner(String goods,double total_fee,String apptype,String member_id,String is_merchant) {
        if (weChatPayZhiFuJinErModel != null) {
            weChatPayZhiFuJinErModel.WeChatZhiFu(new IsWeChatPayZhiFuJinErModel.OnWeChatLinisenet() {
                @Override
                public void onComplet(Object object) {
                    weChatPayJinErView.showWeChatPay(object);
                }
            }, goods, total_fee, apptype, member_id,is_merchant);
        }
    }
}
