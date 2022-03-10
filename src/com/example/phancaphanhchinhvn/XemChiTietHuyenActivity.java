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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class XemChiTietHuyenActivity extends Activity {
	
	TextView tvMaHuyen;
	TextView tvTenHuyen;
	TextView tvLoai;
	
	TextView tvCapTinh_id_huyen;
	TextView tvTenTinh_huyen,tvTongSoLuongXa ;
	//Button btnDat;
	Huyen huyenChiTiet;
	Intent intent;
	//list
	ArrayList<Xa> arrayXa;
	DSXaArrayAdapter_Gridview arrayAdapterXa;
	GridView danhsach;
	Context context;
	// URL to get JSON Array
	
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xem_chi_tiet_huyen);
		tvMaHuyen=(TextView)findViewById(R.id.textViewMaHuyen);
		tvTenHuyen=(TextView)findViewById(R.id.textViewTenHuyen);
		tvLoai=(TextView)findViewById(R.id.textViewLoai);
		
		tvCapTinh_id_huyen=(TextView)findViewById(R.id.textViewCapTinh_id_huyen);
		tvTenTinh_huyen=(TextView)findViewById(R.id.textViewTenTinh_huyen);
		tvTongSoLuongXa=(TextView)findViewById(R.id.textViewSoLuongPhuongXa);
		intent=getIntent();
		Bundle b=intent.getBundleExtra("DATA");
		huyenChiTiet=(Huyen)b.getSerializable("caphuyen");
		
		tvMaHuyen.setText(huyenChiTiet.getIdHuyen());
		tvTenHuyen.setText(huyenChiTiet.getTenHuyen());
		tvLoai.setText(huyenChiTiet.getLoai());
		tvCapTinh_id_huyen.setText(huyenChiTiet.getCaptinh_id());
		tvTenTinh_huyen.setText(huyenChiTiet.getTentinh());
		
		
		
		//hiển thị grid danh sách xã theo id huyện
		danhsach = (GridView) findViewById(R.id.gridView1);
		arrayXa = new ArrayList<Xa>();
		this.context=this;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xem_chi_tiet_huyen, menu);
		return true;
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
		params.add(new BasicNameValuePair("tag", "xemchitiethuyen"));
		params.add(new BasicNameValuePair("caphuyen_id", huyenChiTiet.getIdHuyen()));
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
					int dem=0;
					for (int i = 0; i < soLuong; i++) {
	
						dem++;
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
					tvTongSoLuongXa.setText(dem+"");
					arrayAdapterXa = new DSXaArrayAdapter_Gridview(
							XemChiTietHuyenActivity.this, arrayXa);
					danhsach.setAdapter(arrayAdapterXa);
					// arrayAdapterSach.notifyDataSetChanged();
					// napDanhSachNongDan();
					Toast.makeText(getApplicationContext(),
							"Đã tải xong danh mục xã theo huyện", Toast.LENGTH_LONG)
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
