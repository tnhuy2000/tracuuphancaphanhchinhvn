package com.example.phancaphanhchinhvn;

import java.util.ArrayList;

import com.example.phancaphanhchinhvn.DSXaArrayAdapter_Gridview;
import com.example.phancaphanhchinhvn.JSONParser;
import com.example.phancaphanhchinhvn.Xa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class QLCapXaActivity extends Activity implements OnClickListener{

	//public static final int SAVE_EDIT_Xa = 104;
	Button buttonThemXa, buttonXaTimKiem;
	EditText editXaTimKiem;
	ArrayList<Xa> arrayXa;
	TimXaArrayAdapter_listview arrayAdapterTimXa;
	DSXaArrayAdapter_Gridview arrayAdapterXa;
	GridView dsXa;
	Context context;
	private int selectXa;
	// URL to get JSON Array
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qlcap_xa);
		//ket nối
		editXaTimKiem=(EditText)findViewById(R.id.editTextXaTimKiem);
		buttonThemXa=(Button)findViewById(R.id.buttonThemXa);
		//buttonThoat=(Button)findViewById(R.id.buttonThoat);
		//buttonTroVe=(Button)findViewById(R.id.buttonTroVe);
		buttonXaTimKiem=(Button)findViewById(R.id.buttonXaTimKiem);
		
		buttonXaTimKiem.setOnClickListener(this);
		buttonThemXa.setOnClickListener(this);
		//buttonTroVe.setOnClickListener(this);
		//buttonThoat.setOnClickListener(this);
		
		dsXa = (GridView) findViewById(R.id.gridView1);
		arrayXa = new ArrayList<Xa>();
		this.context=this;
		
		dsXa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				selectXa=arg2;
                AlertDialog.Builder builder=new AlertDialog.Builder(QLCapXaActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn chắc chắn muốn xoá?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        
                        //database.delete("tacgia", "matacgia=?", new String[]{maTacGia});
                        JSONParser jParser = new JSONParser();
        				List<NameValuePair> params = new ArrayList<NameValuePair>();
        				params.add(new BasicNameValuePair("tag", "xoaphuongxa"));
        				params.add(new BasicNameValuePair("id", arrayXa.get(selectXa).getIdXa()+""));
        				// Getting JSON from URL
        				JSONObject json = jParser.getJSONFromUrl(url, params);
        				try {
        					if (json.getString("success") != null) {
        						// loginErrorMsg.setText("");
        						// String res = json.getString(KEY_SUCCESS);
        						// if(Integer.parseInt(res) == 1){
        						if (json.getString("success").equals("1")) {							
        							Toast.makeText(getApplicationContext(),
        									"Xoá thành công", Toast.LENGTH_LONG)
        									.show();
        							
        							arrayXa.remove(selectXa);
        	                        //cách 2
        	                        //taiDanhSachTacGia();
        	                        arrayAdapterXa.notifyDataSetChanged();
        	                        
        		                    
                    				
        						}
        					} else {

        					}
        				
        				} catch (JSONException e) {
        					e.printStackTrace();
        				}
                        

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                return true;
			}
		});
		
		dsXa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle data=new Bundle();
				data.putSerializable("capphuongxa", arrayXa.get(position));
				Intent XaCapNhat=new Intent(context ,SuaXaActivity.class);
				XaCapNhat.putExtra("DATA", data);
				startActivity(XaCapNhat);
			}
		});

		new JSONParserAsyncTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qlcap_xa, menu);
		return true;
	}
	
	private class JSONParserAsyncTask extends
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
								QLCapXaActivity.this, arrayXa);
						dsXa.setAdapter(arrayAdapterXa);
						// arrayAdapterSach.notifyDataSetChanged();
						// napDanhSachNongDan();
						Toast.makeText(getApplicationContext(),
								"Đã tải xong danh sách phường xã", Toast.LENGTH_LONG)
								.show();
					}
				} else {
		
				}
		
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
		}
	
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==buttonThemXa){
			
			Intent themhuyen=new Intent(this,ThemXaActivity.class);
			startActivity(themhuyen);
			
		}
		if(arg0==buttonXaTimKiem){
			
			new JSONParserAsyncTaskXaTimKiem().execute();
			
		}
		
		
	}
	//tìm tên tinh
		private class JSONParserAsyncTaskXaTimKiem extends
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
			params.add(new BasicNameValuePair("tag", "qlphuongxatimkiem"));
			params.add(new BasicNameValuePair("id", editXaTimKiem.getText()+""));
			params.add(new BasicNameValuePair("tenphuongxa", editXaTimKiem.getText()+""));
			
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
								QLCapXaActivity.this, arrayXa);
						dsXa.setAdapter(arrayAdapterTimXa);
						// arrayAdapterSach.notifyDataSetChanged();
						// napDanhSachNongDan();
						Toast.makeText(getApplicationContext(),
								"Đã tìm kiếm xong", Toast.LENGTH_LONG)
								.show();
					}
					else
					{
						Toast.makeText(getApplicationContext(),
								"Không có xã phù hợp nội dung cần tìm", Toast.LENGTH_LONG)
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
