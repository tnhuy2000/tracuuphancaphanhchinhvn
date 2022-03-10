package com.example.phancaphanhchinhvn;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.Bundle;
import android.app.Activity;
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

public class SuaTinhActivity extends Activity {
	Button buttonCapNhat,buttonHuy;
	Tinh tinh;
    Intent intent;
	EditText editTextSuaMaTinh,editTextSuaTenTinh,editTextSuaLoaiTinh,
	editTextSuaDienTichTinh,editTextSuaDanSoTinh,editTextSuaMoTaTinh;
	Context context;
	String loaitinh[]={"Thành phố Trung ương","Tỉnh"};
	String vung[]={"Đồng bằng sông Hồng",
			"Tây Bắc Bộ",
			"Bắc Trung Bộ",
			"Đồng bằng sông Cửu Long",
			"Đông Nam Bộ",
			"Đông Bắc Bộ",
			"Duyên hải Nam Trung Bộ"};
	
    Spinner spinnerSuaLoaiTinh,spinnerSuaVung;
    ArrayAdapter<String> adapterSuaLoaiTinh;
    ArrayAdapter<String> adapterSuaVung;
	private static String url = "http://10.0.2.2:80/phancaphanhchinhvn/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sua_tinh);
		
		
		//ket noi
		
		editTextSuaMaTinh=(EditText)findViewById(R.id.editTextSuaMaTinh);
		editTextSuaTenTinh=(EditText)findViewById(R.id.editTextSuaTenTinh);
		spinnerSuaLoaiTinh=(Spinner)findViewById(R.id.spinnerSuaLoaiTinh);
		spinnerSuaVung=(Spinner)findViewById(R.id.spinnerSuaVung);
		editTextSuaDienTichTinh=(EditText)findViewById(R.id.editTextSuaDienTichTinh);
		editTextSuaDanSoTinh=(EditText)findViewById(R.id.editTextSuaDanSoTinh);
		editTextSuaMoTaTinh=(EditText)findViewById(R.id.editTextSuaMoTaTinh);
		
		buttonHuy=(Button)findViewById(R.id.buttonHuy);
		
