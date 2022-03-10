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

public class ThemXaActivity extends Activity{

	Button buttonThemMoi,buttonXoaTrang,buttonHuy;
	
	EditText editTextThemMaXa,editTextThemTenXa;
	
	Context context;
	Intent intent;
	
	String loaiXa[]={"Phường","Thị trấn","Xã"};
    Spinner spinnerThemLoaiXa, spinnerThemCapHuyen_id;
    ArrayAdapter<String> adapterThemLoaiXa;
    
  //spinner tỉnh
    private ArrayList<Huyen> arrayHuyen;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_xa);
		//ket noi
		buttonXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
		editTextThemMaXa=(EditText)findViewById(R.id.editTextThemMaXa);
		editTextThemTenXa=(EditText)findViewById(R.id.editTextThemTenXa);
		spinnerThemLoaiXa=(Spinner)findViewById(R.id.spinnerThemLoaiXa);
		spinnerThemCapHuyen_id=(Spinner)findViewById(R.id.spinnerThemCapHuyen_id);
		 new JSONParserAsyncTaskXa().execute();
		 
		buttonHuy=(Button)findViewById(R.id.buttonHuy);
		buttonXoaTrang=(Button)findViewById(R.id.buttonXoaTrang);
		buttonThemMoi=(Button)findViewById(R.id.buttonXaThemMoi);
		
		final ArrayAdapter<String>adapterSuaLoaiXa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,loaiXa);
		adapterSuaLoaiXa.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerThemLoaiXa.setAdapter(adapterSuaLoaiXa);
		//tỉnh
		arrayHuyen = new ArrayList<Huyen>();
		
		
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
				((ThemXaActivity) arg0.getContext()).finish();
				Intent themXa=new Intent(ThemXaActivity.this,QLCapXaActivity.class);
				startActivity(themXa);
			}
		});
		
		//them mới
		buttonThemMoi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String maXa=editTextThemMaXa.getText().toString()+"";
				
				if(maXa.trim().equals(""))
					Toast.makeText(getApplicationContext(),
							"Vui lòng nhập mã xã", Toast.LENGTH_LONG)
							.show();
		        else if(editTextThemTenXa.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập tên xã", Toast.LENGTH_LONG)
							.show();
		        else if(spinnerThemLoaiXa.getSelectedItem()==null)
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng chọn cấp", Toast.LENGTH_LONG)
							.show();
		        
		        else
		        {
		        
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "themphuongxa"));
				
				params.add(new BasicNameValuePair("id", editTextThemMaXa.getText().toString()+""));
				params.add(new BasicNameValuePair("tenphuongxa", editTextThemTenXa.getText().toString()+""));
				params.add(new BasicNameValuePair("loai", spinnerThemLoaiXa.getSelectedItem().toString()+""));
				params.add(new BasicNameValuePair("caphuyen_id",((Huyen)spinnerThemCapHuyen_id.getSelectedItem()).getIdHuyen().toString()+""));
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Thêm phường xã thành công", Toast.LENGTH_LONG)
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
		getMenuInflater().inflate(R.menu.them_xa, menu);
		return true;
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		editTextThemMaXa.setText(null);
		editTextThemTenXa.setText(null);
		
	}
	private class JSONParserAsyncTaskXa extends
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
		params.add(new BasicNameValuePair("tag", "loadhuyen"));
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
	
					JSONObject json_huyen;
					Huyen huyen;
					arrayHuyen.clear();
					for (int i = 0; i < soLuong; i++) {
	
						json_huyen = json.getJSONObject("caphuyen" + i);
	
						// Cursor
						// cursor=database.query("sach",null,null,null,null,null,null);
	
						huyen = new Huyen(json_huyen.getString("h.id"),
								json_huyen.getString("h.tenhuyen"),
								json_huyen.getString("h.loai"),
								json_huyen.getString("h.captinh_id"),
								json_huyen.getString("t.tentinh")
								
								);
	
						arrayHuyen.add(huyen);
	
					}
					
					ArrayAdapter<Huyen> adapterThemCapHuyen_id=new ArrayAdapter<Huyen>(ThemXaActivity.this, android.R.layout.simple_spinner_item,arrayHuyen);
					adapterThemCapHuyen_id.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
					spinnerThemCapHuyen_id.setAdapter(adapterThemCapHuyen_id);
				
					
				}
			} else {
	
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}

}

}
