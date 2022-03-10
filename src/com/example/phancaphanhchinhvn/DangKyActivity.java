package com.example.phancaphanhchinhvn;

import java.util.ArrayList;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DangKyActivity extends Activity{

	Button buttonDangKy;
	
	EditText editTextHoTen,editTextSdt,editTextEmail,editTextMatKhau,editTextXNMatKhau;
	
	Context context;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dang_ky);
		//ket noi
		
		editTextHoTen=(EditText)findViewById(R.id.editTextHoTenNDDangKy);
		editTextSdt=(EditText)findViewById(R.id.editTextSdtNDDangKy);
		editTextEmail=(EditText)findViewById(R.id.editTextEmailNDDangKy);
		editTextMatKhau=(EditText)findViewById(R.id.editTextMatKhauNDDangKy);
		editTextXNMatKhau=(EditText)findViewById(R.id.editTextXNMatKhauNDDangKy);
		
		buttonDangKy=(Button)findViewById(R.id.buttonNDDangKy);
		
		
		//them mới
		buttonDangKy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//String matkhau= editTextMatKhau.getText().toString();
				//String xacnhanmatkhau= editTextXNMatKhau.getText().toString();
				//String sodienthoai=editTextThemSdt.getText().toString()+"";
		        if(editTextHoTen.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập họ tên", Toast.LENGTH_LONG)
							.show();
		        else if(editTextSdt.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập số điện thoại", Toast.LENGTH_LONG)
							.show();
		        else if((editTextEmail.getText().toString().trim().equals("")))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập email", Toast.LENGTH_LONG)
							.show();
		        else if(isEmailValid(editTextEmail.getText().toString())==false){
		        	Toast.makeText(getApplicationContext(),
							"Email không đúng định dạng", Toast.LENGTH_LONG)
							.show();
		        	editTextEmail.setFocusable(true);
		        }
		        else if(editTextMatKhau.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập mật khẩu", Toast.LENGTH_LONG)
							.show();
		        else if(editTextXNMatKhau.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng xác nhận mật khẩu", Toast.LENGTH_LONG)
							.show();
		        else if(editTextMatKhau.getText().toString().trim().equals(editTextXNMatKhau.getText().toString().trim())==false)
		        	Toast.makeText(getApplicationContext(),
							"Mật khẩu không khớp", Toast.LENGTH_LONG)
							.show();
		        
		        else
		        {
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "themnguoidung"));
				
				params.add(new BasicNameValuePair("hoten", editTextHoTen.getText()+""));
				params.add(new BasicNameValuePair("sodienthoai", editTextSdt.getText()+""));
				
				params.add(new BasicNameValuePair("email", editTextEmail.getText()+""));
				params.add(new BasicNameValuePair("matkhau", editTextMatKhau.getText()+""));
				
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Đăng ký thành công", Toast.LENGTH_LONG)
									.show();
							
							//((DangKyActivity) arg0.getContext()).finish();
							///Intent themtinh=new Intent(DangKyActivity.this,DangNhapActivity.class);
							//startActivity(themtinh);
							
							((DangKyActivity) context).finish();
							
							Intent quen=new Intent(DangKyActivity.this,DangNhapActivity.class);
							startActivity(quen);
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
	boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    } 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dang_ky, menu);
		return true;
	}


}
