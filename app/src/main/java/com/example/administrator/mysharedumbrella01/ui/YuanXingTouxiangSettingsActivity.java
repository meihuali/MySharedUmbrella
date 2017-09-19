package com.example.administrator.mysharedumbrella01.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.SaoYiSao.ScannerActivity;
import com.example.administrator.mysharedumbrella01.dialog.CustomDialog;
import com.example.administrator.mysharedumbrella01.entivity.ShangChuanTouXiangBean;
import com.example.administrator.mysharedumbrella01.peresenet.ShangChuanTouXiangPersernet;
import com.example.administrator.mysharedumbrella01.utils.ConfigUtils;
import com.example.administrator.mysharedumbrella01.utils.GlideUtils;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.SystemUiUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsShangChuanTouXiangView;
import com.gyf.barlibrary.ImmersionBar;
import com.whyalwaysmea.circular.AnimUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/6/5 0005.
 *  这个是圆形头像的界面
 */

public class YuanXingTouxiangSettingsActivity extends AppCompatActivity implements View.OnClickListener, IsShangChuanTouXiangView {
    private ImageView imge_backes;
    private CircleImageView image_yuanxing;
    private CustomDialog dialog;
    private TextView btn_cancel;
    private Button btn_picture;
    private Button btn_camera;
    private PromptDialog promptDialog;
    private static final int REQUEST_CODE_PERMISSION_SD = 100;
    private static final int REQUEST_CODE_PERMISSION_OTHER = 101;
    private static final int REQUEST_CODE_PERMISSION_CAMER = 102;

    private static final int REQUEST_CODE_SETTING = 300;
    private File myCaptureFile;
    private TextView tv_UserNick;
    private TextView tv_userPhone;
    private View ll_layout_amin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsyuanxingtouxiang);

        AnimUtils.animhpel((Activity) this,R.id.ll_layout_amin);

        //创建dialog对象
        promptDialog = new PromptDialog(this);
        //沉浸式
