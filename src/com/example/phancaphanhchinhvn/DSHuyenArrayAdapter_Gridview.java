package com.example.phancaphanhchinhvn;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.phancaphanhchinhvn.R;
import com.example.phancaphanhchinhvn.Huyen;


public class DSHuyenArrayAdapter_Gridview extends ArrayAdapter<Huyen>{
	ArrayList<Huyen> array;
	 int resource;
	 
	 TextView tvMaHuyen;
	 TextView tvLoai;
	 TextView tvTenHuyen;
	 
	 
	 Activity context;
	 Huyen huyen;
	 //ImageLoader imageLoader;

	 public DSHuyenArrayAdapter_Gridview(Activity context,ArrayList<Huyen> array) {
		 super(context, R.layout.huyen_item,array);
		 // TODO Auto-generated constructor stub
		 
		 this.context = context;
		 //imageLoader = new ImageLoader(this.context);
		 this.array = array;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent){
		
		 LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.huyen_item, null);
		 
		 
		 huyen = array.get(position);
		 if(huyen !=null){
			 tvMaHuyen = (TextView) row.findViewById(R.id.textViewMaHuyen);
			 tvTenHuyen = (TextView) row.findViewById(R.id.textViewTenHuyen);
			 tvLoai = (TextView) row.findViewById(R.id.textViewLoai);
			 
			 //ivHinh = (ImageView) row.findViewById(R.id.imageViewHinh);
			 
			 tvMaHuyen.setText(huyen.getIdHuyen());
			 tvTenHuyen.setText(huyen.getTenHuyen());
			 tvLoai.setText(huyen.getLoai());
			 
			 //imageLoader.DisplayImage("http://10.0.2.2:80/bookonline/img/"+sach.getHinh(), ivHinh);
		
			 
		 }
		 return row;
	 }
}
