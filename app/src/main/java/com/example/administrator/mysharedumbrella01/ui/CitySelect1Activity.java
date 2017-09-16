package com.example.administrator.mysharedumbrella01.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mysharedumbrella01.R;
import com.example.administrator.mysharedumbrella01.utils.CityUtils;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;


public class CitySelect1Activity extends Activity implements OnClickListener {

	private ImageView btn_backs;
	private TextView	btn_right;
	private ListView lv_city;
	private ArrayList<MyRegion> regions;
	private CityAdapter adapter;
	private static int PROVINCE = 0x00;
	private static int CITY = 0x01;
	private static int DISTRICT = 0x02;
	private CityUtils util;
	private City city;
	int last, current;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_city2);//三级联动选择页面
		//沉浸式
		ImmersionBar.with(this)
				.statusBarColor(R.color.zhutiyanse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
				// .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
				.fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
				.init();
		viewInit();

	}

	/*
	 * 初始化
	 */
	private void viewInit() {

		city = new City();
		Intent in = getIntent();
		city = in.getParcelableExtra("city");


		if(city==null){
			city = new City();
			city.setProvince("");
			city.setCity("");
			city.setDistrict("");
		}
		else{
			city = new City();
			city.setProvince("");
			city.setCity("");
			city.setDistrict("");

		}
		btn_backs = (ImageView) findViewById(R.id.btn_backs);
		btn_right = (TextView) findViewById(R.id.tv_mingxi);
		//btn_right.setText("确认");

		findViewById(R.id.scrollview).setVisibility(View.GONE);

		util = new CityUtils(getApplicationContext(), hand);
		util.initProvince();
		lv_city = (ListView) findViewById(R.id.lv_city);

		regions = new ArrayList<MyRegion>();
		adapter = new CityAdapter(this);
		lv_city.setAdapter(adapter);

	}

	protected void onStart() {
		super.onStart();
		lv_city.setOnItemClickListener(onItemClickListener);
		btn_backs.setOnClickListener(this);
		btn_right.setOnClickListener(this);
	};



	Handler hand = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
				case 1:
					System.out.println("省份列表what======" + msg.what);
					regions = (ArrayList<MyRegion>) msg.obj;
					adapter.clear();
					adapter.addAll(regions);
					adapter.update();
					break;

				case 2:
					System.out.println("城市列表what======" + msg.what);
					regions = (ArrayList<MyRegion>) msg.obj;
					adapter.clear();
					adapter.addAll(regions);
					adapter.update();
					break;

				case 3:
					System.out.println("区/县列表what======" + msg.what);
					regions = (ArrayList<MyRegion>) msg.obj;
					adapter.clear();
					adapter.addAll(regions);
					adapter.update();
					break;
			}
		};
	};

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {

			if (current == PROVINCE) {
				String newProvince = regions.get(arg2).getName();
				if (!newProvince.equals(city.getProvince())) {
					city.setProvince(newProvince);

					city.setRegionId(regions.get(arg2).getId());
					city.setProvinceCode(regions.get(arg2).getId());
					city.setCityCode("");
					city.setDistrictCode("");

				}

				current = 1;
				//点击省份列表中的省份就初始化城市列表
				util.initCities(city.getProvinceCode());
			} else if (current == CITY) {
				String newCity = regions.get(arg2).getName();
				if (!newCity.equals(city.getCity())) {
					city.setCity(newCity);
					city.setRegionId(regions.get(arg2).getId());
					city.setCityCode(regions.get(arg2).getId());
					city.setDistrictCode("");
				}
				util.initDistricts(city.getCityCode());
				current = 2;

			} else if (current == DISTRICT) {
				current = 2;
				city.setDistrictCode(regions.get(arg2).getId());
				city.setRegionId(regions.get(arg2).getId());
				city.setDistrict(regions.get(arg2).getName());
			}

			last = current;
		}
	};


	class CityAdapter extends ArrayAdapter<MyRegion> {

		LayoutInflater inflater;

		public CityAdapter(Context con) {
			super(con, 0);
			inflater = LayoutInflater.from(CitySelect1Activity.this);
		}

		@Override
		public View getView(int arg0, View v, ViewGroup arg2) {
			v = inflater.inflate(R.layout.city_item, null);
			TextView tv_city = (TextView) v.findViewById(R.id.tv_city);
			tv_city.setText(getItem(arg0).getName());
			return v;
		}

		public void update() {
			this.notifyDataSetChanged();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_backs:
				finish();
				break;
			case R.id.tv_mingxi:
				Intent in = new Intent();
				in.putExtra("city", city);
				setResult(8,in);
				finish();
				break;
		}
	}

}
