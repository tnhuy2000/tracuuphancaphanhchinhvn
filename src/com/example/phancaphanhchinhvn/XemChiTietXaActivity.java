package com.example.phancaphanhchinhvn;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class XemChiTietXaActivity extends Activity implements OnClickListener{
	
	Button buttonQuayVeTrangChu;
	TextView tvMaXa;
	TextView tvTenXa;
	TextView tvLoai;
	TextView tvCapHuyen_id;
	TextView tvTenHuyen;
	TextView tvCapTinh_id;
	TextView tvTenTinh;
	//Button btnDat;
	Xa xaChiTiet;
	Intent intent;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xem_chi_tiet_xa);
		
		buttonQuayVeTrangChu=(Button)findViewById(R.id.buttonQuayVeTrangChu);
		buttonQuayVeTrangChu.setOnClickListener(this);
		
		tvMaXa=(TextView)findViewById(R.id.textViewMaXa);
		tvTenXa=(TextView)findViewById(R.id.textViewTenXa);
		tvLoai=(TextView)findViewById(R.id.textViewLoai_xa);
		
		tvCapHuyen_id=(TextView)findViewById(R.id.textViewCapHuyen_id_xa);
		tvTenHuyen=(TextView)findViewById(R.id.textViewTenHuyen_xa);
		
		tvCapTinh_id=(TextView)findViewById(R.id.textViewCapTinh_id_xa);
		tvTenTinh=(TextView)findViewById(R.id.textViewTenTinh_xa);
		
		
		intent=getIntent();
		Bundle b=intent.getBundleExtra("DATA");
		xaChiTiet=(Xa)b.getSerializable("capphuongxa");
		
		
		tvMaXa.setText(xaChiTiet.getIdXa());
		tvTenXa.setText(xaChiTiet.getTenXa());
		tvLoai.setText(xaChiTiet.getLoai());
		
		tvCapHuyen_id.setText(xaChiTiet.getCaphuyen_id());
		tvTenHuyen.setText(xaChiTiet.getTenhuyen());
		
		tvCapTinh_id.setText(xaChiTiet.getCaptinh_id());
		tvTenTinh.setText(xaChiTiet.getTentinh());
		
		//tvThanhTien.setText(tinhChiTiet.getGia()+"");
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xem_chi_tiet_xa, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(arg0==buttonQuayVeTrangChu)
		{
			Intent main=new Intent(this,TrangChuActivity.class);
			startActivity(main);
		}
		
	}

}
