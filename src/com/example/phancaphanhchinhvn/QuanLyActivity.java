package com.example.phancaphanhchinhvn;

import java.util.HashMap;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuanLyActivity extends Activity implements OnClickListener{

	//Button buttonXemTinh;
	private ImageButton buttonQLTinh,buttonQLHuyen,buttonQLXa,buttonQLNguoiDung;
	
	Button buttonDoiMatKhau,buttonDangXuat;
	//private Button btnXemDS;
	//private Button btnDangNhap;
	SessionManager sessionManager;
	TextView tvHoTen,tvSdt,tvEmail;
	private View btnThoat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quan_ly);
		
		sessionManager=new SessionManager(this);
		sessionManager.checkLogin();
		
		
		tvHoTen=(TextView)findViewById(R.id.textViewHoTen);
		tvSdt=(TextView)findViewById(R.id.textViewSdt);
		tvEmail=(TextView)findViewById(R.id.textViewEmail);
		
		buttonQLTinh=(ImageButton)findViewById(R.id.buttonQLTinh);
		buttonQLHuyen=(ImageButton)findViewById(R.id.buttonQLHuyen);
		buttonQLXa=(ImageButton)findViewById(R.id.buttonQLXa);
		buttonQLNguoiDung=(ImageButton)findViewById(R.id.buttonQLNguoiDung);
		
		buttonDangXuat=(Button)findViewById(R.id.buttonDangXuat);
		//buttonThoat=(Button)findViewById(R.id.buttonThoat);
		buttonDoiMatKhau=(Button)findViewById(R.id.buttonDoiMatKhau);
		
		buttonQLTinh.setOnClickListener(this);
		buttonQLHuyen.setOnClickListener(this);
		buttonQLXa.setOnClickListener(this);
		buttonQLNguoiDung.setOnClickListener(this);
		//buttonThoat.setOnClickListener(this);
		buttonDangXuat.setOnClickListener(this);
		buttonDoiMatKhau.setOnClickListener(this);
		
		HashMap<String,String> user=sessionManager.getUserDetail();
		String mhoten=user.get(sessionManager.NAME);
		String memail=user.get(sessionManager.EMAIL);
		String mphone=user.get(sessionManager.PHONE);
		
		tvHoTen.setText("Họ tên: "+mhoten);
		tvSdt.setText("Số điện thoại: "+ mphone);
		tvEmail.setText("Email: "+ memail);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quan_ly, menu);
		return true;
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==buttonQLTinh){
			Intent qltinh=new Intent(this,QLCapTinhActivity.class);
			startActivity(qltinh);
		}
		else if(arg0==buttonDoiMatKhau){
			Intent qlhuyen=new Intent(this,DoiMatKhauActivity.class);
			startActivity(qlhuyen);
		}
		else if(arg0==buttonQLHuyen){
			Intent qlhuyen=new Intent(this,QLCapHuyenActivity.class);
			startActivity(qlhuyen);
		}
		else if(arg0==buttonQLXa){
			Intent qlxa=new Intent(this,QLCapXaActivity.class);
			startActivity(qlxa);
		}
		else if(arg0==buttonQLNguoiDung){
			Intent qlnd=new Intent(this,QLNguoiDungActivity.class);
			startActivity(qlnd);
		}
		else if(arg0==buttonDangXuat){
			AlertDialog.Builder builder= new AlertDialog.Builder(QuanLyActivity.this,
					AlertDialog.THEME_HOLO_LIGHT);
			builder.setMessage("Are you sure want to log out?");
			builder.setTitle("Question?");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					sessionManager.logoutQuanLy();
				}
			});
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			AlertDialog dialog=builder.create();
			dialog.show();
			
		}
	}

}
