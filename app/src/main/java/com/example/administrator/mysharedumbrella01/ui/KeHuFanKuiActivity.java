package com.example.administrator.mysharedumbrella01.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.FanKuiAdapter;
import com.example.administrator.mysharedumbrella01.Adapter.HistoricalAdapter;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.FanKuiBean;
import com.example.administrator.mysharedumbrella01.entivity.KeFuFanKuiBean;
import com.example.administrator.mysharedumbrella01.entivity.KefufankuisBean;
import com.example.administrator.mysharedumbrella01.peresenet.IsKefufankuisPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.KefufankuiPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.ShangChuanTouXiangPersernet;
import com.example.administrator.mysharedumbrella01.transition.Utilss;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsKefufankuiView;
import com.example.administrator.mysharedumbrella01.view.IsKefufankuisView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.whyalwaysmea.circular.AnimUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/7/11 0011.
 * <p>
 * 客户返回 的界面
 */

public class KeHuFanKuiActivity extends AppCompatActivity implements View.OnClickListener, IsKefufankuiView, IsKefufankuisView {
    private ImageView image_back;
    private RecyclerView mRecyclerView;
    private FanKuiAdapter fanKuiAdapter;
    private List<KeFuFanKuiBean.DataBean> mlist = new ArrayList<>();
    private ImageView img_cemacr;
    private PromptDialog promptDialog;

    public static final int IMAGE_REQUEST_CODE = 101;
    private static final int REQUEST_CODE_PERMISSION_CAMER = 102;
    private static final int REQUEST_CODE_PERMISSION_SD = 100;
    private static final int REQUEST_CODE_PERMISSION_OTHER = 101;
    private static final int REQUEST_CODE_SETTING = 300;
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final int RESULT_REQUEST_CODE = 103;
    private File tempFile = null;
    private File myCaptureFile;
    private  View ll_layout_a;
    private Button btn_kefufankui;
    private String typeID;
    private String typeids;
    private File file;
    private EditText et_bodyss;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kehufankui);
        //创建dialog对象
        promptDialog = new PromptDialog(this);
        //沉浸式
/*        ImmersionBar.with(this)
                .statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();*/
        ImmersionBar.with(this)
                .init();

        initView();
        initData();
    }

    private void initData() {

        //这里请求客户反馈问题列表 的接口
        KefufankuiPerserent wentiliebiao = new KefufankuiPerserent(this);
        wentiliebiao.kehufankui();
    }

    private void initView() {
        et_bodyss = (EditText) findViewById(R.id.et_bodyss);
        btn_kefufankui = (Button) findViewById(R.id.btn_kefufankui);
        btn_kefufankui.setOnClickListener(this);

        //过渡动画
        AnimUtils.animhpel(this,R.id.ll_layoutss);
        img_cemacr = (ImageView) findViewById(R.id.img_cemacr);
        img_cemacr.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        //初始化recycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器 //这里默认是竖着的，想横着只需要再setlayout传入第2个参数 Layout。horreaoy
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //这一句是设置横向 两列
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        fanKuiAdapter = new FanKuiAdapter(R.layout.fankui_item, mlist, getApplicationContext());
        mRecyclerView.setAdapter(fanKuiAdapter);
//        这一句是开启 item 动画
        fanKuiAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        fanKuiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mlist.size(); i++) {
                    if (i == position) {
                        //   mlist.get(i).setSelect(!mlist.get(i).isSelect());
                        mlist.get(i).setSelect(true);
                    } else {
                        mlist.get(i).setSelect(false);
                    }
                }
                fanKuiAdapter.notifyDataSetChanged();
                KeFuFanKuiBean.DataBean kffkbb = (KeFuFanKuiBean.DataBean) adapter.getItem(position);
                typeids = kffkbb.getId();

            }
        });

        //这里点击按按钮 启动照相机

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                AnimUtils.finishAmins(KeHuFanKuiActivity.this,R.id.ll_xxx,view,R.id.ll_layoutss);
                break;
            case R.id.img_cemacr:
                openDialog();
                break;
            case R.id.btn_kefufankui:
                promptDialog.showLoading("正在提交问题···");
                String bodys = et_bodyss.getText().toString().trim();
                String zh = ShareUtils.getString(getApplicationContext(),"zhanghao","");
                if (!TextUtils.isEmpty(zh)) {
                    if (!TextUtils.isEmpty(typeids)) {
                        IsKefufankuisPerserent kefufankui = new IsKefufankuisPerserent(this);
                        kefufankui.kefufankui(zh, typeids, file, bodys);
                    } else {
                        ToastUtil.showShortToast(getApplicationContext(), "请选择问题！");
                    }
                } else {
                    ToastUtil.showShortToast(getApplicationContext(),"请先登录账号！");
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        AnimUtils.finishonBackPressed(KeHuFanKuiActivity.this,R.id.ll_layoutss);
    }

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
                        AndPermission.rationaleDialog(KeHuFanKuiActivity.this, rationale).
                                show();
                    }
                })
                .start();
    }

    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
