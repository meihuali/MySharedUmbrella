package com.example.administrator.mysharedumbrella01.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingImgChangBean;
import com.example.administrator.mysharedumbrella01.peresenet.ImageChangPerserent;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsImageChangView;
import com.hss01248.dialog.StyledDialog;

import java.io.File;
import java.util.ArrayList;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;
import static com.example.administrator.mysharedumbrella01.R.string.jiejing;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.Fragment
 * 文件名：PropagandamapFragment
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 17:41
 * 描述：宣传图 fragment
 */
public class PropagandamapFragment extends Fragment implements View.OnClickListener, IsImageChangView {
    private ImageView img_open_photo,image_showImage;
    private String pathImg;
    private static final int REQUEST_CODE = 1024;
    private Button ll_image;
    private String phone;
    private String xuanchuanImg;
    private File files;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_xuanchuan,null);
        initView(view);
        initData(view);
        L.e("生命抽泣"+"onCreateView");
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        L.e("生命抽泣"+"onResume");
    }

    /*
        * 初始化
        * */
    private void initView(View view) {
        img_open_photo = (ImageView) view.findViewById(R.id.img_open_photo);
        img_open_photo.setOnClickListener(this);
        image_showImage = (ImageView) view.findViewById(R.id.image_showImage);
        ll_image = (Button) view.findViewById(R.id.ll_image);
        ll_image.setOnClickListener(this);
    }
    /*
*  设置数据
* */
    private void initData(View view) {
        //这里获取商家的号码
        phone =  ShareUtils.getString(getActivity(),"zhanghao","");
        //这里获取服务器返回的图片路径
        String xuanchuanImg = ShareUtils.getString(getActivity(),"xuanchuanImg","");
        if (!TextUtils.isEmpty(xuanchuanImg)) {
            String url = "http://u.sunyie.com/public/"+xuanchuanImg;
            //这里将图片设置在控件上
            Glide.with(getActivity()).load(url).override(450,360).into(image_showImage);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_open_photo:
                //  startActivity(new Intent(getActivity(), UpdataPotoNullActivity.class));
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needCamera(R.drawable.ic_boxing_camera_white).needGif().withMaxCount(1);
                Boxing.of(config).withIntent(getActivity(), BoxingActivity.class).start(this, REQUEST_CODE);
                break;
            case R.id.ll_image:
                //弹菊花
                StyledDialog.buildLoading( "加载中...").show();
                ImageChangPerserent imgchang = new ImageChangPerserent(this);
                imgchang.imgchang("2",phone,files);//1表示街景
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final ArrayList<BaseMedia> medias = Boxing.getResult(data);
            //显示出提交按钮
            ll_image.setVisibility(View.VISIBLE);

            L.e("选择图片的结果 "+medias.size());
            for (int i = 0; i < medias.size(); i++) {
                pathImg = medias.get(i).getPath();
            }

            compressWithLs(pathImg);
            // img_open_photo.setVisibility(View.GONE);
            //这里将图片设置在控件上
           // BoxingMediaLoader.getInstance().displayThumbnail(image_showImage, pathImg, 450, 360);
        }
    }

    /*
* 这里采用鲁班压缩
* */
    private void compressWithLs(String pathImg) {
        Luban.with(getActivity())
                .load(pathImg)
                .ignoreBy(100)
                .setTargetDir(getPath())
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        StyledDialog.buildLoading("压缩图片中···").show();
                    }

                    @Override
                    public void onSuccess(File file) {
                        // 压缩成果隐藏菊花
                        StyledDialog.dismissLoading();
                        L.e("压缩后 "+file);
                        files = file;
                        //这里将图片设置在控件上
                        Glide.with(getActivity()).load(files).into(image_showImage);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();
    }
    /*
    * 将压缩后的图片保存起来
    * */
    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }


    /*
    * 宣传图 上传图片的回调
    * */
    @Override
    public void showImage(Object object) {
        ShoppingImgChangBean shoppingimage = (ShoppingImgChangBean) object;
        int status = shoppingimage.getStatus();
        if (status == 1) {
            //隐藏菊花
            StyledDialog.dismissLoading();
            //隐藏按钮
            ll_image.setVisibility(View.GONE);
            ShoppingImgChangBean.DataBean jiejingImg = shoppingimage.getData();
            xuanchuanImg = jiejingImg.getProimg();
            ShareUtils.putString(getActivity(),"xuanchuanImg",xuanchuanImg);
        } else {
            //隐藏菊花
            StyledDialog.dismissLoading();
            ToastUtil.showShortToast(getContext(),"上传失败");
        }
    }
}
