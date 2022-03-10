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

public class TimHuyenArrayAdapter_listview extends ArrayAdapter<Huyen> {
	ArrayList<Huyen> array;
	 int resource;
	 
	 TextView tvMa;
	 TextView tvTenHuyen;
	 TextView tvLoai;
	 //ImageView ivHinh;
	 
	 Activity context;
	 Huyen huyen;
	 //ImageLoader imageLoader;

	 public TimHuyenArrayAdapter_listview(Activity context,ArrayList<Huyen> array) {
		 super(context, R.layout.timhuyen_item,array);
		 // TODO Auto-generated constructor stub
		 
		 this.context = context;
		 //imageLoader = new ImageLoader(this.context);
		 this.array = array;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent){
		
		 LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.timhuyen_item, null);
		 
		 
		 huyen = array.get(position);
		 if(huyen !=null){
			
			 tvTenHuyen = (TextView) row.findViewById(R.id.textViewTenTimHuyen);
			 tvMa = (TextView) row.findViewById(R.id.textViewMaTimHuyen);
			 tvLoai = (TextView) row.findViewById(R.id.textViewLoaiTimHuyen);
			 //ivHinh = (ImageView) row.findViewById(R.id.imageViewHinhTimSach);
			 
			
			 tvTenHuyen.setText(huyen.getTenHuyen());
			 tvMa.setText(huyen.getIdHuyen());
			 tvLoai.setText(huyen.getLoai());
			 
		 }
		 return row;
	 }	
}
