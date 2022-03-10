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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SuaNguoiDungActivity extends Activity {
	Button buttonCapNhat,buttonHuy;
	NguoiDung nd;
    Intent intent;
	EditText editTextHoTen,editTextEmail,editTextSdt;
	String loainguoidung[]={"Admin","Người dùng"};
	
	Spinner spinnerSuaLoaiNd;
    ArrayAdapter<String> adapterSuaLoaiNd;
   
    
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sua_nguoi_dung);
		
		
		//ket noi
		spinnerSuaLoaiNd=(Spinner)findViewById(R.id.spinnerSuaLoaiNd);
		editTextHoTen=(EditText)findViewById(R.id.editTextHoTen);
		editTextEmail=(EditText)findViewById(R.id.editTextEmail);
		editTextSdt=(EditText)findViewById(R.id.editTextSdt);
		
		
		final ArrayAdapter<String>adapterSuaLoaiNd=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,loainguoidung);
		adapterSuaLoaiNd.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerSuaLoaiNd.setAdapter(adapterSuaLoaiNd);
		
		buttonHuy=(Button)findViewById(R.id.buttonHuy);
		
		
        intent=getIntent();
		Bundle b=intent.getBundleExtra("DATA");
		nd=(NguoiDung)b.getSerializable("nguoidung");
		
		editTextHoTen.setEnabled(false);
		editTextEmail.setEnabled(false);
		editTextSdt.setEnabled(false);
		
		editTextHoTen.setText(nd.getHoten()+"");
		editTextEmail.setText(nd.getEmail()+"");
		editTextSdt.setText(nd.getSodienthoai()+"");
		String loai=nd.getLoai();
		 
		 //System.out.println(loai);
		if("Admin".equals(loai)){
			spinnerSuaLoaiNd.setSelection(adapterSuaLoaiNd.getPosition("Admin"));
        }
        else
        {  
    	   spinnerSuaLoaiNd.setSelection(adapterSuaLoaiNd.getPosition("Người dùng"));
        }
       
     
		buttonCapNhat=(Button)findViewById(R.id.buttonNDCapNhat);
		
		buttonHuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//((ThemTinhActivity) arg0.getContext()).finish();
				Intent themtinh=new Intent(SuaNguoiDungActivity.this,QLNguoiDungActivity.class);
				startActivity(themtinh);
			}
		});
		
		//them mới
		buttonCapNhat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
		       
		        
		        else
		        {
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "capnhatnguoidung"));
				params.add(new BasicNameValuePair("id", nd.getId() +""));
				//params.add(new BasicNameValuePair("hoten", editTextHoTen.getText()+""));
				//params.add(new BasicNameValuePair("sodienthoai", editTextSdt.getText()+""));
				//params.add(new BasicNameValuePair("email", editTextEmail.getText()+""));	
				params.add(new BasicNameValuePair("loai", spinnerSuaLoaiNd.getSelectedItem().toString()+""));
				
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Cập nhật người dùng thành công", Toast.LENGTH_LONG)
									.show();
							
		                   
							//arrayAdapterTinh.notifyDataSetChanged();
							
							nd.setId(nd.getId());
							//nd.setHoten(editTextHoTen.getText().toString());
							//nd.setSodienthoai(editTextSdt.getText().toString());
							//nd.setEmail(editTextEmail.getText().toString());
							nd.setLoai(spinnerSuaLoaiNd.getSelectedItem().toString());
							
							((SuaNguoiDungActivity) arg0.getContext()).finish();
							Intent themtinh=new Intent(SuaNguoiDungActivity.this,QLNguoiDungActivity.class);
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
	boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    } 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sua_nguoi_dung, menu);
		return true;
	}

}
