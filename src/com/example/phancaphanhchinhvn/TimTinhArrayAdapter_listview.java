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

public class TimTinhArrayAdapter_listview extends ArrayAdapter<Tinh> {
	ArrayList<Tinh> array;
	 int resource;
	 
	 TextView tvMa;
	 TextView tvTenTinh;
	 TextView tvLoai;
	 //ImageView ivHinh;
	 
	 Activity context;
	 Tinh tinh;
	 //ImageLoader imageLoader;

	 public TimTinhArrayAdapter_listview(Activity context,ArrayList<Tinh> array) {
		 super(context, R.layout.timtinh_item,array);
		 // TODO Auto-generated constructor stub
		 
		 this.context = context;
		 //imageLoader = new ImageLoader(this.context);
		 this.array = array;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent){
		
		 LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.timtinh_item, null);
		 
		 
		 tinh = array.get(position);
		 if(tinh !=null){
			
			 tvTenTinh = (TextView) row.findViewById(R.id.textViewTenTimTinh);
			 tvMa = (TextView) row.findViewById(R.id.textViewMaTimTinh);
			 tvLoai = (TextView) row.findViewById(R.id.textViewLoaiTimTinh);
			 //ivHinh = (ImageView) row.findViewById(R.id.imageViewHinhTimSach);
			 
			
			 tvTenTinh.setText(tinh.getTenTinh());
			 tvMa.setText(tinh.getIdTinh());
			 tvLoai.setText(tinh.getLoai());
			 
		 }
		 return row;
	 }	
}
