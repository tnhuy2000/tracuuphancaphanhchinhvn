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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TraCuuActivity extends Activity {
	//Cấp Tỉnh
	ArrayList<Tinh> arrayTinh;
	DSTinhArrayAdapter_Gridview arrayAdapterTinh;
	
	ArrayList<Huyen> arrayHuyen;
	DSHuyenArrayAdapter_Gridview arrayAdapterHuyen;
	
	ArrayList<Xa> arrayXa;
	DSXaArrayAdapter_Gridview arrayAdapterXa;
	
	GridView danhsach;
	//TextView select;
	Context context;
	
	String phancap[]={"Cấp tỉnh","Cấp huyện","Cấp xã"};
    Spinner spinnerPhanCap;
    ArrayAdapter<String> adapterPhanCap;
   // ArrayList<Tinh> arrTinhFilter;
    
    
	// URL to get JSON Array
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tra_cuu);
		
		danhsach = (GridView) findViewById(R.id.gridView1);
		//select=(TextView)findViewById(R.id.select);
		spinnerPhanCap=(Spinner)findViewById(R.id.spinnerPhanCap);
		
		arrayTinh = new ArrayList<Tinh>();
		arrayHuyen = new ArrayList<Huyen>();
		arrayXa = new ArrayList<Xa>();
		this.context=this;
		
		ArrayAdapter<String>adapterPhanCap=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,phancap);
		adapterPhanCap.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerPhanCap.setAdapter(adapterPhanCap);
		
		
		spinnerPhanCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

          
			
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				// TODO Auto-generated method stub
				 //arrTinhFilter.clear();
	               // String pc=phancap[i];
	                if(l==0){
	                	
	                	danhsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            			@Override
	            			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	            					long arg3) {
	            				// TODO Auto-generated method stub
	            				Bundle data=new Bundle();
	            				data.putSerializable("captinh", arrayTinh.get(position));
	            				Intent tinhChiTiet=new Intent(context ,XemChiTietTinhActivity.class);
	            				tinhChiTiet.putExtra("DATA", data);
	            				startActivity(tinhChiTiet);
	            			}
	            		});

	            		new JSONParserAsyncTaskCapTinh().execute();
	                	//select.setText("Bạn đã chọn= "+l);
	                }
	                else if(l==1){
	                	
	                	//select.setText("Ngược lại, Bạn đã chọn= "+l);
	                	
	                	danhsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            			@Override
	            			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	            					long arg3) {
	            				// TODO Auto-generated method stub
	            				Bundle data=new Bundle();
	            				data.putSerializable("caphuyen", arrayHuyen.get(position));
	            				Intent huyenChiTiet=new Intent(context ,XemChiTietHuyenActivity.class);
	            				huyenChiTiet.putExtra("DATA", data);
	            				startActivity(huyenChiTiet);
	            			}
	            		});
						
	            		new JSONParserAsyncTaskCapHuyen().execute();
	                }
	                else
	                {
	                	//select.setText("Ngược lại, Bạn đã chọn= "+l);
	                	danhsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            			@Override
	            			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	            					long arg3) {
	            				// TODO Auto-generated method stub
	            				Bundle data=new Bundle();
	            				data.putSerializable("capphuongxa", arrayXa.get(position));
	            				Intent xaChiTiet=new Intent(context ,XemChiTietXaActivity.class);
	            				xaChiTiet.putExtra("DATA", data);
	            				startActivity(xaChiTiet);
	            			}
	            		});
	                	
	                	new JSONParserAsyncTaskCapXa().execute();
	                }
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				//select.setText("");
			}
        });
	
        
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tra_cuu, menu);
		return true;
	}
	
	private class JSONParserAsyncTaskCapTinh extends
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
					arrayAdapterTinh = new DSTinhArrayAdapter_Gridview(
							TraCuuActivity.this, arrayTinh);
					danhsach.setAdapter(arrayAdapterTinh);
					// arrayAdapterSach.notifyDataSetChanged();
					// napDanhSachNongDan();
					Toast.makeText(getApplicationContext(),
							"Đã tải xong danh mục tỉnh", Toast.LENGTH_LONG)
							.show();
				}
			} else {
	
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}

}

	
	private class JSONParserAsyncTaskCapHuyen extends
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
					arrayAdapterHuyen = new DSHuyenArrayAdapter_Gridview(
							TraCuuActivity.this, arrayHuyen);
					danhsach.setAdapter(arrayAdapterHuyen);
					// arrayAdapterSach.notifyDataSetChanged();
					// napDanhSachNongDan();
					Toast.makeText(getApplicationContext(),
							"Đã tải xong danh mục huyện", Toast.LENGTH_LONG)
							.show();
				}
			} else {
	
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}

}

	
	private class JSONParserAsyncTaskCapXa extends
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
		params.add(new BasicNameValuePair("tag", "loadphuongxa"));
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
	
					JSONObject json_xa;
					Xa xa;
					arrayXa.clear();
					for (int i = 0; i < soLuong; i++) {
	
						json_xa = json.getJSONObject("capphuongxa" + i);
	
						// Cursor
						// cursor=database.query("sach",null,null,null,null,null,null);
	
						xa = new Xa(json_xa.getString("x.id"),
								json_xa.getString("x.tenphuongxa"),
								json_xa.getString("x.loai"),
								json_xa.getString("x.caphuyen_id"),
								json_xa.getString("h.tenhuyen"),
								json_xa.getString("h.captinh_id"),
								json_xa.getString("t.tentinh")
								);
	
						arrayXa.add(xa);
	
					}
					arrayAdapterXa = new DSXaArrayAdapter_Gridview(
							TraCuuActivity.this, arrayXa);
					danhsach.setAdapter(arrayAdapterXa);
					// arrayAdapterSach.notifyDataSetChanged();
					// napDanhSachNongDan();
					Toast.makeText(getApplicationContext(),
							"Đã tải xong danh mục phường xã", Toast.LENGTH_LONG)
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
