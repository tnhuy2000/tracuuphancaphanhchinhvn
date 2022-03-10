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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ThemHuyenActivity extends Activity{

	Button buttonThemMoi,buttonXoaTrang,buttonHuy;
	
	EditText editTextThemMaHuyen,editTextThemTenHuyen;
	
	Context context;
	Intent intent;
	
	String loaihuyen[]={"Thành phố","Quận","Thị xã","Huyện"};
    Spinner spinnerThemLoaiHuyen, spinnerThemCapTinh_id;
    ArrayAdapter<String> adapterThemLoaiHuyen;
    
  //spinner tỉnh
    private ArrayList<Tinh> arrayTinh;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_huyen);
		//ket noi
		buttonXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
		editTextThemMaHuyen=(EditText)findViewById(R.id.editTextThemMaHuyen);
		editTextThemTenHuyen=(EditText)findViewById(R.id.editTextThemTenHuyen);
		spinnerThemLoaiHuyen=(Spinner)findViewById(R.id.spinnerThemLoaiHuyen);
		spinnerThemCapTinh_id=(Spinner)findViewById(R.id.spinnerThemCapTinh_id);
		 new JSONParserAsyncTaskTinh().execute();
		 
		buttonHuy=(Button)findViewById(R.id.buttonHuy);
		buttonXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
		buttonThemMoi=(Button)findViewById(R.id.buttonHuyenThemMoi);
		
		final ArrayAdapter<String>adapterSuaLoaiHuyen=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,loaihuyen);
		adapterSuaLoaiHuyen.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerThemLoaiHuyen.setAdapter(adapterSuaLoaiHuyen);
		//tỉnh
		arrayTinh = new ArrayList<Tinh>();
		
		
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
				((ThemHuyenActivity) arg0.getContext()).finish();
				Intent themhuyen=new Intent(ThemHuyenActivity.this,QLCapHuyenActivity.class);
				startActivity(themhuyen);
			}
		});
		
		//them mới
		buttonThemMoi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String mahuyen=editTextThemMaHuyen.getText().toString()+"";
				
				if(mahuyen.trim().equals(""))
					Toast.makeText(getApplicationContext(),
							"Vui lòng nhập mã huyện", Toast.LENGTH_LONG)
							.show();
		        else if(editTextThemTenHuyen.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập tên huyện", Toast.LENGTH_LONG)
							.show();
		        else if(spinnerThemLoaiHuyen.getSelectedItem()==null)
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng chọn cấp", Toast.LENGTH_LONG)
							.show();
		        
		        else
		        {
		        
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "themhuyen"));
				
				params.add(new BasicNameValuePair("id", editTextThemMaHuyen.getText().toString()+""));
				params.add(new BasicNameValuePair("tenhuyen", editTextThemTenHuyen.getText().toString()+""));
				params.add(new BasicNameValuePair("loai", spinnerThemLoaiHuyen.getSelectedItem().toString()+""));
				params.add(new BasicNameValuePair("captinh_id",((Tinh)spinnerThemCapTinh_id.getSelectedItem()).getIdTinh().toString()+""));
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Thêm huyện thành công", Toast.LENGTH_LONG)
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
		getMenuInflater().inflate(R.menu.them_huyen, menu);
		return true;
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		editTextThemMaHuyen.setText(null);
		editTextThemTenHuyen.setText(null);
		
	}
	private class JSONParserAsyncTaskTinh extends
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
		params.add(new BasicNameValuePair("tag", "loadtinh"));
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
	
					JSONObject json_tinh;
					Tinh tinh;
					arrayTinh.clear();
					for (int i = 0; i < soLuong; i++) {
	
						json_tinh = json.getJSONObject("captinh" + i);
	
						// Cursor
						// cursor=database.query("sach",null,null,null,null,null,null);
	
						tinh = new Tinh(json_tinh.getString("id"),
								json_tinh.getString("tentinh"),
								json_tinh.getString("loai"),
								json_tinh.getString("vung"),
								json_tinh.getString("dientich"),
								json_tinh.getInt("danso"),
								json_tinh.getString("mota")
								);
	
						arrayTinh.add(tinh);
	
					}
					
					ArrayAdapter<Tinh> adapterThemCapTinh_id=new ArrayAdapter<Tinh>(ThemHuyenActivity.this, android.R.layout.simple_spinner_item,arrayTinh);
					adapterThemCapTinh_id.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
					spinnerThemCapTinh_id.setAdapter(adapterThemCapTinh_id);
				
					Toast.makeText(getApplicationContext(),
							"Đã tải xong spinner tỉnh", Toast.LENGTH_LONG)
							.show();
				}
			} else {
	
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}

}

}
