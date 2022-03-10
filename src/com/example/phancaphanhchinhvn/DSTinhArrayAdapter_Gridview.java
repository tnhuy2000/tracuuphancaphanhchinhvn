package com.example.phancaphanhchinhvn;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.phancaphanhchinhvn.R;
import com.example.phancaphanhchinhvn.Tinh;


public class DSTinhArrayAdapter_Gridview extends ArrayAdapter<Tinh>{
	ArrayList<Tinh> array;
	 int resource;
	 
	 TextView tvMaTinh;
	 TextView tvLoai;
	 TextView tvTenTinh;
	 //ImageView ivHinh;
	 
	 Activity context;
	 Tinh tinh;
	 //ImageLoader imageLoader;

	 public DSTinhArrayAdapter_Gridview(Activity context,ArrayList<Tinh> array) {
		 super(context, R.layout.tinh_item,array);
		 // TODO Auto-generated constructor stub
		 
		 this.context = context;
		 //imageLoader = new ImageLoader(this.context);
		 this.array = array;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent){
		
		 LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.tinh_item, null);
		 
		 
		 tinh = array.get(position);
		 if(tinh !=null){
			 tvMaTinh = (TextView) row.findViewById(R.id.textViewMaTinh);
			 tvTenTinh = (TextView) row.findViewById(R.id.textViewTenTinh);
			 tvLoai = (TextView) row.findViewById(R.id.textViewLoai);
			 //ivHinh = (ImageView) row.findViewById(R.id.imageViewHinh);
			 
			 tvMaTinh.setText(tinh.getIdTinh());
			 tvTenTinh.setText(tinh.getTenTinh());
			 tvLoai.setText(tinh.getLoai());
			 //imageLoader.DisplayImage("http://10.0.2.2:80/bookonline/img/"+sach.getHinh(), ivHinh);
		
			 
		 }
		 return row;
	 }
}
