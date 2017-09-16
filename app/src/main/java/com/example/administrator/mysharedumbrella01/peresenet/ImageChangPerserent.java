package com.example.administrator.mysharedumbrella01.peresenet;

import com.example.administrator.mysharedumbrella01.Impl.ImageChangImpl;
import com.example.administrator.mysharedumbrella01.model.IsImageChangeModel;
import com.example.administrator.mysharedumbrella01.view.IsImageChangView;

import java.io.File;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.peresenet
 * 文件名：ImageChangPerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 15:22
 * 描述：TODO
 */
public class ImageChangPerserent {
    IsImageChangeModel imageChangeModel;
    IsImageChangView imageChangView;

    public ImageChangPerserent(IsImageChangView imageChangView) {
        this.imageChangView = imageChangView;
        imageChangeModel = new ImageChangImpl();
    }
    public void imgchang(String stype, String phone, File file){
        if (imageChangeModel != null) {
            imageChangeModel.imageChange(new IsImageChangeModel.onImageChangLinserts() {
                @Override
                public void onComplte(Object object) {
                    imageChangView.showImage(object);
                }
            },stype,phone,file);
        }
    }
}
