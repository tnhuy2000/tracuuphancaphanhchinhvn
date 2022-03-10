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

public class SuaHuyenActivity extends Activity {
	Button buttonCapNhat,buttonHuy;
	Huyen huyen;
    Intent intent;
	EditText editTextSuaMaHuyen,editTextSuaTenHuyen,editTextSuaLoaiHuyen;
	
	Context context;
	String loaihuyen[]={"Thành phố","Quận","Thị xã","Huyện"};
    Spinner spinnerSuaLoaiHuyen, spinnerSuaCapTinh_id;
    ArrayAdapter<String> adapterSuaLoaiHuyen;
    
    //spinner tỉnh
    private ArrayList<Tinh> arrayTinh;
    //DSTinhArrayAdapter_Gridview adapterSuaCapTinh_id;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sua_huyen);
		
		
		//ket noi
		
		editTextSuaMaHuyen=(EditText)findViewById(R.id.editTextSuaMaHuyen);
		editTextSuaTenHuyen=(EditText)findViewById(R.id.editTextSuaTenHuyen);
		spinnerSuaLoaiHuyen=(Spinner)findViewById(R.id.spinnerSuaLoaiHuyen);
		spinnerSuaCapTinh_id=(Spinner)findViewById(R.id.spinnerSuaCapTinh_id);

		
		buttonHuy=(Button)findViewById(R.id.buttonHuy);
		
		final ArrayAdapter<String>adapterSuaLoaiHuyen=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,loaihuyen);
		adapterSuaLoaiHuyen.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerSuaLoaiHuyen.setAdapter(adapterSuaLoaiHuyen);
		
		//tỉnh
		arrayTinh = new ArrayList<Tinh>();
		
		
        intent=getIntent();
		Bundle b=intent.getBundleExtra("DATA");
		huyen=(Huyen)b.getSerializable("caphuyen");
		editTextSuaMaHuyen.setEnabled(false);
        editTextSuaMaHuyen.setText(huyen.getIdHuyen()+"");
        editTextSuaTenHuyen.setText(huyen.getTenHuyen()+"");
        new JSONParserAsyncTaskTinh().execute();
        //spinnerSuaCapTinh_id.setText(huyen.getCaptinh_id()+"");
        String loai=huyen.getLoai();
		 //if(huyen.getCaptinh_id()==spinnerSuaLoaiHuyen.getSelectedItem())
		if("Thành phố".equals(loai)){
			spinnerSuaLoaiHuyen.setSelection(adapterSuaLoaiHuyen.getPosition("Thành phố"));
		}
		else if("Quận".equals(loai))
		{
			spinnerSuaLoaiHuyen.setSelection(adapterSuaLoaiHuyen.getPosition("Quận"));
		}
		else if("Thị xã".equals(loai))
		{
			spinnerSuaLoaiHuyen.setSelection(adapterSuaLoaiHuyen.getPosition("Thị xã"));
		}
		else
		{
			spinnerSuaLoaiHuyen.setSelection(adapterSuaLoaiHuyen.getPosition("Huyện"));
		}
        
        //spinnerSuaLoaiTinh.setSelected(tinh.getLoai()+"");
		
		 //spinner tỉnh kiểm tra
		
        
		
		
		buttonCapNhat=(Button)findViewById(R.id.buttonHuyenCapNhat);
		
		buttonHuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//((ThemHuyenActivity) arg0.getContext()).finish();
				Intent themHuyen=new Intent(SuaHuyenActivity.this,QLCapHuyenActivity.class);
				startActivity(themHuyen);
			}
		});
		
		//them mới
		buttonCapNhat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(editTextSuaMaHuyen.getText().toString().trim().equals(""))
					Toast.makeText(getApplicationContext(),
							"Vui lòng nhập mã huyện", Toast.LENGTH_LONG)
							.show();
		        else if(editTextSuaTenHuyen.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập tên huyện", Toast.LENGTH_LONG)
							.show();
		        else if(spinnerSuaLoaiHuyen.getSelectedItem()==null)
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng chọn cấp", Toast.LENGTH_LONG)
							.show();
		        
		        else
		        {
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "capnhathuyen"));
				params.add(new BasicNameValuePair("id", editTextSuaMaHuyen.getText()+""));
				params.add(new BasicNameValuePair("tenhuyen", editTextSuaTenHuyen.getText()+""));
				params.add(new BasicNameValuePair("loai", spinnerSuaLoaiHuyen.getSelectedItem().toString()+""));
				params.add(new BasicNameValuePair("captinh_id",((Tinh)spinnerSuaCapTinh_id.getSelectedItem()).getIdTinh().toString()+""));
				
				
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Cập nhật huyện thành công", Toast.LENGTH_LONG)
									.show();
							
		                   
							//arrayAdapterTinh.notifyDataSetChanged();
							huyen.setIdHuyen(editTextSuaMaHuyen.getText().toString());
							huyen.setTenHuyen(editTextSuaTenHuyen.getText().toString());
							huyen.setLoai(spinnerSuaLoaiHuyen.getSelectedItem().toString());
							huyen.setCaptinh_id(((Tinh)spinnerSuaCapTinh_id.getSelectedItem()).getIdTinh()+"");
							System.out.println(((Tinh)spinnerSuaCapTinh_id.getSelectedItem()).getIdTinh());
							System.out.println(spinnerSuaLoaiHuyen.getSelectedItem().toString());
							
							((SuaHuyenActivity) arg0.getContext()).finish();
							Intent dshuyen=new Intent(SuaHuyenActivity.this,QLCapHuyenActivity.class);
							startActivity(dshuyen);
		                    
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
		getMenuInflater().inflate(R.menu.sua_huyen, menu);
		return true;
	}
	private class JSONParserAsyncTaskTinh extends
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
					
					ArrayAdapter<Tinh> adapterSuaCapTinh_id=new ArrayAdapter<Tinh>(SuaHuyenActivity.this, android.R.layout.simple_spinner_item,arrayTinh);
					adapterSuaCapTinh_id.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
					spinnerSuaCapTinh_id.setAdapter(adapterSuaCapTinh_id);
				
					
				}
			} else {
	
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}

}

}
