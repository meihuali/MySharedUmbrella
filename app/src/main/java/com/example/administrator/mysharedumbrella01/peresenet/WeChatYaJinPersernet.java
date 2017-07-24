package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.WeChatYajinModelImpl;
import com.example.administrator.mysharedumbrella01.model.IsWeChatYaJinModel;
import com.example.administrator.mysharedumbrella01.view.IsWeChatYajinView;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class WeChatYaJinPersernet {
    IsWeChatYaJinModel weChatYaJinModel;
    IsWeChatYajinView weChatYajinView;

    public WeChatYaJinPersernet(IsWeChatYajinView weChatYajinView) {
        this.weChatYajinView = weChatYajinView;
        weChatYaJinModel = new WeChatYajinModelImpl();
    }

    public void wechatyajin(String goods,double total_fee,String apptype,String member_id) {
        if (weChatYaJinModel != null) {
            weChatYaJinModel.wechatYajin(new IsWeChatYaJinModel.OnWechatYajinLinerest() {
                @Override
                public void onComplet(Object object) {
                    weChatYajinView.showWechatYajin(object);
                }
            }, goods, total_fee, apptype, member_id);
        }
    }
}
