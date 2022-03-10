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

public class TrangChuActivity extends Activity implements OnClickListener {

	//Button buttonXemTinh;
	TextView tvHoTen;
	private ImageButton btnTraCuuTheoTen_Ma;
	private ImageButton btnXemDS;
	Button btnDangXuat,btnDoiMatKhauND;
	private View btnThoat;
	private ImageButton btnGioiThieu;
	SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        
        sessionManager=new SessionManager(this);
		sessionManager.checkLogin();
		
		tvHoTen=(TextView)findViewById(R.id.textViewHoTenTCND);
		
        btnTraCuuTheoTen_Ma=(ImageButton)findViewById(R.id.btnTraCuuTheoTen_Ma);
        btnXemDS=(ImageButton)findViewById(R.id.btnXemDS);
        btnDangXuat=(Button)findViewById(R.id.btnDangXuat);
        btnGioiThieu=(ImageButton)findViewById(R.id.btnGioiThieu);
        btnThoat=(Button)findViewById(R.id.btnThoat);
        btnDoiMatKhauND=(Button)findViewById(R.id.btnDoiMatKhauND);
        
        btnDangXuat.setOnClickListener(this);
        btnXemDS.setOnClickListener(this);
        btnTraCuuTheoTen_Ma.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        btnDoiMatKhauND.setOnClickListener(this);
        btnGioiThieu.setOnClickListener(this);
        
        HashMap<String,String> user=sessionManager.getUserDetail();
		String mhoten=user.get(sessionManager.NAME);
		String memail=user.get(sessionManager.EMAIL);
		String mphone=user.get(sessionManager.PHONE);
		
		tvHoTen.setText("Xin chào "+mhoten);
		//tvSdt.setText("Số điện thoại: "+ mphone);
		//tvEmail.setText("Email: "+ memail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trang_chu, menu);
        return true;
    }


	@SuppressLint("NewApi") @Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==btnDangXuat)
		{
			AlertDialog.Builder builder= new AlertDialog.Builder(TrangChuActivity.this,
					AlertDialog.THEME_HOLO_LIGHT);
			builder.setMessage("Are you sure want to log out?");
			builder.setTitle("Question?");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					sessionManager.logoutNguoiDung();
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
		else if(arg0==btnTraCuuTheoTen_Ma)
		{
			Intent tracuutheoten_ma=new Intent(this,TraCuuTheoTenMaActivity.class);
			startActivity(tracuutheoten_ma);
		}
		else if(arg0==btnXemDS)
		{
			Intent xemds=new Intent(this,TraCuuActivity.class);
			startActivity(xemds);
		}
		else if(arg0==btnGioiThieu)
		{
			Intent xemds=new Intent(this,GioiThieuActivity.class);
			startActivity(xemds);
		}
		else if(arg0==btnDoiMatKhauND)
		{
			Intent xemds=new Intent(this,DoiMatKhauNDActivity.class);
			startActivity(xemds);
		}
		else if(arg0==btnThoat){
			AlertDialog.Builder builder= new AlertDialog.Builder(TrangChuActivity.this,
					AlertDialog.THEME_HOLO_LIGHT);
			builder.setMessage("Are you sure you want to exit?");
			builder.setTitle("Question?");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					TrangChuActivity.this.finish();
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
