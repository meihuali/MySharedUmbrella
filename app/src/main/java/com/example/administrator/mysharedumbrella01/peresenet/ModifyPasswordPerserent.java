package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ModifyPasswordModelImpl;
import com.example.administrator.mysharedumbrella01.entivity.ModifyPasswordBean;
import com.example.administrator.mysharedumbrella01.model.IsModifyPasswordModel;
import com.example.administrator.mysharedumbrella01.view.IsModifyPasswordView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class ModifyPasswordPerserent {
    IsModifyPasswordModel modifyPasswordModel;
    IsModifyPasswordView modifyPasswordView;

    public ModifyPasswordPerserent(IsModifyPasswordView modifyPasswordView) {
        this.modifyPasswordView = modifyPasswordView;
        modifyPasswordModel = new ModifyPasswordModelImpl();
    }

    //绑定
    public void fach( String phone, String newPassWord, String yanzhenma) {
        if (modifyPasswordModel != null) {
            modifyPasswordModel.password(new IsModifyPasswordModel.OnPassWordLineserset() {
                @Override
                public void OnCompelte(ModifyPasswordBean list) {
                    modifyPasswordView.showPassWord(list);
                }
            },phone,newPassWord,yanzhenma);
        }
    }
}
