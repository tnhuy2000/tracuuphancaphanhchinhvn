package com.example.phancaphanhchinhvn;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TimNguoiDungArrayAdapter_listview extends ArrayAdapter<NguoiDung> {
	ArrayList<NguoiDung> array;
	 int resource;
	 
	 TextView tvhoten;
	 TextView tvloai;
	 TextView tvemail;
	 //ImageView ivHinh;
	 
	 Activity context;
	 NguoiDung nguoidung;
	 //ImageLoader imageLoader;

	 public TimNguoiDungArrayAdapter_listview(Activity context,ArrayList<NguoiDung> array) {
		 super(context, R.layout.timnguoidung_item,array);
		 // TODO Auto-generated constructor stub
		 
		 this.context = context;
		 //imageLoader = new ImageLoader(this.context);
		 this.array = array;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent){
		
		 LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.timnguoidung_item, null);
		 
		 
		 nguoidung = array.get(position);
		 if(nguoidung !=null){
			
			 tvhoten = (TextView) row.findViewById(R.id.textViewHotenTimND);
			 tvloai = (TextView) row.findViewById(R.id.textViewLoaiTimND);
			 tvemail = (TextView) row.findViewById(R.id.textViewEmailTimND);
			 //ivHinh = (ImageView) row.findViewById(R.id.imageViewHinhTimSach);
			 
			
			 tvhoten.setText(nguoidung.getHoten());
			 tvloai.setText("Quyền: "+nguoidung.getLoai());
			 tvemail.setText(nguoidung.getEmail());
			 
		 }
		 return row;
	 }	
}
