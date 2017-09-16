package com.example.administrator.mysharedumbrella01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.gyf.barlibrary.ImmersionBar;

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
public class AddressSelectorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_city1;
    private City city;
    private ArrayList<City> toCitys;
    private TextView tv_mingxi;
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
        initView();
    }
    /*
    * 初始化数据
    * */
    private void initView() {
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
            case R.id.tv_city1:
                Intent in = new Intent(this, CitySelect1Activity.class);
                in.putExtra("city", city);
                startActivityForResult(in, 1);
                break;
            //点击保存用户 选取的地址
            case R.id.tv_mingxi:

                break;
        }
    }

    /*
    * 这里是用户选择完毕城市后 返回的结果
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 8){
            if(requestCode == 1){
                city = data.getParcelableExtra("city");
                tv_city1.setText(city.getProvince()+city.getCity()+city.getDistrict());

            }else if(requestCode == 2){
                toCitys = data.getParcelableArrayListExtra("toCitys");
                StringBuffer ab = new StringBuffer();
                for (int i = 0; i < toCitys.size(); i++) {
                    if(i==toCitys.size()-1){//如果是最后一个城市就不需要逗号
                        ab.append(toCitys.get(i).getCity());
                    }else{
                        StringBuffer a = ab.append(toCitys.get(i).getCity()+"， ");//如果不是最后一个城市就需要逗号
                        tv_city1.setText(a);
                    }
                }
            }
        }
    }
}
