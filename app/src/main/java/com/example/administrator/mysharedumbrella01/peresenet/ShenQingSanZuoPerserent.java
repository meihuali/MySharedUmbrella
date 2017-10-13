package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ShenQingSanZuoImpl;
import com.example.administrator.mysharedumbrella01.model.IsShenQingSanZuoModel;
import com.example.administrator.mysharedumbrella01.view.IsShenQingSanZuoView;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ShenQingSanZuoPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/20 0020 19:15
 * 描述：TODO
 */
public class ShenQingSanZuoPerserent {
    IsShenQingSanZuoModel shenQingSanZuoModel;
    IsShenQingSanZuoView shenQingSanZuoView;

    public ShenQingSanZuoPerserent(IsShenQingSanZuoView shenQingSanZuoView) {
        this.shenQingSanZuoView = shenQingSanZuoView;
        shenQingSanZuoModel = new ShenQingSanZuoImpl();
    }

    public void shenqing(String phone,String stand,String umbrella) {
        if (shenQingSanZuoModel != null) {
            shenQingSanZuoModel.shenqingsanzuo(new IsShenQingSanZuoModel.onShenQinSanZUOLinserst() {
                @Override
                public void onComplte(Object object) {
                    shenQingSanZuoView.showReslus(object);
                }
            }, phone, stand, umbrella);
        }
    }
}
