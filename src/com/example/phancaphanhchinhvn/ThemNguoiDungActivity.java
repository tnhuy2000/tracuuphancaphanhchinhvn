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

public class ThemNguoiDungActivity extends Activity{

	Button buttonThemMoi,buttonXoaTrang,buttonHuy;
	
	EditText editTextThemHoTen,editTextThemSdt,editTextThemEmail,editTextMatKhau,editTextXNMatKhau;
	
	Context context;
	Intent intent;
	
	
   
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_nguoi_dung);
		//ket noi
		buttonXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
		editTextThemHoTen=(EditText)findViewById(R.id.editTextThemHoTenND);
		editTextThemSdt=(EditText)findViewById(R.id.editTextThemSdtND);
		editTextThemEmail=(EditText)findViewById(R.id.editTextThemEmailND);
		editTextMatKhau=(EditText)findViewById(R.id.editTextMatKhauND);
		editTextXNMatKhau=(EditText)findViewById(R.id.editTextXNMatKhauND);
		
		buttonHuy=(Button)findViewById(R.id.buttonHuy);
		buttonXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
		buttonThemMoi=(Button)findViewById(R.id.buttonNDThemMoi);
		
		
		
		buttonXoaTrang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				xoaTrang();
			}
		});
		buttonHuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((ThemNguoiDungActivity) arg0.getContext()).finish();
				Intent themtinh=new Intent(ThemNguoiDungActivity.this,QLNguoiDungActivity.class);
				startActivity(themtinh);
			}
		});
		
		//them mới
		buttonThemMoi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String matkhau= editTextMatKhau.getText().toString();
				String xacnhanmatkhau= editTextXNMatKhau.getText().toString();
				//String sodienthoai=editTextThemSdt.getText().toString()+"";
		        if(editTextThemHoTen.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập họ tên", Toast.LENGTH_LONG)
							.show();
		        else if(editTextThemSdt.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập số điện thoại", Toast.LENGTH_LONG)
							.show();
		        else if((editTextThemEmail.getText().toString().trim().equals("")))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập email", Toast.LENGTH_LONG)
							.show();
		        else if(isEmailValid(editTextThemEmail.getText().toString())==false){
		        	Toast.makeText(getApplicationContext(),
							"Email không đúng định dạng", Toast.LENGTH_LONG)
							.show();
		        	editTextThemEmail.setFocusable(true);
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
				
				params.add(new BasicNameValuePair("hoten", editTextThemHoTen.getText()+""));
				params.add(new BasicNameValuePair("sodienthoai", editTextThemSdt.getText()+""));
				
				params.add(new BasicNameValuePair("email", editTextThemEmail.getText()+""));
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
									"Thêm người dùng thành công", Toast.LENGTH_LONG)
									.show();
							
		                    xoaTrang();
		                    
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
		getMenuInflater().inflate(R.menu.them_nguoi_dung, menu);
		return true;
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		editTextThemHoTen.setText(null);
		editTextThemSdt.setText(null);
		editTextThemEmail.setText(null);
		editTextMatKhau.setText(null);
		editTextXNMatKhau.setText(null);
		editTextThemHoTen.setFocusable(true);
	}

}
