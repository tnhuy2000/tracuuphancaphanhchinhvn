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

public class QLCapTinhActivity extends Activity implements OnClickListener{

	public static final int SAVE_EDIT_TINH = 104;
	Button buttonThemTinh, buttonTinhTimKiem;
	EditText editTinhTimKiem;
	ArrayList<Tinh> arrayTinh;
	TimTinhArrayAdapter_listview arrayAdapterTimTinh;
	DSTinhArrayAdapter_Gridview arrayAdapterTinh;
	GridView dstinh;
	Context context;
	private int selectTinh;
	// URL to get JSON Array
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qlcap_tinh);
		//ket nối
		editTinhTimKiem=(EditText)findViewById(R.id.editTextTinhTimKiem);
		buttonThemTinh=(Button)findViewById(R.id.buttonThemTinh);
		//buttonThoat=(Button)findViewById(R.id.buttonThoat);
		//buttonTroVe=(Button)findViewById(R.id.buttonTroVe);
		buttonTinhTimKiem=(Button)findViewById(R.id.buttonTinhTimKiem);
		
		buttonTinhTimKiem.setOnClickListener(this);
		buttonThemTinh.setOnClickListener(this);
		//buttonTroVe.setOnClickListener(this);
		//buttonThoat.setOnClickListener(this);
		
		dstinh = (GridView) findViewById(R.id.gridView1);
		arrayTinh = new ArrayList<Tinh>();
		this.context=this;
		
		dstinh.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				selectTinh=arg2;
                AlertDialog.Builder builder=new AlertDialog.Builder(QLCapTinhActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn chắc chắn muốn xoá?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        
                        //database.delete("tacgia", "matacgia=?", new String[]{maTacGia});
                        JSONParser jParser = new JSONParser();
        				List<NameValuePair> params = new ArrayList<NameValuePair>();
        				params.add(new BasicNameValuePair("tag", "xoatinh"));
        				params.add(new BasicNameValuePair("id", arrayTinh.get(selectTinh).getIdTinh()+""));
        				// Getting JSON from URL
        				JSONObject json = jParser.getJSONFromUrl(url, params);
        				try {
        					if (json.getString("success") != null) {
        						// loginErrorMsg.setText("");
        						// String res = json.getString(KEY_SUCCESS);
        						// if(Integer.parseInt(res) == 1){
        						if (json.getString("success").equals("1")) {							
        							Toast.makeText(getApplicationContext(),
        									"Xoá tỉnh thành công", Toast.LENGTH_LONG)
        									.show();
        							
        							arrayTinh.remove(selectTinh);
        	                        //cách 2
        	                        //taiDanhSachTacGia();
        	                        arrayAdapterTinh.notifyDataSetChanged();
        	                        
        		                    
                    				
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
		
		dstinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle data=new Bundle();
				data.putSerializable("captinh", arrayTinh.get(position));
				Intent tinhCapNhat=new Intent(context ,SuaTinhActivity.class);
				tinhCapNhat.putExtra("DATA", data);
				startActivity(tinhCapNhat);
			}
		});

		new JSONParserAsyncTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qlcap_tinh, menu);
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
								QLCapTinhActivity.this, arrayTinh);
						dstinh.setAdapter(arrayAdapterTinh);
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

	@SuppressLint("NewApi") @Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==buttonThemTinh){
			
			Intent themtinh=new Intent(this,ThemTinhActivity.class);
			startActivity(themtinh);
			
		}
		if(arg0==buttonTinhTimKiem){
			
			new JSONParserAsyncTaskTinhTimKiem().execute();
			
		}
		
		
	}
	//tìm tên tinh
		private class JSONParserAsyncTaskTinhTimKiem extends
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
			params.add(new BasicNameValuePair("tag", "qltinhtimkiem"));
			params.add(new BasicNameValuePair("id", editTinhTimKiem.getText()+""));
			params.add(new BasicNameValuePair("tentinh", editTinhTimKiem.getText()+""));
			
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
								QLCapTinhActivity.this, arrayTinh);
						dstinh.setAdapter(arrayAdapterTimTinh);
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

}