//                case REQUEST_CODE_PERMISSION_SD: {
//                    Toast.makeText(KeHuFanKuiActivity.this, R.string.message_calendar_succeed, Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                case REQUEST_CODE_PERMISSION_OTHER: {
//                    Toast.makeText(KeHuFanKuiActivity.this, R.string.message_post_succeed, Toast.LENGTH_SHORT).show();
//                    break;
//                }
                case REQUEST_CODE_PERMISSION_CAMER: {
                    Toast.makeText(KeHuFanKuiActivity.this, R.string.xiangjiqyuanxian, Toast.LENGTH_SHORT).show();
                    toCamera(); //打开相机
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode) {
                case REQUEST_CODE_PERMISSION_SD: {
                    Toast.makeText(KeHuFanKuiActivity.this, R.string.message_calendar_failed, Toast.LENGTH_SHORT).show();
                    break;
                }
                case REQUEST_CODE_PERMISSION_OTHER: {
                    Toast.makeText(KeHuFanKuiActivity.this, R.string.message_post_failed, Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(KeHuFanKuiActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(KeHuFanKuiActivity.this, REQUEST_CODE_SETTING).show();


            }
        }
    };

    //跳转相机
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的话就进行储存
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        promptDialog.dismiss();
    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
        promptDialog.dismiss();
    }

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
            file = saveBitmapFile(bitmap);

            byte[] bitmapby = getBitmapByte(bitmap);

            //这里是请求网络上传到服务器了
 /*           ShangChuanTouXiangPersernet sctxp = new ShangChuanTouXiangPersernet(KeHuFanKuiActivity.this);
            sctxp.fach(file,this);*/
            img_cemacr.setImageBitmap(bitmap);
        }
    }
    /*
    * 将bitmap转成二进制数组
    * */
    public byte[] getBitmapByte(Bitmap bitmap){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    //Bitmap对象保存图片文件
    public File saveBitmapFile(Bitmap bitmap) {
        String zh = ShareUtils.getString(getApplicationContext(), "zhanghao", "");
        try {
            String path = getSDPath() + "/revoeye/";
            File dirFile = new File(path);
            if (!dirFile.exists()) {
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
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }


    /*
    * 这个就是客服 问题 列表的 那个接口回调
    * */
    @Override
    public void showRelout(Object object) {

        KeFuFanKuiBean keFuFanKuiBean = (KeFuFanKuiBean) object;
        int status = keFuFanKuiBean.getStatus();
        if (status == 1) {
            List<KeFuFanKuiBean.DataBean> list =  keFuFanKuiBean.getData();
            mlist.addAll(list);
            fanKuiAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(),"服务器故障",Toast.LENGTH_SHORT).show();
        }
    }

    /*
    * 用户反馈的 问题·回调
    * */
    @Override
    public void ShowBugFankui(Object object) {
        promptDialog.dismiss();
        KefufankuisBean kf = (KefufankuisBean) object;
        if (kf.getData().equals("反馈成功")) {
            StyledDialog.buildIosAlert("问题反馈", kf.getData() + ", 您的问题我们将在12小时内立即处理！", new MyDialogListener() {
                @Override
                public void onFirst() {
                    finish();
                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "").show();
        } else {
            StyledDialog.buildIosAlert("问题反馈", kf.getData() , new MyDialogListener() {
                @Override
                public void onFirst() {
                    finish();
                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "").show();
        }

      //  finish();
        //   ToastUtil.showShortToast(getApplicationContext(),kf.getData());
    }
}
