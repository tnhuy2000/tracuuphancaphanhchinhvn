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
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class TraCuuTheoTenMaActivity extends Activity {

	String phancap[]={"Cấp tỉnh","Cấp huyện","Cấp xã"};
    Spinner spinnerPhanCap;
    ArrayAdapter<String> adapterPhanCap;
    
    //cap tinh
    ArrayList<Tinh> arrayTinh;
	TimTinhArrayAdapter_listview arrayAdapterTimTinh;
	
	//cap huyen
	ArrayList<Huyen> arrayHuyen;
	TimHuyenArrayAdapter_listview arrayAdapterTimHuyen;
		
	//cap xa
	ArrayList<Xa> arrayXa;
	TimXaArrayAdapter_listview arrayAdapterTimXa;
		
	private ListView lv;
	private Button btnTimMa;
	private Button btnTimTen;
	
	
	
	private EditText etTenTimKiem;
	private EditText etMaTimKiem;
	
	private TraCuuTheoTenMaActivity context;
	
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tra_cuu_theo_ten_ma);
		//array tinh
		arrayTinh = new ArrayList<Tinh>();
		
		//array huyen
		arrayHuyen = new ArrayList<Huyen>();
		
		//array phuongxa
		arrayXa = new ArrayList<Xa>();
		
		//ket noi
		lv=(ListView)findViewById(R.id.listViewKQ);
		btnTimMa=(Button)findViewById(R.id.buttonTimKiemMa);
		btnTimTen=(Button)findViewById(R.id.buttonTimKiemTen);
		
		etMaTimKiem=(EditText)findViewById(R.id.editTextMaTimKiem);
		etTenTimKiem=(EditText)findViewById(R.id.editTextTenTimKiem);
		
		spinnerPhanCap=(Spinner)findViewById(R.id.spinnerPhanCap);
		ArrayAdapter<String>adapterPhanCap=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,phancap);
		adapterPhanCap.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerPhanCap.setAdapter(adapterPhanCap);
		
		this.context=this;
		spinnerPhanCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

          
			
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				// TODO Auto-generated method stub
				 //arrTinhFilter.clear();
	               // String pc=phancap[i];
	                if(l==0){
	                	
	                	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

	                	btnTimTen.setOnClickListener(new OnClickListener() {
	            			
	            			@Override
	            			public void onClick(View arg0) {
	            				// TODO Auto-generated method stub
	            				new JSONParserAsyncTaskTimTenTinh().execute();
	            			}
	            		});
	                	btnTimMa.setOnClickListener(new OnClickListener() {
	            			
	            			@Override
	            			public void onClick(View arg0) {
	            				// TODO Auto-generated method stub
	            				new JSONParserAsyncTaskTimMaTinh().execute();
	            			}
	            		});
	                	
	                	
	                	//select.setText("Bạn đã chọn= "+l);
	                }
	                else if(l==1){
	                	
	                	//select.setText("Ngược lại, Bạn đã chọn= "+l);
	                	
	                	
	                	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
						
	                	btnTimTen.setOnClickListener(new OnClickListener() {
	            			
	            			@Override
	            			public void onClick(View arg0) {
	            				// TODO Auto-generated method stub
	            				new JSONParserAsyncTaskTimTenHuyen().execute();
	            			}
	            		});
	                	btnTimMa.setOnClickListener(new OnClickListener() {
	            			
	            			@Override
	            			public void onClick(View arg0) {
	            				// TODO Auto-generated method stub
	            				new JSONParserAsyncTaskTimMaHuyen().execute();
	            			}
	            		});
	            		
	                }
	                else
	                {
	                	//select.setText("Ngược lại, Bạn đã chọn= "+l);
	                	
	                	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
	                	
	                	btnTimTen.setOnClickListener(new OnClickListener() {
	            			
	            			@Override
	            			public void onClick(View arg0) {
	            				// TODO Auto-generated method stub
	            				new JSONParserAsyncTaskTimTenXa().execute();
	            			}
	            		});
	                	btnTimMa.setOnClickListener(new OnClickListener() {
	            			
	            			@Override
	            			public void onClick(View arg0) {
	            				// TODO Auto-generated method stub
	            				new JSONParserAsyncTaskTimMaXa().execute();
	            			}
	            		});
	                	
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
		getMenuInflater().inflate(R.menu.tra_cuu_theo_ten_ma, menu);
		return true;
	}
	//tìm tên tinh
	private class JSONParserAsyncTaskTimTenTinh extends
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
		params.add(new BasicNameValuePair("tag", "timtinh"));
		params.add(new BasicNameValuePair("tentinh", etTenTimKiem.getText()+""));
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
					arrayAdapterTimTinh = new TimTinhArrayAdapter_listview(
							TraCuuTheoTenMaActivity.this, arrayTinh);
					lv.setAdapter(arrayAdapterTimTinh);
					// arrayAdapterSach.notifyDataSetChanged();
					// napDanhSachNongDan();
					Toast.makeText(getApplicationContext(),
							"Đã tìm kiếm xong", Toast.LENGTH_LONG)
							.show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"Không có tỉnh phù hợp nội dung cần tìm", Toast.LENGTH_LONG)
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

	//tìm mã tỉnh
	private class JSONParserAsyncTaskTimMaTinh extends
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
		params.add(new BasicNameValuePair("tag", "timmatinh"));
		params.add(new BasicNameValuePair("id", etMaTimKiem.getText()+""));
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
					arrayAdapterTimTinh = new TimTinhArrayAdapter_listview(
							TraCuuTheoTenMaActivity.this, arrayTinh);
					lv.setAdapter(arrayAdapterTimTinh);
					// arrayAdapterSach.notifyDataSetChanged();
					// napDanhSachNongDan();
					Toast.makeText(getApplicationContext(),
							"Đã tìm kiếm xong", Toast.LENGTH_LONG)
							.show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"Không có tỉnh phù hợp nội dung cần tìm", Toast.LENGTH_LONG)
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

	//tìm tên huyện
	private class JSONParserAsyncTaskTimTenHuyen extends
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
		params.add(new BasicNameValuePair("tag", "timhuyen"));
		params.add(new BasicNameValuePair("tenhuyen", etTenTimKiem.getText()+""));
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
					arrayAdapterTimHuyen = new TimHuyenArrayAdapter_listview(
							TraCuuTheoTenMaActivity.this, arrayHuyen);
					lv.setAdapter(arrayAdapterTimHuyen);
					// arrayAdapterSach.notifyDataSetChanged();
					// napDanhSachNongDan();
					Toast.makeText(getApplicationContext(),
							"Đã tìm kiếm xong", Toast.LENGTH_LONG)
							.show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"Không có huyện cần tìm", Toast.LENGTH_LONG)
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

	//tìm mã huyện
	private class JSONParserAsyncTaskTimMaHuyen extends
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
		params.add(new BasicNameValuePair("tag", "timmahuyen"));
		params.add(new BasicNameValuePair("id", etMaTimKiem.getText()+""));
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
					arrayAdapterTimHuyen = new TimHuyenArrayAdapter_listview(
							TraCuuTheoTenMaActivity.this, arrayHuyen);
					lv.setAdapter(arrayAdapterTimHuyen);
					// arrayAdapterSach.notifyDataSetChanged();
					// napDanhSachNongDan();
					Toast.makeText(getApplicationContext(),
							"Đã tìm kiếm xong", Toast.LENGTH_LONG)
							.show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"Không có huyện cần tìm", Toast.LENGTH_LONG)
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

	//tìm tên xã
	private class JSONParserAsyncTaskTimTenXa extends
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
			params.add(new BasicNameValuePair("tag", "timphuongxa"));
			params.add(new BasicNameValuePair("tenphuongxa", etTenTimKiem.getText()+""));
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
						arrayAdapterTimXa = new TimXaArrayAdapter_listview(
								TraCuuTheoTenMaActivity.this, arrayXa);
						lv.setAdapter(arrayAdapterTimXa);
						// arrayAdapterSach.notifyDataSetChanged();
						// napDanhSachNongDan();
						Toast.makeText(getApplicationContext(),
								"Đã tìm kiếm xong", Toast.LENGTH_LONG)
								.show();
					}
					else
					{
						Toast.makeText(getApplicationContext(),
								"Không có  phường xã cần tìm", Toast.LENGTH_LONG)
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
	//tìm tên xã
	private class JSONParserAsyncTaskTimMaXa extends
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
				params.add(new BasicNameValuePair("tag", "timmaphuongxa"));
				params.add(new BasicNameValuePair("id", etMaTimKiem.getText()+""));
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
							arrayAdapterTimXa = new TimXaArrayAdapter_listview(
									TraCuuTheoTenMaActivity.this, arrayXa);
							lv.setAdapter(arrayAdapterTimXa);
							// arrayAdapterSach.notifyDataSetChanged();
							// napDanhSachNongDan();
							Toast.makeText(getApplicationContext(),
									"Đã tìm kiếm xong", Toast.LENGTH_LONG)
									.show();
						}
						else
						{
							Toast.makeText(getApplicationContext(),
									"Không có  phường xã cần tìm", Toast.LENGTH_LONG)
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
