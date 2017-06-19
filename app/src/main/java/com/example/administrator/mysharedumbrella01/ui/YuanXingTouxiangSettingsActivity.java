package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.CustomDialog;
import com.example.administrator.mysharedumbrella01.entivity.ShangChuanTouXiangBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShangChuanTouXiangPersernet;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanTouXiangView;
import com.gyf.barlibrary.ImmersionBar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class YuanXingTouxiangSettingsActivity extends AppCompatActivity implements View.OnClickListener, IsShangChuanTouXiangView {
    private ImageView imge_backes;
    private CircleImageView image_yuanxing;
    private CustomDialog dialog;
    private TextView btn_cancel;
    private Button btn_picture;
    private Button btn_camera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsyuanxingtouxiang);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.top_red) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        intiview();

    }
    /*初始化 */
    private void intiview() {

        image_yuanxing = (CircleImageView) findViewById(R.id.image_yuanxing);
        image_yuanxing.setOnClickListener(this);
        imge_backes = (ImageView) findViewById(R.id.imge_backes);
        imge_backes.setOnClickListener(this);

        //初始化dialog
        dialog = new CustomDialog(this, 0, 0,
                R.layout.dialog_photo, R.style.pop_anim_style, Gravity.BOTTOM, 0);
        //提示框以外点击无效
        dialog.setCancelable(false);
        btn_camera = (Button) dialog.findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(this);
        btn_picture = (Button) dialog.findViewById(R.id.btn_picture);
        btn_picture.setOnClickListener(this);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_backes:
                finish();
                break;
            case R.id.image_yuanxing:
                //打开dialog
                dialog.show();
                break;
            //取消
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            //打开相册
            case R.id.btn_picture:
                toPicture(); //打开相册
                break;
            case R.id.btn_camera:
                toCamera();
                break;


        }
    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
        dialog.dismiss();
    }

    //回调


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != this.RESULT_CANCELED) {
            switch (requestCode) {
                //相册数据
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                //相机数据
                case CAMERA_REQUEST_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能点击舍弃
                    if (data != null) {
                        //拿到图片设置
                        setImageToView(data);
                        //既然已经设置了图片，我们原先的就应该删除
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }

    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int IMAGE_REQUEST_CODE = 101;
    public static final int RESULT_REQUEST_CODE = 102;
    private File tempFile = null;

    //裁剪
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            L.e("uri == null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            image_yuanxing.setImageBitmap(bitmap);
            File file =  saveBitmapFile(bitmap);
            L.e("xiangce "+file);
            ShangChuanTouXiangPersernet sctxp = new ShangChuanTouXiangPersernet(this);
            sctxp.fach(file,this);
        }
    }

    //跳转相机
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的话就进行储存
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        dialog.dismiss();
    }

    //Bitmap对象保存图片文件
    public File saveBitmapFile(Bitmap bitmap){
        String path =  "/mnt/sdcard/pic"+UUID.randomUUID()+"01.jpg";
        File file=new File(path);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
        /*这个是 打开相册 上传图片后的 回调结果*/
    @Override
    public void ShowRest(ShangChuanTouXiangBean sctxb) {

    }
}
