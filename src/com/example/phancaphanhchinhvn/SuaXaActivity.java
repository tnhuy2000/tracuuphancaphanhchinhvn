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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SuaXaActivity extends Activity {
	Button buttonCapNhat,buttonHuy;
	Xa xa;
    Intent intent;
	EditText editTextSuaMaXa,editTextSuaTenXa,editTextSuaLoaiXa;
	
	Context context;
	String loaiXa[]={"Phường","Thị trấn","Xã"};
    Spinner spinnerSuaLoaiXa, spinnerSuaCapHuyen_id;
    ArrayAdapter<String> adapterSuaLoaiXa;
    
    //spinner tỉnh
    private ArrayList<Huyen> arrayHuyen;
    //DSTinhArrayAdapter_Gridview adapterSuaCapTinh_id;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sua_xa);
		
		
		//ket noi
		
		editTextSuaMaXa=(EditText)findViewById(R.id.editTextSuaMaXa);
		editTextSuaTenXa=(EditText)findViewById(R.id.editTextSuaTenXa);
		spinnerSuaLoaiXa=(Spinner)findViewById(R.id.spinnerSuaLoaiXa);
		spinnerSuaCapHuyen_id=(Spinner)findViewById(R.id.spinnerSuaCapHuyen_id);

		
		buttonHuy=(Button)findViewById(R.id.buttonHuy);
		
		final ArrayAdapter<String>adapterSuaLoaiXa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,loaiXa);
		adapterSuaLoaiXa.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerSuaLoaiXa.setAdapter(adapterSuaLoaiXa);
		
		//tỉnh
		arrayHuyen = new ArrayList<Huyen>();
		
		
        intent=getIntent();
		Bundle b=intent.getBundleExtra("DATA");
		xa=(Xa)b.getSerializable("capphuongxa");
		editTextSuaMaXa.setEnabled(false);
        editTextSuaMaXa.setText(xa.getIdXa()+"");
        editTextSuaTenXa.setText(xa.getTenXa()+"");
        new JSONParserAsyncTaskHuyen().execute();
        //spinnerSuaCapTinh_id.setText(huyen.getCaptinh_id()+"");
        String loai=xa.getLoai();
		 //if(huyen.getCaptinh_id()==spinnerSuaLoaiHuyen.getSelectedItem())
		if("Phường".equals(loai)){
			spinnerSuaLoaiXa.setSelection(adapterSuaLoaiXa.getPosition("Phường"));
		}
		else if("Thị trấn".equals(loai))
		{
			spinnerSuaLoaiXa.setSelection(adapterSuaLoaiXa.getPosition("Thị trấn"));
		}
		else if("Xã".equals(loai))
		{
			spinnerSuaLoaiXa.setSelection(adapterSuaLoaiXa.getPosition("Xã"));
		}
		
        
        //spinnerSuaLoaiTinh.setSelected(tinh.getLoai()+"");
		
		 //spinner tỉnh kiểm tra
		
        
		
		
		buttonCapNhat=(Button)findViewById(R.id.buttonXaCapNhat);
		
		buttonHuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//((ThemHuyenActivity) arg0.getContext()).finish();
				Intent themXa=new Intent(SuaXaActivity.this,QLCapXaActivity.class);
				startActivity(themXa);
			}
		});
		
		//them mới
		buttonCapNhat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(editTextSuaMaXa.getText().toString().trim().equals(""))
					Toast.makeText(getApplicationContext(),
							"Vui lòng nhập mã xã", Toast.LENGTH_LONG)
							.show();
		        else if(editTextSuaTenXa.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập tên xã", Toast.LENGTH_LONG)
							.show();
		        else if(spinnerSuaLoaiXa.getSelectedItem()==null)
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng chọn cấp", Toast.LENGTH_LONG)
							.show();
		        
		        else
		        {
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "capnhatphuongxa"));
				params.add(new BasicNameValuePair("id", editTextSuaMaXa.getText()+""));
				params.add(new BasicNameValuePair("tenphuongxa", editTextSuaTenXa.getText()+""));
				params.add(new BasicNameValuePair("loai", spinnerSuaLoaiXa.getSelectedItem().toString()+""));
				params.add(new BasicNameValuePair("caphuyen_id",((Huyen)spinnerSuaCapHuyen_id.getSelectedItem()).getIdHuyen().toString()+""));
				
				
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Cập nhật xã thành công", Toast.LENGTH_LONG)
									.show();
							
		                   
							//arrayAdapterTinh.notifyDataSetChanged();
							xa.setIdXa(editTextSuaMaXa.getText().toString());
							xa.setTenXa(editTextSuaTenXa.getText().toString());
							xa.setLoai(spinnerSuaLoaiXa.getSelectedItem().toString());
							xa.setCaphuyen_id(((Huyen)spinnerSuaCapHuyen_id.getSelectedItem()).getIdHuyen()+"");
							//System.out.println(((Xa)spinnerSuaCapHuyen_id.getSelectedItem()).getIdXa());
							//System.out.println(spinnerSuaLoaiXa.getSelectedItem().toString());
							
							((SuaXaActivity) arg0.getContext()).finish();
							Intent dsXa=new Intent(SuaXaActivity.this,QLCapXaActivity.class);
							startActivity(dsXa);
		                    
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
		getMenuInflater().inflate(R.menu.sua_xa, menu);
		return true;
	}
	private class JSONParserAsyncTaskHuyen extends
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
					
					ArrayAdapter<Huyen> adapterSuaCapHuyen_id=new ArrayAdapter<Huyen>(SuaXaActivity.this, android.R.layout.simple_spinner_item,arrayHuyen);
					adapterSuaCapHuyen_id.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
					spinnerSuaCapHuyen_id.setAdapter(adapterSuaCapHuyen_id);
				
				}
			} else {
	
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}

}

}
