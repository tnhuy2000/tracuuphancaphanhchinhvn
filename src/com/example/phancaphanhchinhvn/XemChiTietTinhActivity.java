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

public class XemChiTietTinhActivity extends Activity {
	
	TextView tvMaTinh;
	TextView tvTenTinh;
	TextView tvLoai;
	TextView tvVung;
	TextView tvDienTich;
	TextView tvDanSo;
	TextView tvMoTa;
	TextView tvTongSoHuyen;
	//Button btnDat;
	Tinh tinhChiTiet;
	Intent intent;
	
	Huyen huyenct;
	//list
	ArrayList<Huyen> arrayHuyen;
	DSHuyenArrayAdapter_Gridview arrayAdapterHuyen;
	GridView danhsach;
	Context context;
		
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xem_chi_tiet_tinh);
		tvMaTinh=(TextView)findViewById(R.id.textViewMaTinh);
		tvTenTinh=(TextView)findViewById(R.id.textViewTenTinh);
		tvLoai=(TextView)findViewById(R.id.textViewLoai);
		
		tvVung=(TextView)findViewById(R.id.textViewVung);
		tvDienTich=(TextView)findViewById(R.id.textViewDienTich);
		tvDanSo=(TextView)findViewById(R.id.textViewDanSo);
		tvMoTa=(TextView)findViewById(R.id.textViewMoTa);
		tvTongSoHuyen=(TextView)findViewById(R.id.textViewSoLuongQuanHuyen);
		
		intent=getIntent();
		Bundle b=intent.getBundleExtra("DATA");
		tinhChiTiet=(Tinh)b.getSerializable("captinh");
		
		tvMaTinh.setText(tinhChiTiet.getIdTinh());
		tvTenTinh.setText(tinhChiTiet.getTenTinh());
		tvLoai.setText(tinhChiTiet.getLoai());
		tvVung.setText(tinhChiTiet.getVung());
		tvDienTich.setText(tinhChiTiet.getDientich()+" Km\u00B2");
		tvDanSo.setText(tinhChiTiet.getDanso()+" người");
		tvMoTa.setText(tinhChiTiet.getMota());
		
		
		//hiển thị grid danh sách huyện theo id tỉnh
		danhsach = (GridView) findViewById(R.id.gridView1);
		arrayHuyen = new ArrayList<Huyen>();
		this.context=this;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xem_chi_tiet_tinh, menu);
		return true;
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
		params.add(new BasicNameValuePair("tag", "xemchitiettinh"));
		params.add(new BasicNameValuePair("captinh_id", tinhChiTiet.getIdTinh()));
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
					int dem=0;
					for (int i = 0; i < soLuong; i++) {
	
						dem++;
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
					tvTongSoHuyen.setText(dem+"");
					
					arrayAdapterHuyen = new DSHuyenArrayAdapter_Gridview(
							XemChiTietTinhActivity.this, arrayHuyen);
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

	
}
