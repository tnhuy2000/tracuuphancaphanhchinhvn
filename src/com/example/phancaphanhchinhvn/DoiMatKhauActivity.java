package com.example.phancaphanhchinhvn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DoiMatKhauActivity extends Activity {
	Button buttonCapNhat;
	NguoiDung nd;
    Intent intent;
	EditText editTextSuaMatKhau,editTextXNMatKhau;
	SessionManager sessionManager;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doi_mat_khau);
		
		sessionManager=new SessionManager(this);
		sessionManager.checkLogin();
		//ket noi
		
		
		//editTextEmail=(EditText)findViewById(R.id.editTextEmail);
		
		editTextSuaMatKhau=(EditText)findViewById(R.id.editTextDoiMatKhauMoi);
		editTextXNMatKhau=(EditText)findViewById(R.id.editTextDoiXNMatKhauMoi);
		
		
		
		
		HashMap<String,String> user=sessionManager.getUserDetail();
		//String mhoten=user.get(sessionManager.NAME);
		final String memail=user.get(sessionManager.EMAIL);
		//String mphone=user.get(sessionManager.PHONE);
		
     
		buttonCapNhat=(Button)findViewById(R.id.buttonDoiMatKhauMoi);
		
		//them mới
		buttonCapNhat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
		        if(editTextSuaMatKhau.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập mật khẩu mới", Toast.LENGTH_LONG)
							.show();
		        else if(editTextXNMatKhau.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng xác nhận mật khẩu", Toast.LENGTH_LONG)
							.show();
		        else if(editTextSuaMatKhau.getText().toString().trim().equals(editTextXNMatKhau.getText().toString().trim())==false)
		        	Toast.makeText(getApplicationContext(),
							"Mật khẩu không khớp", Toast.LENGTH_LONG)
							.show();
		        else
		        {
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "doimatkhau"));
				
				params.add(new BasicNameValuePair("email", memail.toString()+""));	
				params.add(new BasicNameValuePair("matkhau", editTextSuaMatKhau.getText()+""));
				
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Đổi mật khẩu thành công", Toast.LENGTH_LONG)
									.show();
							
		                   
							//arrayAdapterTinh.notifyDataSetChanged();
							
							
							
							((DoiMatKhauActivity) arg0.getContext()).finish();
							Intent themtinh=new Intent(DoiMatKhauActivity.this,QuanLyActivity.class);
							startActivity(themtinh);
		                    
						}
						
					} else {

					}
				

				} catch (JSONException e) {
					e.printStackTrace();
				}
		        }
			}
		});
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doi_mat_khau, menu);
		return true;
	}

}