/*        ImmersionBar.with(this)
                .statusBarColor(R.color.top_red) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();*/
        ImmersionBar.with(this)
                .init();

        intiview();

    }
    /*初始化 */
    private void intiview() {

        tv_UserNick = (TextView)findViewById(R.id.tv_UserNick);
        tv_userPhone = (TextView)findViewById(R.id.tv_userPhone);
        String shoujiPhone = ShareUtils.getString(getApplicationContext(),"zhanghao","");
        String UserNick = ShareUtils.getString(getApplicationContext(),"username","");
        if (!TextUtils.isEmpty(shoujiPhone)) {
            tv_userPhone.setText(shoujiPhone);
        }
        if (!TextUtils.isEmpty(UserNick)) {
            tv_UserNick.setText(UserNick);
        }

        image_yuanxing = (CircleImageView) findViewById(R.id.image_yuanxing);
        image_yuanxing.setOnClickListener(this);
        //进来该界面的时候去取图片路径设置在控件上
        String  imageurl = ShareUtils.getString(getApplicationContext(),"touxiangURL","");
        if (!TextUtils.isEmpty(imageurl)) {
            if (imageurl.contains("http")) {
                GlideUtils.loadImageViewCache(getApplicationContext(), imageurl, image_yuanxing);
            } else {
                String url = ConfigUtils.ZHU_YU_MING+"public/avatar/"+imageurl;
                GlideUtils.loadImageViewCache(getApplicationContext(), url, image_yuanxing);
            }
        }
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
//                dialog.show();
                //打开dialog
                openDialog();
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
                break;


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimUtils.finishonBackPressed(YuanXingTouxiangSettingsActivity.this,R.id.ll_layout_amin);
    }

    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case REQUEST_CODE_PERMISSION_SD: {
                    Toast.makeText(YuanXingTouxiangSettingsActivity.this, R.string.message_calendar_succeed, Toast.LENGTH_SHORT).show();
                    break;
                }
                case REQUEST_CODE_PERMISSION_OTHER: {
                    Toast.makeText(YuanXingTouxiangSettingsActivity.this, R.string.message_post_succeed, Toast.LENGTH_SHORT).show();
                    break;
                }
                case REQUEST_CODE_PERMISSION_CAMER: {
                    Toast.makeText(YuanXingTouxiangSettingsActivity.this, R.string.xiangjiqyuanxian, Toast.LENGTH_SHORT).show();
                    toCamera(); //打开相机

                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode) {
                case REQUEST_CODE_PERMISSION_SD: {
                    Toast.makeText(YuanXingTouxiangSettingsActivity.this, R.string.message_calendar_failed, Toast.LENGTH_SHORT).show();
                    break;
                }
                case REQUEST_CODE_PERMISSION_OTHER: {
                    Toast.makeText(YuanXingTouxiangSettingsActivity.this, R.string.message_post_failed, Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(YuanXingTouxiangSettingsActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(YuanXingTouxiangSettingsActivity.this, REQUEST_CODE_SETTING).show();

                // 第二种：用自定义的提示语。
//             AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING)
//                     .setTitle("权限申请失败")
//                     .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
//                     .setPositiveButton("好，去设置")
//                     .show();

//            第三种：自定义dialog样式。
//            SettingService settingService = AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
//            你的dialog点击了确定调用：
//            settingService.execute();
//            你的dialog点击了取消调用：
//            settingService.cancel();
            }
        }
    };

    private void openDialog() {
        //可创建android效果的底部Sheet选择，默认IOS效果，sheetCellPad=0为Android效果的Sheet
//                promptDialog.getAlertDefaultBuilder().sheetCellPad(0).round(0);
        //设置按钮的特点，颜色大小什么的，具体看PromptButton的成员变量
        PromptButton cancle = new PromptButton("取消", null);
        cancle.setTextColor(Color.parseColor("#0076ff"));
        //设置显示的文字大小及颜色
//                promptDialog.getAlertDefaultBuilder().textSize(12).textColor(Color.GRAY);
        //默认两个按钮为Alert对话框，大于三个按钮的为底部SHeet形式展现
        promptDialog.showAlertSheet("", true, cancle,
                new PromptButton("打开相册", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                        toPicture(); //打开相册
                    }
                }),
                new PromptButton("打开相机", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton promptButton) {

                        //动态授权 相机权限
                        dongtaishouquan();
                    }
                }),
                new PromptButton("请选择上传头像的方式", null));
    }

    private void dongtaishouquan() {
        // 申请单个权限。
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_CAMER)
                .permission(Manifest.permission.CAMERA)
                .callback(permissionListener)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(YuanXingTouxiangSettingsActivity.this, rationale).
                                show();
                    }
                })
                .start();
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
            File file =  saveBitmapFile(bitmap);
            L.e("xiangce "+file);
            promptDialog.showLoading("头像上传中···");
            ShangChuanTouXiangPersernet sctxp = new ShangChuanTouXiangPersernet(this);
            sctxp.fach(file,this);
            image_yuanxing.setImageBitmap(bitmap);
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
        String zh = ShareUtils.getString(getApplicationContext(),"zhanghao","");
        try {
            String path = getSDPath() +"/revoeye/";
            File dirFile = new File(path);
            if(!dirFile.exists()){
                dirFile.mkdir();
            }
            myCaptureFile = new File(path + UUID.randomUUID()+".png");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCaptureFile;
    }
    /*
    * 获取sd卡的 路径
    * */
    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    /*这个是 打开相册 上传图片后的 回调结果*/
    @Override
    public void ShowRest(ShangChuanTouXiangBean sctxb) {
        L.e("上传头像结果 "+sctxb);
        int status = sctxb.getStatus();
        if (status == 1) {
            promptDialog.dismiss();
            String datas = sctxb.getData();
            ShareUtils.putString(getApplicationContext(),"touxiangURL",datas);
        } else {
            promptDialog.dismiss();
            ToastUtil.showShortToast(getApplicationContext(), sctxb.getData());
        }
    }
}
