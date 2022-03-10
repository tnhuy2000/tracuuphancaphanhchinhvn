package com.example.phancaphanhchinhvn;

import java.util.ArrayList;

import com.example.phancaphanhchinhvn.DSHuyenArrayAdapter_Gridview;
import com.example.phancaphanhchinhvn.JSONParser;
import com.example.phancaphanhchinhvn.Huyen;

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

public class QLCapHuyenActivity extends Activity implements OnClickListener{

	public static final int SAVE_EDIT_Huyen = 104;
	Button buttonThemHuyen, buttonHuyenTimKiem;
	EditText editHuyenTimKiem;
	ArrayList<Huyen> arrayHuyen;
	TimHuyenArrayAdapter_listview arrayAdapterTimHuyen;
	DSHuyenArrayAdapter_Gridview arrayAdapterHuyen;
	GridView dshuyen;
	Context context;
	private int selectHuyen;
	// URL to get JSON Array
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qlcap_huyen);
		//ket nối
		editHuyenTimKiem=(EditText)findViewById(R.id.editTextHuyenTimKiem);
		buttonThemHuyen=(Button)findViewById(R.id.buttonThemHuyen);
		//buttonThoat=(Button)findViewById(R.id.buttonThoat);
		//buttonTroVe=(Button)findViewById(R.id.buttonTroVe);
		buttonHuyenTimKiem=(Button)findViewById(R.id.buttonHuyenTimKiem);
		
		buttonHuyenTimKiem.setOnClickListener(this);
		buttonThemHuyen.setOnClickListener(this);
		//buttonTroVe.setOnClickListener(this);
		//buttonThoat.setOnClickListener(this);
		
		dshuyen = (GridView) findViewById(R.id.gridView1);
		arrayHuyen = new ArrayList<Huyen>();
		this.context=this;
		
		dshuyen.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				selectHuyen=arg2;
                AlertDialog.Builder builder=new AlertDialog.Builder(QLCapHuyenActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn chắc chắn muốn xoá?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        
                        //database.delete("tacgia", "matacgia=?", new String[]{maTacGia});
                        JSONParser jParser = new JSONParser();
        				List<NameValuePair> params = new ArrayList<NameValuePair>();
        				params.add(new BasicNameValuePair("tag", "xoahuyen"));
        				params.add(new BasicNameValuePair("id", arrayHuyen.get(selectHuyen).getIdHuyen()+""));
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
        							
        							arrayHuyen.remove(selectHuyen);
        	                        //cách 2
        	                        //taiDanhSachTacGia();
        	                        arrayAdapterHuyen.notifyDataSetChanged();
        	                        
        		                    
                    				
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
		
		dshuyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle data=new Bundle();
				data.putSerializable("caphuyen", arrayHuyen.get(position));
				Intent huyenCapNhat=new Intent(context ,SuaHuyenActivity.class);
				huyenCapNhat.putExtra("DATA", data);
				startActivity(huyenCapNhat);
			}
		});

		new JSONParserAsyncTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qlcap_huyen, menu);
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
								QLCapHuyenActivity.this, arrayHuyen);
						dshuyen.setAdapter(arrayAdapterHuyen);
						// arrayAdapterSach.notifyDataSetChanged();
						// napDanhSachNongDan();
						Toast.makeText(getApplicationContext(),
								"Đã tải xong danh sách quận huyện", Toast.LENGTH_LONG)
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
		if(arg0==buttonThemHuyen){
			
			Intent themhuyen=new Intent(this,ThemHuyenActivity.class);
			startActivity(themhuyen);
			
		}
		if(arg0==buttonHuyenTimKiem){
			
			new JSONParserAsyncTaskHuyenTimKiem().execute();
			
		}
		
		
	}
	//tìm tên tinh
		private class JSONParserAsyncTaskHuyenTimKiem extends
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
			params.add(new BasicNameValuePair("tag", "qlhuyentimkiem"));
			params.add(new BasicNameValuePair("id", editHuyenTimKiem.getText()+""));
			params.add(new BasicNameValuePair("tenhuyen", editHuyenTimKiem.getText()+""));
			
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
								QLCapHuyenActivity.this, arrayHuyen);
						dshuyen.setAdapter(arrayAdapterTimHuyen);
						// arrayAdapterSach.notifyDataSetChanged();
						// napDanhSachNongDan();
						Toast.makeText(getApplicationContext(),
								"Đã tìm kiếm xong", Toast.LENGTH_LONG)
								.show();
					}
					else
					{
						Toast.makeText(getApplicationContext(),
								"Không có huyện phù hợp nội dung cần tìm", Toast.LENGTH_LONG)
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
