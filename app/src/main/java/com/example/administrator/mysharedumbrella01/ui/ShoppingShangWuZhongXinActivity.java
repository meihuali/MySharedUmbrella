package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.ShangChuanTouXiangBean;
import com.example.administrator.mysharedumbrella01.entivity.ShangJiaRenZhenBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShangChuanTouXiangPersernet;
import com.example.administrator.mysharedumbrella01.peresenet.ShangWuzhongxinrenzhengPersert;
import com.example.administrator.mysharedumbrella01.utils.BoxingGlideLoader;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanTouXiangView;
import com.example.administrator.mysharedumbrella01.view.IsShangWuzhongxinRenzhengView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.whyalwaysmea.circular.AnimUtils;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.leefeng.promptlibrary.PromptDialog;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static java.lang.System.load;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShoppingShangWuZhongXinActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/7 0007 19:46
 * 描述：商家商务中心
 */
public class ShoppingShangWuZhongXinActivity extends AppCompatActivity implements View.OnClickListener, IsShangWuzhongxinRenzhengView, IsShangChuanTouXiangView {
    //认证按钮
    private TextView tv_shoppingRz;
    //商家手机号码
    private TextView tv_UserNick;
    //商家名字
    private TextView tv_name;
    //微信绑定状态
    private TextView tv_wechat;
    // QQ绑定状态
    private TextView tv_QQ;
    private String id;
    private CircleImageView image_yuanxing;
    private static final int REQUEST_CODE = 1024;
    private String pathImg;
    private PromptDialog promptDialog;
    private String imgPath;
    private ImageView image_backs;
    private File files;
    private View ll_layout;
    private View ll_flayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangwuzhongxin);
        AnimUtils.animhpel((Activity) this,R.id.ll_layout);
        promptDialog = new PromptDialog(this);

        //沉浸式
        ImmersionBar.with(this)
                //  .transparentBar()
                .init();
        initView();
        initData();

    }

    /*
    * 获取服务器返回商务中心的所有信息
    * */
    private void initData() {
        id =  ShareUtils.getString(getApplicationContext(),"id","");
        ShangWuzhongxinrenzhengPersert shangwurenzhen = new ShangWuzhongxinrenzhengPersert(this);
        shangwurenzhen.shangwuzhongxinrenzhen(id);
    }
    /*
    * 商家界面提交资料成功后
    * 返回该界面后也需要调用该接口
    * */
    @Override
    protected void onResume() {
        super.onResume();
        ShangWuzhongxinrenzhengPersert shangwurenzhen = new ShangWuzhongxinrenzhengPersert(this);
        shangwurenzhen.shangwuzhongxinrenzhen(id);
    }

    private void initView() {
        image_backs = (ImageView) findViewById(R.id.image_backs);
        image_backs.setOnClickListener(this);
        image_yuanxing = (CircleImageView) findViewById(R.id.image_yuanxing);
        image_yuanxing.setOnClickListener(this);
        tv_shoppingRz = (TextView) findViewById(R.id.tv_shoppingRz);
        tv_shoppingRz.setOnClickListener(this);
        tv_UserNick = (TextView) findViewById(R.id.tv_UserNick);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_wechat = (TextView) findViewById(R.id.tv_wechat);
        tv_QQ = (TextView) findViewById(R.id.tv_QQ);
        //程序进来取出服务器返回的那个头像路径因为用户上传头像到服务器成功过后通过sp保存到本地了，在下面回调中保存了
        String photoImg = ShareUtils.getString(getApplicationContext(),"touxiangURL","");
        String url = ConfigUtils.ZHU_YU_MING+"public/avatar/"+photoImg;
        if (!TextUtils.isEmpty(photoImg)) {
            Glide.with(getApplicationContext()).load(url).into(image_yuanxing);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shoppingRz:
                startActivity(new Intent(getApplicationContext(),ShoppingDataActivity.class));
                break;
            //这里是商家上传头像 点击该按钮打开图库拍照
            case R.id.image_yuanxing:
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needCamera(R.drawable.ic_boxing_camera_white).needGif().withMaxCount(1);
                Boxing.of(config).withIntent(this, BoxingActivity.class).start(this, REQUEST_CODE);
                break;
            case R.id.image_backs:
                AnimUtils.finishAmins((Activity)this,R.id.ll_flayout,v,R.id.ll_layout);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final ArrayList<BaseMedia> medias = Boxing.getResult(data);
            L.e("选择图片的结果 "+medias.size());
            for (int i = 0; i < medias.size(); i++) {
                pathImg = medias.get(i).getPath();
            }
            if (!TextUtils.isEmpty(pathImg)) {

                promptDialog.showLoading("头像上传中···");
                //压缩图片
                compressWithLs(pathImg);


            }
        }
    }

    private void compressWithLs(String pathImg) {
        Luban.with(ShoppingShangWuZhongXinActivity.this)
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
                        //  BoxingMediaLoader.getInstance().displayThumbnail(image_showImage, pathImg, 450, 360);
                        Glide.with(ShoppingShangWuZhongXinActivity.this).load(files).into(image_yuanxing);
                        ShangChuanTouXiangPersernet sctxp = new ShangChuanTouXiangPersernet(ShoppingShangWuZhongXinActivity.this);
                        sctxp.fach(files,ShoppingShangWuZhongXinActivity.this);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    /*
    * 商务中心认证界面成功的回调
    * */
    @Override
    public void showSocces(Object object) {
        ShangJiaRenZhenBean shangjiarenzheng = (ShangJiaRenZhenBean) object;
        int status = shangjiarenzheng.getStatus();
        if (status == 1) {
            ShangJiaRenZhenBean.DataBean shangjiadata =  shangjiarenzheng.getData();
            //商家手机号码
            String phone = shangjiadata.getPhone();
            tv_UserNick.setText(phone);
            //商家的名字
            String shangjiaName =  shangjiadata.getNickname();
            tv_name.setText(shangjiaName);
            //获取微信绑定状态
            String wechatStatus = shangjiadata.getWechat();
            if (wechatStatus.equals("")) {
                tv_wechat.setText("未绑定");
            }
            //获取QQ绑定状态
            String qqStatus = shangjiadata.getQq();
            if (qqStatus.equals("")) {
                tv_QQ.setText("未绑定");
            }

            tv_shoppingRz.setText("已认证");
/*            //获取商家认证状态
            String AuthenStatus = shangjiadata.getIs_Authentication();
            if (AuthenStatus.equals("1")) {
                tv_shoppingRz.setText("已认证");
                tv_shoppingRz.setOnClickListener(null); //这里是屏蔽到点击事件
            } else if (AuthenStatus.equals("2")) {
                tv_shoppingRz.setText("认证中");
                tv_shoppingRz.setOnClickListener(null); //这里是屏蔽到点击事件
            } else {
                tv_shoppingRz.setText("去认证？");
                tv_shoppingRz.setTextColor(getResources().getColor(R.color.top_red));
            }*/

        } else {
         //   ToastUtil.showShortToast(getApplicationContext(),"服务器返回status不等于1");
        }
    }
    /*
    * 商务中心认证界面失败的回调
    * */
    @Override
    public void showError() {
        ToastUtil.showShortToast(getApplicationContext(),"服务器返回走Error了");
    }



    /*
    * 商家版本头像上传
    * */
    @Override
    public void ShowRest(ShangChuanTouXiangBean sctxb) {
        int status = sctxb.getStatus();
        if (status == 1) {
            promptDialog.dismiss();
            imgPath = sctxb.getData();
            ShareUtils.putString(getApplicationContext(),"touxiangURL",imgPath);
        } else {
            promptDialog.dismiss();
            ToastUtil.showShortToast(getApplicationContext(),"头像上传失败！");
        }
    }
}
