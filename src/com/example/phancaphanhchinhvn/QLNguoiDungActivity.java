package com.example.phancaphanhchinhvn;

import java.util.ArrayList;

import com.example.phancaphanhchinhvn.DSTinhArrayAdapter_Gridview;
import com.example.phancaphanhchinhvn.JSONParser;
import com.example.phancaphanhchinhvn.Tinh;
import com.example.phancaphanhchinhvn.XemTinhActivity;



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

public class QLNguoiDungActivity extends Activity implements OnClickListener{

	public static final int SAVE_EDIT_TINH = 104;
	Button buttonThemNguoiDung, buttonNDTimKiem;
	EditText editNDTimKiem;
	ArrayList<NguoiDung> arrayNguoiDung;
	TimNguoiDungArrayAdapter_listview arrayAdapterTimNguoiDung;
	DSNguoiDungArrayAdapter_Gridview arrayAdapterNguoiDung;
	GridView dsnguoidung;
	Context context;
	private int selectNguoiDung;
	// URL to get JSON Array
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qlnguoi_dung);
		//ket nối
		editNDTimKiem=(EditText)findViewById(R.id.editTextNDTimKiem);
		buttonThemNguoiDung=(Button)findViewById(R.id.buttonThemND);
		//buttonThoat=(Button)findViewById(R.id.buttonThoat);
		//buttonTroVe=(Button)findViewById(R.id.buttonTroVe);
		buttonNDTimKiem=(Button)findViewById(R.id.buttonNDTimKiem);
		
		buttonNDTimKiem.setOnClickListener(this);
		buttonThemNguoiDung.setOnClickListener(this);
		//buttonTroVe.setOnClickListener(this);
		//buttonThoat.setOnClickListener(this);
		
		dsnguoidung = (GridView) findViewById(R.id.gridView1);
		arrayNguoiDung = new ArrayList<NguoiDung>();
		this.context=this;
		
		dsnguoidung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				selectNguoiDung=arg2;
                AlertDialog.Builder builder=new AlertDialog.Builder(QLNguoiDungActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn chắc chắn muốn xoá?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        
                        //database.delete("tacgia", "matacgia=?", new String[]{maTacGia});
                        JSONParser jParser = new JSONParser();
        				List<NameValuePair> params = new ArrayList<NameValuePair>();
        				params.add(new BasicNameValuePair("tag", "xoanguoidung"));
        				params.add(new BasicNameValuePair("id", arrayNguoiDung.get(selectNguoiDung).getId()+""));
        				// Getting JSON from URL
        				JSONObject json = jParser.getJSONFromUrl(url, params);
        				try {
        					if (json.getString("success") != null) {
        						// loginErrorMsg.setText("");
        						// String res = json.getString(KEY_SUCCESS);
        						// if(Integer.parseInt(res) == 1){
        						if (json.getString("success").equals("1")) {							
        							Toast.makeText(getApplicationContext(),
        									"Xoá người dùng thành công", Toast.LENGTH_LONG)
        									.show();
        							
        							arrayNguoiDung.remove(selectNguoiDung);
        	                        //cách 2
        	                        //taiDanhSachTacGia();
        	                        arrayAdapterNguoiDung.notifyDataSetChanged();
        	                        
        		                    
                    				
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
		
		dsnguoidung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle data=new Bundle();
				data.putSerializable("nguoidung", arrayNguoiDung.get(position));
				Intent ndCapNhat=new Intent(context ,SuaNguoiDungActivity.class);
				ndCapNhat.putExtra("DATA", data);
				startActivity(ndCapNhat);
			}
		});

		new JSONParserAsyncTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qlnguoi_dung, menu);
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
			params.add(new BasicNameValuePair("tag", "loadnguoidung"));
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
		
						JSONObject json_nguoidung;
						NguoiDung nguoidung;
						arrayNguoiDung.clear();
						for (int i = 0; i < soLuong; i++) {
		
							json_nguoidung = json.getJSONObject("nguoidung" + i);
		
							// Cursor
							// cursor=database.query("sach",null,null,null,null,null,null);
		
							nguoidung = new NguoiDung(json_nguoidung.getInt("id"),
									json_nguoidung.getString("hoten"),
									json_nguoidung.getString("sodienthoai"),
									json_nguoidung.getString("email"),
									json_nguoidung.getString("loai")
									);
		
							arrayNguoiDung.add(nguoidung);
		
						}
						arrayAdapterNguoiDung = new DSNguoiDungArrayAdapter_Gridview(
								QLNguoiDungActivity.this, arrayNguoiDung);
						dsnguoidung.setAdapter(arrayAdapterNguoiDung);
						// arrayAdapterSach.notifyDataSetChanged();
						// napDanhSachNongDan();
						Toast.makeText(getApplicationContext(),
								"Đã tải xong danh mục người dùng", Toast.LENGTH_LONG)
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
		if(arg0==buttonThemNguoiDung){
			
			Intent themnd=new Intent(this,ThemNguoiDungActivity.class);
			startActivity(themnd);
			
		}
		if(arg0==buttonNDTimKiem){
			
			new JSONParserAsyncTaskNguoiDungTimKiem().execute();
			
		}
		
		
	}
	//tìm tên tinh
		private class JSONParserAsyncTaskNguoiDungTimKiem extends
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
			params.add(new BasicNameValuePair("tag", "qlnguoidungtimkiem"));
			
			params.add(new BasicNameValuePair("hoten", editNDTimKiem.getText()+""));
			
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
		
						JSONObject json_nguoidung;
						NguoiDung nguoidung;
						arrayNguoiDung.clear();
						for (int i = 0; i < soLuong; i++) {
		
							json_nguoidung = json.getJSONObject("nguoidung" + i);
		
							// Cursor
							// cursor=database.query("sach",null,null,null,null,null,null);
		
							nguoidung = new NguoiDung(json_nguoidung.getInt("id"),
									json_nguoidung.getString("hoten"),
									json_nguoidung.getString("sodienthoai"),
									json_nguoidung.getString("email"),
									json_nguoidung.getString("loai")
									);
		
							arrayNguoiDung.add(nguoidung);
		
						}
						arrayAdapterTimNguoiDung = new TimNguoiDungArrayAdapter_listview(
								QLNguoiDungActivity.this, arrayNguoiDung);
						dsnguoidung.setAdapter(arrayAdapterTimNguoiDung);
						// arrayAdapterSach.notifyDataSetChanged();
						// napDanhSachNongDan();
						Toast.makeText(getApplicationContext(),
								"Đã tìm kiếm xong", Toast.LENGTH_LONG)
								.show();
					}
					else
					{
						Toast.makeText(getApplicationContext(),
								"Không có người dùng  cần tìm", Toast.LENGTH_LONG)
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