		final ArrayAdapter<String>adapterSuaLoaiTinh=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,loaitinh);
		adapterSuaLoaiTinh.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerSuaLoaiTinh.setAdapter(adapterSuaLoaiTinh);
		
		//vùng
		final ArrayAdapter<String>adapterSuaVung=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,vung);
		adapterSuaVung.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinnerSuaVung.setAdapter(adapterSuaVung);
		
        intent=getIntent();
		Bundle b=intent.getBundleExtra("DATA");
		tinh=(Tinh)b.getSerializable("captinh");
		editTextSuaMaTinh.setEnabled(false);
        editTextSuaMaTinh.setText(tinh.getIdTinh()+"");
        editTextSuaTenTinh.setText(tinh.getTenTinh()+"");
       
        
        
        //spinnerSuaLoaiTinh.setSelected(tinh.getLoai()+"");
		
		editTextSuaDienTichTinh.setText(tinh.getDientich()+"");
		editTextSuaDanSoTinh.setText(tinh.getDanso()+"");
		editTextSuaMoTaTinh.setText(tinh.getMota()+"");
		 
		String vung=tinh.getVung();
		if("Đồng bằng sông Hồng".equals(vung)){
			spinnerSuaVung.setSelection(adapterSuaVung.getPosition("Đồng bằng sông Hồng"));
			//editTextSuaMoTaTinh.setText("Tỉnh");
        }
        else if("Tây Bắc Bộ".equals(vung))
        {
        	
        //editTextSuaMoTaTinh.setText("Thành phố trung ương");
        	spinnerSuaVung.setSelection(adapterSuaVung.getPosition("Tây Bắc Bộ"));
        }
        else if("Bắc Trung Bộ".equals(vung))
        {
        	
        //editTextSuaMoTaTinh.setText("Thành phố trung ương");
        	spinnerSuaVung.setSelection(adapterSuaVung.getPosition("Bắc Trung Bộ"));
        }
        else if("Đồng bằng sông Cửu Long".equals(vung))
        {
        	
        //editTextSuaMoTaTinh.setText("Thành phố trung ương");
        	spinnerSuaVung.setSelection(adapterSuaVung.getPosition("Đồng bằng sông Cửu Long"));
        }
        else if("Đông Nam Bộ".equals(vung))
        {
        	
        //editTextSuaMoTaTinh.setText("Thành phố trung ương");
        	spinnerSuaVung.setSelection(adapterSuaVung.getPosition("Đông Nam Bộ"));
        }
        else if("Đông Bắc Bộ".equals(vung))
        {
        	
        //editTextSuaMoTaTinh.setText("Thành phố trung ương");
        	spinnerSuaVung.setSelection(adapterSuaVung.getPosition("Đông Bắc Bộ"));
        }
        else if("Duyên hải Nam Trung Bộ".equals(vung))
        {
        	
        //editTextSuaMoTaTinh.setText("Thành phố trung ương");
        	spinnerSuaVung.setSelection(adapterSuaVung.getPosition("Duyên hải Nam Trung Bộ"));
        }
		
		
		///loại tỉnh
		 String loai=tinh.getLoai();
		 
		 //System.out.println(loai);
		if("Tỉnh".equals(loai)){
        	spinnerSuaLoaiTinh.setSelection(adapterSuaLoaiTinh.getPosition("Tỉnh"));
			//editTextSuaMoTaTinh.setText("Tỉnh");
        }
        else
        {
        	
        //editTextSuaMoTaTinh.setText("Thành phố trung ương");
        	spinnerSuaLoaiTinh.setSelection(adapterSuaLoaiTinh.getPosition("Thành phố Trung ương"));
       }
        
		
		
		buttonCapNhat=(Button)findViewById(R.id.buttonTinhCapNhat);
		
		buttonHuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//((ThemTinhActivity) arg0.getContext()).finish();
				Intent themtinh=new Intent(SuaTinhActivity.this,QLCapTinhActivity.class);
				startActivity(themtinh);
			}
		});
		
		//them mới
		buttonCapNhat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(editTextSuaMaTinh.getText().toString().trim().equals(""))
					Toast.makeText(getApplicationContext(),
							"Vui lòng nhập mã tỉnh", Toast.LENGTH_LONG)
							.show();
		        else if(editTextSuaTenTinh.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập tên tỉnh", Toast.LENGTH_LONG)
							.show();
		        else if(spinnerSuaLoaiTinh.getSelectedItem()==null)
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng chọn cấp", Toast.LENGTH_LONG)
							.show();
		        else if(spinnerSuaVung.getSelectedItem()==null)
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng chọn vùng", Toast.LENGTH_LONG)
							.show();
		        else if(editTextSuaDienTichTinh.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập diện tích tỉnh", Toast.LENGTH_LONG)
							.show();
		        else if(editTextSuaDanSoTinh.getText().toString().trim().equals(""))
		        	Toast.makeText(getApplicationContext(),
							"Vui lòng nhập dân số tỉnh", Toast.LENGTH_LONG)
							.show();
		        else
		        {
				JSONParser jParser = new JSONParser();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tag", "capnhattinh"));
				
				params.add(new BasicNameValuePair("id", editTextSuaMaTinh.getText()+""));
				params.add(new BasicNameValuePair("tentinh", editTextSuaTenTinh.getText()+""));
				params.add(new BasicNameValuePair("loai", spinnerSuaLoaiTinh.getSelectedItem().toString()+""));
				params.add(new BasicNameValuePair("vung", spinnerSuaVung.getSelectedItem().toString()+""));
				params.add(new BasicNameValuePair("dientich", editTextSuaDienTichTinh.getText()+""));
				params.add(new BasicNameValuePair("danso", editTextSuaDanSoTinh.getText()+""));
				params.add(new BasicNameValuePair("mota", editTextSuaMoTaTinh.getText()+""));
				// Getting JSON from URL
				JSONObject json = jParser.getJSONFromUrl(url, params);
				try {
					if (json.getString("success") != null) {
						// loginErrorMsg.setText("");
						// String res = json.getString(KEY_SUCCESS);
						// if(Integer.parseInt(res) == 1){
						if (json.getString("success").equals("1")) {							
							Toast.makeText(getApplicationContext(),
									"Cập nhật tỉnh thành công", Toast.LENGTH_LONG)
									.show();
							
		                   
							//arrayAdapterTinh.notifyDataSetChanged();
							tinh.setIdTinh(editTextSuaMaTinh.getText().toString());
							tinh.setTenTinh(editTextSuaTenTinh.getText().toString());
							tinh.setLoai(spinnerSuaLoaiTinh.getSelectedItem().toString());
							tinh.setVung(spinnerSuaVung.getSelectedItem().toString());
							tinh.setDientich(editTextSuaDienTichTinh.getText().toString());
							int danso=Integer.parseInt(editTextSuaDanSoTinh.getText().toString()); 
							tinh.setDanso(danso);
							tinh.setMota(editTextSuaMoTaTinh.getText().toString());
							
							((SuaTinhActivity) arg0.getContext()).finish();
							Intent themtinh=new Intent(SuaTinhActivity.this,QLCapTinhActivity.class);
							startActivity(themtinh);
		                    
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
		getMenuInflater().inflate(R.menu.sua_tinh, menu);
		return true;
	}

}
