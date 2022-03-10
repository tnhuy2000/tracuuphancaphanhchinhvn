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

public class TimXaArrayAdapter_listview extends ArrayAdapter<Xa> {
	ArrayList<Xa> array;
	 int resource;
	 
	 TextView tvMa;
	 TextView tvTenXa;
	 TextView tvLoai;
	 //ImageView ivHinh;
	 
	 Activity context;
	 Xa xa;
	 //ImageLoader imageLoader;

	 public TimXaArrayAdapter_listview(Activity context,ArrayList<Xa> array) {
		 super(context, R.layout.timxa_item,array);
		 // TODO Auto-generated constructor stub
		 
		 this.context = context;
		 //imageLoader = new ImageLoader(this.context);
		 this.array = array;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent){
		
		 LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.timxa_item, null);
		 
		 
		 xa = array.get(position);
		 if(xa !=null){
			
			 tvTenXa = (TextView) row.findViewById(R.id.textViewTenTimXa);
			 tvMa = (TextView) row.findViewById(R.id.textViewMaTimXa);
			 tvLoai = (TextView) row.findViewById(R.id.textViewLoaiTimXa);
			 //ivHinh = (ImageView) row.findViewById(R.id.imageViewHinhTimSach);
			 
			
			 tvTenXa.setText(xa.getTenXa());
			 tvMa.setText(xa.getIdXa());
			 tvLoai.setText(xa.getLoai());
			 
		 }
		 return row;
	 }	
}
