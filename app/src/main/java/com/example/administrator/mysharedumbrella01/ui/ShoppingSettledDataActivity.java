package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingUserRegisterBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShoppingUserRegisterPersernt;
import com.example.administrator.mysharedumbrella01.utils.EditTextWithDelete;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsShoppingUserRegisterView;
import com.gyf.barlibrary.ImmersionBar;

import java.io.File;
import java.util.ArrayList;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingSettledDataActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/9 0009 18:08
 * 描述： 该界面是 入驻商家需要提供资料(最终的)
 */
public class ShoppingSettledDataActivity extends AppCompatActivity implements View.OnClickListener, IsShoppingUserRegisterView {
    private ImageView img_paizhao;
    private static final int REQUEST_CODE = 1024;
    private String pathImg;
    private ImageView show_img;
    private Button btn_comit;
    private EditTextWithDelete edit_phone;
    private EditTextWithDelete et_address;
    private PromptDialog promptDialog;
    private LinearLayout ll_layout_qianyue;
    private boolean isFrist = true;
    private ImageView img_qianyueweigouxuan,img_qianyuegouxuan;
    private ImageView image_backs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingsettleddata);
         promptDialog = new PromptDialog(this);
        //沉浸式
        ImmersionBar.with(this)
                .init();
        initView();

    }
    /*
    * 初始化
    * */
    private void initView() {
        image_backs = (ImageView) findViewById(R.id.image_backs);
        image_backs.setOnClickListener(this);
        img_qianyuegouxuan  = (ImageView) findViewById(R.id.img_qianyuegouxuan);
        img_qianyueweigouxuan = (ImageView) findViewById(R.id.img_qianyueweigouxuan);
        ll_layout_qianyue = (LinearLayout) findViewById(R.id.ll_layout_qianyue);
        ll_layout_qianyue.setOnClickListener(this);
        et_address = (EditTextWithDelete) findViewById(R.id.et_address);
        edit_phone = (EditTextWithDelete) findViewById(R.id.edit_phone);
        btn_comit = (Button) findViewById(R.id.btn_comit);
        btn_comit.setOnClickListener(this);
        show_img = (ImageView) findViewById(R.id.show_img);
        img_paizhao = (ImageView) findViewById(R.id.img_paizhao);
        img_paizhao.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_backs:
                finish();
                break;
            //拍照
            case R.id.img_paizhao:
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needCamera(R.drawable.ic_boxing_camera_white).needGif().withMaxCount(1);
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, REQUEST_CODE);
                break;
            case R.id.btn_comit:
             promptDialog.showLoading("正在提交中···");
                //获取商家店名
                String dianing = edit_phone.getText().toString().trim();
                // 获取商家地址
                String address = et_address.getText().toString().trim();
                //获取手机号码
                String phone = ShareUtils.getString(getApplicationContext(),"zhanghao","");

                //请求网络 入驻商家
                ShoppingUserRegisterPersernt shoppinguser = new ShoppingUserRegisterPersernt(this);
                shoppinguser.shoppingRegsiert(dianing,phone,new File(pathImg),address);
                break;
            case R.id.ll_layout_qianyue:
                if (isFrist) {
                    img_qianyueweigouxuan.setVisibility(View.GONE);
                    img_qianyuegouxuan.setVisibility(View.VISIBLE);
                    isFrist = false;
                } else {
                    img_qianyueweigouxuan.setVisibility(View.VISIBLE);
                    img_qianyuegouxuan.setVisibility(View.GONE);
                    isFrist = true;
                }
                break;
        }
    }


    /*
    * 选择拍照的结果
    * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final ArrayList<BaseMedia> medias = Boxing.getResult(data);
            L.e("选择图片的结果 "+medias.size());
            for (int i = 0; i < medias.size(); i++) {
                pathImg = medias.get(i).getPath();
            }
            //这里将图片设置在控件上
           // BoxingMediaLoader.getInstance().displayThumbnail(show_img, pathImg, 400, 400);
            Glide.with(getApplicationContext()).load(new File(pathImg)).into(show_img);
        }
    }
    /*
    * 商家入驻后 接口回调
    * */
    @Override
    public void showShoppingRrelst(Object object) {
        ShoppingUserRegisterBean shangjia = (ShoppingUserRegisterBean) object;
        int status = shangjia.getStatus();
        if (status == 1) {
            promptDialog.dismiss();
            ToastUtil.showShortToast(getApplicationContext(),shangjia.getData().getSuccess());

//            String is_aut =  shangjia.getIs_Authentication();
//            if (is_aut.equals("2")) {
//                ShareUtils.putString(getApplicationContext(),"is_aut",is_aut);
//
//            }

            finish();
        } else {
            promptDialog.dismiss();
            ToastUtil.showShortToast(getApplicationContext(),shangjia.getData().getSuccess());
        }
    }
}
