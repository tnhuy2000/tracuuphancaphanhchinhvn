package com.example.phancaphanhchinhvn;

import java.util.ArrayList;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class DangNhapActivity extends Activity implements OnClickListener{

	
	Button buttonDangNhap,buttonThoat,buttonQuenMKND,buttonDangKyND;
	
	ArrayList<NguoiDung> arrayNguoiDung;
	
	Context context;
	
	SessionManager sessionManager;
	EditText etEmail, etMatKhau;
	// URL to get JSON Array
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dang_nhap);
		
		sessionManager=new SessionManager(this);
		//ket nối
		//editTinhTimKiem=(EditText)findViewById(R.id.editTextTinhTimKiem);
		buttonDangNhap=(Button)findViewById(R.id.buttonDangNhap);
		buttonQuenMKND=(Button)findViewById(R.id.buttonQuenMKND);
		buttonDangKyND=(Button)findViewById(R.id.buttonDangKyND);
		
		arrayNguoiDung = new ArrayList<NguoiDung>();
		this.context=this;
		etEmail=(EditText)findViewById(R.id.editTextEmailDN);
		etMatKhau=(EditText)findViewById(R.id.editTextMatKhauDN);
		
		buttonDangNhap.setOnClickListener(this);
		buttonQuenMKND.setOnClickListener(this);
		buttonDangKyND.setOnClickListener(this);
		//buttonThoat.setOnClickListener(this);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dang_nhap, menu);
		return true;
	}
	
	
	
	boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    } 

	@SuppressLint("NewApi") @Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==buttonDangNhap){
			if((etEmail.getText().toString().trim().equals("")))
	        	Toast.makeText(getApplicationContext(),
						"Vui lòng nhập email", Toast.LENGTH_LONG)
						.show();
	        else if(isEmailValid(etEmail.getText().toString())==false){
	        	Toast.makeText(getApplicationContext(),
						"Email không đúng định dạng", Toast.LENGTH_LONG)
						.show();
	        	etEmail.setFocusable(true);
	        }
	        else if(etMatKhau.getText().toString().trim().equals(""))
	        	Toast.makeText(getApplicationContext(),
						"Vui lòng nhập mật khẩu", Toast.LENGTH_LONG)
						.show();
	        
	        else
	        {
	        	new JSONParserAsyncTaskDangNhap().execute();
	        }
		}
		if(arg0==buttonQuenMKND){
			Intent quen=new Intent(this,QuenMatKhauActivity.class);
			startActivity(quen);
		}
		if(arg0==buttonDangKyND){
			
			Intent quen=new Intent(this,DangKyActivity.class);
			startActivity(quen);
		}
		
		
		
	}
	private class JSONParserAsyncTaskDangNhap extends
	AsyncTask<String, String, JSONObject> {
	private ProgressDialog pDialog;


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	
		/*pDialog = new ProgressDialog(XemSach_GridView.this);
		pDialog.setMessage("Getting Data ...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();*/
	
	}
	
	@Override
	protected JSONObject doInBackground(String... args) {
		
		JSONParser jParser = new JSONParser();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "dangnhap"));
		params.add(new BasicNameValuePair("email", etEmail.getText().toString()+""));
		params.add(new BasicNameValuePair("matkhau", etMatKhau.getText().toString()+""));
		
		// Getting JSON from URL
		JSONObject json = jParser.getJSONFromUrl(url, params);
		return json;
        
	}
	
	@Override
	protected void onPostExecute(JSONObject json) {
		//pDialog.dismiss();
		try {
			if (json.getString("success") != null) {
				// loginErrorMsg.setText("");
				// String res = json.getString(KEY_SUCCESS);
				// if(Integer.parseInt(res) == 1){
				
				if (json.getString("success").equals("1")) {
					int soLuong = json.getInt("soluong");
	
					JSONObject json_nguoidung;
					NguoiDung nguoidung;
					arrayNguoiDung.clear();
					for (int i = 0; i < soLuong; i++) {
	
						json_nguoidung = json.getJSONObject("nguoidung" + i);
	
						// Cursor
						// cursor=database.query("sach",null,null,null,null,null,null);
	
						nguoidung = new NguoiDung(json_nguoidung.getInt("id"),
								json_nguoidung.getString("hoten"),
								json_nguoidung.getString("sodienthoai"),
								json_nguoidung.getString("email"),
								json_nguoidung.getString("loai")
								);
	
						arrayNguoiDung.add(nguoidung);
						String loai=json_nguoidung.getString("loai").trim();
						String name=json_nguoidung.getString("hoten").trim();
						String email=json_nguoidung.getString("email").trim();
						String phone=json_nguoidung.getString("sodienthoai").trim(); 
						//int loai=json_nguoidung.getInt("loai");
						
						if(json_nguoidung.getString("loai").trim().equals("Người dùng"))
						{
							sessionManager.createSession(name, email, phone);
							
							
							
							//Bundle data=new Bundle();
							Intent quanly=new Intent(DangNhapActivity.this ,TrangChuActivity.class);
							quanly.putExtra("name", name);
							quanly.putExtra("email", email);
							quanly.putExtra("phone", phone);
							startActivity(quanly);
							finish();
						}
						else if(json_nguoidung.getString("loai").trim().equals("Admin"))
						{
							sessionManager.createSession(name, email, phone);
							
							Intent quanly=new Intent(DangNhapActivity.this ,QuanLyActivity.class);
							quanly.putExtra("name", name);
							quanly.putExtra("email", email);
							quanly.putExtra("phone", phone);
							startActivity(quanly);
							finish();
						}
						
	
					}
					
					Toast.makeText(getApplicationContext(),
							"Đăng nhập thành công", Toast.LENGTH_LONG)
							.show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"Email hoặc mật khẩu không đúng", Toast.LENGTH_LONG)
							.show();
				}
				
				
			} else {
				Toast.makeText(getApplicationContext(),
						"Có lỗi xảy ra", Toast.LENGTH_LONG)
						.show();
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}

}



}
