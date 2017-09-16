package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.CheckBingdingImpl;
import com.example.administrator.mysharedumbrella01.model.IsCheckBindingModel;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.view.IsCheckBingdingView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：CheckBingdingPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/12 0012 14:37
 * 描述：检测用户是否绑定过手机
 */
public class CheckBingdingPerserent {

    IsCheckBindingModel checkBindingModel;
    IsCheckBingdingView checkBingdingView;

    public CheckBingdingPerserent(IsCheckBingdingView checkBingdingView) {
        this.checkBingdingView = checkBingdingView;
        checkBindingModel = new CheckBingdingImpl();
    }

    public void bingdingzh(String phone) {
        if (checkBindingModel != null) {
            checkBindingModel.checkbangding(new IsCheckBindingModel.onCheckBindingLinsestes() {
                @Override
                public void onComplte(Object objects) {
                    checkBingdingView.showCheckBingdingSoccss(objects);
                }
            },phone);
        }
    }
}
