package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.dialog.ShoppingYajinDialog;
import com.example.administrator.mysharedumbrella01.entivity.SanZuoSanBean;
import com.example.administrator.mysharedumbrella01.peresenet.GetShoppingSanZuoSanPerserent;
import com.example.administrator.mysharedumbrella01.utils.ShareUtils;
import com.example.administrator.mysharedumbrella01.utils.ToastUtil;
import com.example.administrator.mysharedumbrella01.view.IsGetShoppingSanZuoSanView;
import com.gyf.barlibrary.ImmersionBar;
import com.whyalwaysmea.circular.AnimUtils;

/**
 * 项目名：MySharedUmbrella
 * 包名：com.example.administrator.mysharedumbrella01.ui
 * 文件名：ShanZuoSanActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/8 0008 11:47
 * 描述： 伞座，跟伞的界面
 */
public class ShanZuoSanActivity extends AppCompatActivity implements View.OnClickListener, IsGetShoppingSanZuoSanView {
    private Button btn_confirm;
    private ImageView imageView;
    private LinearLayout ll_layout_addres;
    private ImageView image_back;
    private TextView tv_sanzuo,tv_yusan;
    private ImageView imge_add,img_jian;
    private TextView tv_sums;
    private TextView tv_shoppingYJ;
    private int sum;
    private int cuont = 6 ;
    private ImageView img_yusan_add;
    private ImageView img_yusan_jian;
    private int yusanCuont = 18;
    private TextView tv_yusans;
    private TextView tv_addressName;
    private TextView tv_phone;
    private TextView tv_address;
    private View rl_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanzuosan);
        AnimUtils.animhpel( this,R.id.ll_layout_aimn);

        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse_x_x) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
        initdata();
    }
    /*
    * 发起网络请求
    * */
    private void initdata() {
        String shoppingid =  ShareUtils.getString(getApplicationContext(),"shoppingId","");
        GetShoppingSanZuoSanPerserent getsanzuosan = new GetShoppingSanZuoSanPerserent(this);
        getsanzuosan.getDatas(shoppingid);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initdata();
    }

    /*
        * 初始化
        * */
    private void initView() {
        tv_address  = (TextView) findViewById(R.id.tv_address);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_addressName = (TextView) findViewById(R.id.tv_addressName);

        tv_yusans = (TextView) findViewById(R.id.tv_yusans);

        img_yusan_jian = (ImageView) findViewById(R.id.img_yusan_jian);
        img_yusan_jian.setOnClickListener(this);
        img_yusan_add = (ImageView) findViewById(R.id.img_yusan_add);
        img_yusan_add.setOnClickListener(this);
        tv_shoppingYJ = (TextView) findViewById(R.id.tv_shoppingYJ);
        tv_sums = (TextView) findViewById(R.id.tv_sums);
        imge_add = (ImageView) findViewById(R.id.imge_add);
        imge_add.setOnClickListener(this);
        img_jian = (ImageView) findViewById(R.id.img_jian);
        img_jian.setOnClickListener(this);
        tv_sanzuo = (TextView) findViewById(R.id.tv_sanzuo);
        tv_yusan = (TextView) findViewById(R.id.tv_yusan);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
//        btn_confirm = (Button) findViewById(R.id.btn_confirm);
//        btn_confirm.setOnClickListener(this);
        ll_layout_addres = (LinearLayout) findViewById(R.id.ll_layout_addres);
        ll_layout_addres.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
               // finish();
                AnimUtils.finishAmins((Activity) this,R.id.rl_back,v,R.id.ll_layout_aimn);

                break;
            case R.id.btn_confirm:
                ShoppingYajinDialog shangjiayajin = new ShoppingYajinDialog(this);
                //设置pop 为全屏
                shangjiayajin.setPopupWindowFullScreen(true);
                shangjiayajin.showPopupWindow();
                shangjiayajin.setDismissWhenTouchOuside(true);
                break;
            //选择地址
            case R.id.ll_layout_addres:
              //  startActivity(new Intent(getApplicationContext(),ShoppingHarvestAddress.class));
                Intent intent = new Intent(this,ShoppingHarvestAddress.class);
                AnimUtils.startIntent(intent,v, (Activity)this,R.id.ll_layout_addres);

                break;
            case R.id.imge_add:
                sum =  (cuont+=6) * 50;
                // 设置点击个数
                tv_sums.setText(cuont+"");
                //设置总金额
                tv_shoppingYJ.setText(sum+"");
                break;
            case R.id.img_jian:
                if (cuont > 6) {
                    sum = (cuont-=6) * 50;
                    //这里设置金额
                    tv_shoppingYJ.setText(sum + "");
                    //设置个数
                    tv_sums.setText(cuont+"");
                } else {
                    ToastUtil.showShortToast(getApplicationContext(),"默认6个伞座，300元！");
                }
                break;
            case R.id.img_yusan_add:
                yusanCuont+=10;
                //设置申请的雨伞个数
                tv_yusans.setText(yusanCuont+"");
                break;
            case R.id.img_yusan_jian:
                if (yusanCuont > 18) {
                    yusanCuont -= 10;
                    tv_yusans.setText(yusanCuont + "");
                } else {
                    ToastUtil.showShortToast(getApplicationContext(),"默认就是18把雨伞哦！");
                }
                break;
        }
    }
    /*
    * 获取伞座跟伞商家的哦
    * */
    @Override
    public void onShowRelurt(Object object) {
        SanZuoSanBean sanzuosan = (SanZuoSanBean) object;
        int status = sanzuosan.getStatus();
        if (status == 1) {
            SanZuoSanBean.DataBean sz = sanzuosan.getData();
            //获取伞座的个数
            int sanzuo =  sz.getSeat();
            //设置伞座到textview
            tv_sanzuo.setText(sanzuo+"");
            //获取伞的数量
            String umbrella= sz.getUmbrella();
            //设置雨伞到textview
            tv_yusan.setText(umbrella);
            //获取收货地址联系人

            if (sz.getAddress() !=null) {
                tv_addressName.setText(sz.getAddress().getName());
            } else {
                tv_addressName.setText("张三");
            }

            //获取收货地址 电话号码
            if (sz.getAddress() != null) {
                tv_phone.setText(sz.getAddress().getPhone());
            }
            if (sz.getAddress() != null) {
                //获取详细地址
                tv_address.setText(sz.getAddress().getRegion()+sz.getAddress().getAddress());
            }
        }
    }
}
