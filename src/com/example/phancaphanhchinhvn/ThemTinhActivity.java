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

public class ThemTinhActivity extends Activity{

	Button buttonThemMoi,buttonXoaTrang,buttonHuy;
	
	EditText editTextThemMaTinh,editTextThemTenTinh,
	editTextThemVungTinh,editTextThemDienTichTinh,editTextThemDanSoTinh,editTextThemMoTaTinh;
	Context context;
	Intent intent;
	
	String loaitinh[]={"Thành phố Trung ương","Tỉnh"};
	String vung[]={"Đồng bằng sông Hồng",
					"Tây Bắc Bộ",
					"Bắc Trung Bộ",
					"Đồng bằng sông Cửu Long",
					"Đông Nam Bộ",
					"Đông Bắc Bộ",
					"Duyên hải Nam Trung Bộ"};
	
    Spinner spinnerThemLoaiTinh, spinnerThemVung;
    ArrayAdapter<String> adapterThemLoaiTinh;
    ArrayAdapter<String> adapterThemVung;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_tinh);
		//ket noi
		buttonXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
		editTextThemMaTinh=(EditText)findViewById(R.id.editTextThemMaTinh);
		editTextThemTenTinh=(EditText)findViewById(R.id.editTextThemTenTinh);
		spinnerThemLoaiTinh=(Spinner)findViewById(R.id.spinnerThemLoaiTinh);
		spinnerThemVung=(Spinner)findViewById(R.id.spinnerThemVung);
		editTextThemDienTichTinh=(EditText)findViewById(R.id.editTextThemDienTichTinh);
		editTextThemDanSoTinh=(EditText)findViewById(R.id.editTextThemDanSoTinh);
		editTextThemMoTaTinh=(EditText)findViewById(R.id.editTextThemMoTaTinh);
		
		buttonHuy=(Button)findViewById(R.id.buttonHuy);
		buttonXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
		buttonThemMoi=(Button)findViewById(R.id.buttonTinhThemMoi);
		
		//loại cấp
		ArrayAdapter<String>adapterThemLoaiTinh=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,loaitinh);
		adapterThemLoaiTinh.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerThemLoaiTinh.setAdapter(adapterThemLoaiTinh);
		
		//vùng
		ArrayAdapter<String>adapterThemVung=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,vung);
		adapterThemVung.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerThemVung.setAdapter(adapterThemVung);
		
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
				((ThemTinhActivity) arg0.getContext()).finish();
				Intent themtinh=new Intent(ThemTinhActivity.this,QLCapTinhActivity.class);
				startActivity(themtinh);
			}
		});
		
		//them mới
		buttonThemMoi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String matinh=editTextThemMaTinh.getText().toString()+"";
				
				if(matinh.trim().equals(""))
					Toast.makeText(getApplicationContext(),
							"Vui lòng nhập mã tỉnh", Toast.LENGTH_LONG)
							.show();
		        else if(editTextThemTenTinh.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập tên tỉnh", Toast.LENGTH_LONG)
							.show();
		        else if(spinnerThemLoaiTinh.getSelectedItem()==null)
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng chọn cấp", Toast.LENGTH_LONG)
							.show();
		        else if(spinnerThemVung.getSelectedItem()==null)
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng chọn vùng", Toast.LENGTH_LONG)
							.show();
		        else if(editTextThemDienTichTinh.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập diện tích tỉnh", Toast.LENGTH_LONG)
							.show();
		        else if(editTextThemDanSoTinh.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập dân số tỉnh", Toast.LENGTH_LONG)
							.show();
		        else
		        {
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "themtinh"));
				
				params.add(new BasicNameValuePair("id", editTextThemMaTinh.getText()+""));
				params.add(new BasicNameValuePair("tentinh", editTextThemTenTinh.getText()+""));
				params.add(new BasicNameValuePair("loai", spinnerThemLoaiTinh.getSelectedItem().toString()+""));
				params.add(new BasicNameValuePair("vung", spinnerThemVung.getSelectedItem().toString()+""));
				params.add(new BasicNameValuePair("dientich", editTextThemDienTichTinh.getText()+""));
				params.add(new BasicNameValuePair("danso", editTextThemDanSoTinh.getText()+""));
				params.add(new BasicNameValuePair("mota", editTextThemMoTaTinh.getText()+""));
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Thêm tỉnh thành công", Toast.LENGTH_LONG)
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.them_tinh, menu);
		return true;
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		editTextThemMaTinh.setText(null);
		editTextThemTenTinh.setText(null);
		editTextThemDienTichTinh.setText(null);
		editTextThemDanSoTinh.setText(null);
		editTextThemMoTaTinh.setText(null);
		editTextThemMaTinh.setFocusable(true);;
	}

}
