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
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class XemTinhActivity extends Activity {

	ArrayList<Tinh> arrayTinh;
	DSTinhArrayAdapter_Gridview arrayAdapterTinh;
	GridView dstinh;
	Context context;
	// URL to get JSON Array
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xem_tinh);
		
		dstinh = (GridView) findViewById(R.id.gridView1);
		arrayTinh = new ArrayList<Tinh>();
		this.context=this;
		
		dstinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

		new JSONParserAsyncTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xem_tinh, menu);
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
								XemTinhActivity.this, arrayTinh);
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
}
