package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.AdminBandinBean;
import com.example.administrator.mysharedumbrella01.peresenet.ComiteBindingPerenset;
import com.example.administrator.mysharedumbrella01.seclectmaploction.LocationActivity;
import com.example.administrator.mysharedumbrella01.utils.L;
import com.example.administrator.mysharedumbrella01.utils.MyDialog;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsComiteBindingView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：AdministratorInformation
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/13 0013 17:14
 * 描述：这个就是 信息认证扫描后调转到的这个 界面
 */
public class AdministratorInformation extends AppCompatActivity implements View.OnClickListener, IsComiteBindingView {

    private ImageView image_back;
    private TextView tv_sanzuoid;
    private TextView tv_dianwo;
    private TextView tv_shoppingSeclect;
    private TextView tv_mingxi;
    private String sanzuoid;
    private String merchant_id;
    private String jingdu,weidu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }

    private void initView() {
        tv_mingxi = (TextView) findViewById(R.id.tv_mingxi);
        tv_mingxi.setOnClickListener(this);
        tv_shoppingSeclect = (TextView)findViewById(R.id.tv_shoppingSeclect);
        tv_shoppingSeclect.setOnClickListener(this);
        tv_dianwo = (TextView)findViewById(R.id.tv_dianwo);
        tv_dianwo.setOnClickListener(this);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        tv_sanzuoid = (TextView) findViewById(R.id.tv_sanzuoid);
        Intent intent = getIntent();
         sanzuoid =  intent.getStringExtra("sanzuoID");
        tv_sanzuoid.setText(sanzuoid);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_dianwo:
                //这里跳转到 地图选择界面让用户选择
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                startActivityForResult(intent,100);
                break;
            //商户名称选择
            case R.id.tv_shoppingSeclect:
                Intent intent1 = new Intent(getApplicationContext(),ShoppingNameSeclectActivity.class);
                startActivityForResult(intent1,102);
                break;
                //保存用户选择的数据 提交绑定
            case R.id.tv_mingxi:
                StyledDialog.buildLoading("保存中···").show();
                ComiteBindingPerenset comiteBindingPerenset = new ComiteBindingPerenset(this);
                String phone = ShareUtils.getString(getApplicationContext(),"zhanghao","");
                comiteBindingPerenset.bangding(phone,sanzuoid,merchant_id,jingdu,weidu);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 101:
                if (data != null) {
                    String change01 = data.getStringExtra("change01");
                    L.e("最终结果来了 "+change01);
                    //这里设置选择后的地理位置
                    tv_dianwo.setText(change01);
                    //经度
                      jingdu = data.getStringExtra("jingdu");
                    //维度
                      weidu = data.getStringExtra("weidu");

                }
                break;
            case 102:
                if (data != null) {
                    String change02 = data.getStringExtra("change02");
                     merchant_id = data.getStringExtra("merchant_id");
                    L.e("商家名称来了呀  "+change02);
                    tv_shoppingSeclect.setText(change02);
                }
                break;

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    /*
    * 该回调是 点击保存后把 商家 选择的数据 提交到服务器
    * */
    @Override
    public void showResulets(Object object) {
        StyledDialog.dismissLoading();
        AdminBandinBean bandinBean = (AdminBandinBean) object;
        int state = bandinBean.getStatus();
        if (state == 1) {
            StyledDialog.buildIosAlert("提示", bandinBean.getData().getSuccess(), new MyDialogListener() {
                @Override
                public void onFirst() {
                    finish();
                }
                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "").show();
        } else {
            MyDialog.dialog("警告",bandinBean.getData().getError_reason(),"确定","");
        }
    }
}
