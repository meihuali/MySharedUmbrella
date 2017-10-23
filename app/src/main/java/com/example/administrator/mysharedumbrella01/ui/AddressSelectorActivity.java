package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.entivity.EditShoppingAddressBean;
import com.example.administrator.mysharedumbrella01.entivity.ShoppingAddressBean;
import com.example.administrator.mysharedumbrella01.peresenet.AddAddressPerserent;
import com.example.administrator.mysharedumbrella01.peresenet.EditShoppingAddressPerserent;
import com.example.administrator.mysharedumbrella01.utils.MyDialog;
import com.example.administrator.mysharedumbrella01.utils.RegularUtil;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsAddAddressView;
import com.example.administrator.mysharedumbrella01.view.IsEditShoppingAddressView;
import com.google.zxing.common.StringUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;

import java.util.ArrayList;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：AddressSelectorActivity
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/13 0013 17:24
 * 描述：这里就是开始选择地址的界面了·
 */
public class AddressSelectorActivity extends AppCompatActivity implements View.OnClickListener, IsAddAddressView, IsEditShoppingAddressView {
    private TextView tv_city1;
    private City city;
    private ArrayList<City> toCitys;
    private TextView tv_mingxi;

    private EditText et_shouhuoren;
    private EditText et_Mobile;
    private EditText et_youbian;
    private LinearLayout img_status;
    private EditText et_xiangxidizhi;
    private boolean isFrist = false;
    private ImageView img_gouxuan, img_weigouxuan;
    private int type;
    private String dizhia;
    private String types;
    private String id;
    private String namesss;
    private String phoness;
    private String diquss;
    private String xiangxidizhiasss;
    private String statuss;
    private String youbianss;
    private ImageView image_back;
    private String seclectStatus;
    private RelativeLayout rel_layout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressselectoractivity);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        types = intent.getStringExtra("type");
        id = intent.getStringExtra("id");
        //获取用户名
        namesss = intent.getStringExtra("name");
        //获取手机号码
        phoness = intent.getStringExtra("phone");
        //获取地区
        diquss = intent.getStringExtra("diqu");
        //获取详细地址
        xiangxidizhiasss = intent.getStringExtra("xiangxidizhi");
        //获取状态
        statuss = intent.getStringExtra("status");
        //获取邮编
        youbianss = intent.getStringExtra("youbian");
        //获取被点击的地址的下标
        rel_layout = (RelativeLayout) findViewById(R.id.rel_layout);
        seclectStatus = intent.getStringExtra("seclectStatus");
        if (seclectStatus != null) {
            type = Integer.parseInt(seclectStatus);
            if (seclectStatus.equals("1")) {
                rel_layout.setVisibility(View.GONE);
            }
        }


    }

    /*
    * 初始化数据
    * */
    private void initView() {

        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        if (types.equals("1")) {
            et_xiangxidizhi = (EditText) findViewById(R.id.et_xiangxidizhi);
            et_xiangxidizhi.setText(xiangxidizhiasss);
            img_status = (LinearLayout) findViewById(R.id.img_status);
            img_status.setOnClickListener(this);
            et_youbian = (EditText) findViewById(R.id.et_youbian);
            et_youbian.setText(youbianss);
            et_Mobile = (EditText) findViewById(R.id.et_Mobile);
            et_Mobile.setText(phoness);
            et_shouhuoren = (EditText) findViewById(R.id.et_shouhuoren);
            et_shouhuoren.setText(namesss);
            tv_city1 = (TextView) findViewById(R.id.tv_city1);
            tv_city1.setOnClickListener(this);
            tv_city1.setText(diquss);
            img_weigouxuan = (ImageView) findViewById(R.id.img_weigouxuan);
            img_gouxuan = (ImageView) findViewById(R.id.img_gouxuan);
            if (statuss.equals("0")) {
                img_weigouxuan.setVisibility(View.VISIBLE);
                img_gouxuan.setVisibility(View.GONE);
            } else {
                img_weigouxuan.setVisibility(View.GONE);
                img_gouxuan.setVisibility(View.VISIBLE);
            }
        }
        et_xiangxidizhi = (EditText) findViewById(R.id.et_xiangxidizhi);
        img_status = (LinearLayout) findViewById(R.id.img_status);
        img_status.setOnClickListener(this);
        et_youbian = (EditText) findViewById(R.id.et_youbian);
        et_Mobile = (EditText) findViewById(R.id.et_Mobile);
        et_shouhuoren = (EditText) findViewById(R.id.et_shouhuoren);
        img_weigouxuan = (ImageView) findViewById(R.id.img_weigouxuan);
        img_gouxuan = (ImageView) findViewById(R.id.img_gouxuan);
        tv_mingxi = (TextView) findViewById(R.id.tv_mingxi);
        tv_mingxi.setOnClickListener(this);
        tv_city1 = (TextView) findViewById(R.id.tv_city1);
        tv_city1.setOnClickListener(this);
        city = new City();
        toCitys = new ArrayList<City>();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_city1:
                Intent in = new Intent(this, CitySelect1Activity.class);
                in.putExtra("city", city);
                startActivityForResult(in, 1);
                break;
            //点击保存用户 选取的地址
            case R.id.tv_mingxi:
                addSave();
                break;
            //点击选择默认状态
            case R.id.img_status:
                if (isFrist) {
                    img_weigouxuan.setVisibility(View.VISIBLE);
                    img_gouxuan.setVisibility(View.GONE);
                    type = 0;
                    isFrist = false;
                } else {
                    img_gouxuan.setVisibility(View.VISIBLE);
                    img_weigouxuan.setVisibility(View.GONE);
                    type = 1;
                    isFrist = true;
                }
                break;
        }
    }

    /*
    * 点击保存地址到服务器
    * */
    private void addSave() {
        //收获人
        String shouhuoren = et_shouhuoren.getText().toString().trim();
        //邮编
        String youbian = et_youbian.getText().toString().trim();
        //手机号码
        String mobile = et_Mobile.getText().toString().trim();
        // 地区
        String diqu = tv_city1.getText().toString().trim();
        //详细地址
        String xiangxidizhi = et_xiangxidizhi.getText().toString().trim();

        //获取商家主键ID
        String shoppingid = ShareUtils.getString(getApplicationContext(), "shoppingId", "");
        if (types.equals("1")) { //types 等于1 表示 是修改联系地址
            if (RegularUtil.isMobile(mobile)) {
                if (RegularUtil.isPostalCode(youbian)) {
                    EditShoppingAddressPerserent editAddress = new EditShoppingAddressPerserent(this);
                    editAddress.editAddress(shouhuoren, mobile, youbian, diqu, xiangxidizhi, shoppingid, String.valueOf(type), id);
                } else {
                    MyDialog.dialog("提示", "请输入6位数邮政编码", "确定", "");
                }

            } else {
                MyDialog.dialog("提示", "请输入正确的手机号码！", "确定", "");
            }


        } else { //否则就是新增收获地址
            if (!TextUtils.isEmpty(shouhuoren) & !TextUtils.isEmpty(diqu) & !TextUtils.isEmpty(xiangxidizhi)) {
                //判断手机号码
                if (RegularUtil.isMobile(mobile)) {
                    //判断邮政编码
                    if (RegularUtil.isPostalCode(youbian)) {
                        StyledDialog.buildLoading("添加中···").show();
                        AddAddressPerserent mAddress = new AddAddressPerserent(this);
                        mAddress.addAddress(shouhuoren, mobile, youbian, dizhia, xiangxidizhi, shoppingid, type);
                    } else {
                        MyDialog.dialog("提示", "请输入6位数邮政编码", "确定", "");
                    }

                } else {
                    MyDialog.dialog("提示", "请输入正确的手机号码！", "确定", "");
                }

            } else {
                ToastUtil.showShortToast(getApplicationContext(), "输入框内不能为空");
            }
        }

    }

    /*
    * 这里是用户选择完毕城市后 返回的结果
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 8) {
            if (requestCode == 1) {
                city = data.getParcelableExtra("city");
                dizhia = city.getProvince() + city.getCity() + city.getDistrict();
                tv_city1.setText(dizhia);

            } else if (requestCode == 2) {
                toCitys = data.getParcelableArrayListExtra("toCitys");
                StringBuffer ab = new StringBuffer();
                for (int i = 0; i < toCitys.size(); i++) {
                    if (i == toCitys.size() - 1) {//如果是最后一个城市就不需要逗号
                        ab.append(toCitys.get(i).getCity());
                    } else {
                        StringBuffer a = ab.append(toCitys.get(i).getCity() + "， ");//如果不是最后一个城市就需要逗号
                        tv_city1.setText(a);
                    }
                }
            }
        }
    }

    /*
    * 添加收获地址 回调
    * */
    @Override
    public void showReluest(Object object) {
        ShoppingAddressBean addressBean = (ShoppingAddressBean) object;
        int status = addressBean.getStatus();
        if (status == 1) {
            StyledDialog.dismissLoading();
            ShoppingAddressBean.DataBean spben = addressBean.getData();
            StyledDialog.buildIosAlert("标题", "恭喜您添加成功", new MyDialogListener() {
                @Override
                public void onFirst() {
                    finish();
                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "").show();
        } else {
            StyledDialog.dismissLoading();
        }
    }

    /*
    *  修改某一条 联系地址
    * */
    @Override
    public void showRlrouts(Object object) {
        EditShoppingAddressBean editBean = (EditShoppingAddressBean) object;
        int status = editBean.getStatus();
        if (status == 1) {
            ToastUtil.showShortToast(getApplicationContext(), "修改成功");
            finish();
        } else {
            StyledDialog.buildIosAlert("地址", "修改地址失败" + editBean.getData().getError_reason(), new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("确定", "").show();
        }
    }
}
