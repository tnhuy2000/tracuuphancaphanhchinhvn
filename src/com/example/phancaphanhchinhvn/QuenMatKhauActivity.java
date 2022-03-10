package com.example.phancaphanhchinhvn;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class QuenMatKhauActivity extends Activity {
	Button buttonQuenMatKhau,buttonHuy;
	NguoiDung nd;
    Intent intent;
	EditText editTextEmail;
	ArrayList<NguoiDung> arrayNguoiDung;
	Context context;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quen_mat_khau);
		
		
		//ket noi
		
		arrayNguoiDung = new ArrayList<NguoiDung>();
		this.context=this;
		editTextEmail=(EditText)findViewById(R.id.editTextQuenEmail);
		
		buttonQuenMatKhau=(Button)findViewById(R.id.buttonQuenMatKhau);
		
		
		
		//them mới
		
		buttonQuenMatKhau.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
		        if((editTextEmail.getText().toString().trim().equals("")))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập email", Toast.LENGTH_LONG)
							.show();
		        else if(isEmailValid(editTextEmail.getText().toString())==false){
		        	Toast.makeText(getApplicationContext(),
							"Email không đúng định dạng", Toast.LENGTH_LONG)
							.show();
		        	editTextEmail.setFocusable(true);
		        }
		        		
		        else
		        {
		        	new JSONParserAsyncTaskKiemTraTonTaiEmail().execute();
		        }
			}

			
		});
	
	}
	private class JSONParserAsyncTaskKiemTraTonTaiEmail extends
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
		params.add(new BasicNameValuePair("tag", "kiemtratontaiEmail"));
		params.add(new BasicNameValuePair("email", editTextEmail.getText().toString()+""));
		
		
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
					}
					
					JSONParser jParser = new JSONParser();
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("tag", "quenmatkhau"));
					params.add(new BasicNameValuePair("email", editTextEmail.getText()+""));	
					
					
					// Getting JSON from URL
					JSONObject json1 = jParser.getJSONFromUrl(url, params);
					try {
						if (json1.getString("success") != null) {
							// loginErrorMsg.setText("");
							// String res = json.getString(KEY_SUCCESS);
							// if(Integer.parseInt(res) == 1){
							if (json1.getString("success").equals("1")) {							
								Toast.makeText(getApplicationContext(),
										"Đổi mật khẩu thành công. Vui lòng kiểm tra email", Toast.LENGTH_LONG)
										.show();
								
								((QuenMatKhauActivity) context).finish();
								
								Intent quen=new Intent(QuenMatKhauActivity.this,DangNhapActivity.class);
								startActivity(quen);
							}
							
						} else {

						}
					

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"Email không tồn tại trong hệ thống", Toast.LENGTH_LONG)
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


	boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    } 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quen_mat_khau, menu);
		return true;
	}

}
