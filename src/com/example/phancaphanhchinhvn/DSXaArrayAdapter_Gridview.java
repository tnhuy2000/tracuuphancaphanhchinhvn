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


public class DSXaArrayAdapter_Gridview extends ArrayAdapter<Xa>{
	ArrayList<Xa> array;
	 int resource;
	 
	 TextView tvMaXa;
	 TextView tvLoai;
	 TextView tvTenXa;
	 //ImageView ivHinh;
	 
	 Activity context;
	 Xa xa;
	 //ImageLoader imageLoader;

	 public DSXaArrayAdapter_Gridview(Activity context,ArrayList<Xa> array) {
		 super(context, R.layout.xa_item,array);
		 // TODO Auto-generated constructor stub
		 
		 this.context = context;
		 //imageLoader = new ImageLoader(this.context);
		 this.array = array;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent){
		
		 LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.xa_item, null);
		 
		 
		 xa = array.get(position);
		 if(xa !=null){
			 tvMaXa = (TextView) row.findViewById(R.id.textViewMaXa);
			 tvTenXa = (TextView) row.findViewById(R.id.textViewTenXa);
			 tvLoai = (TextView) row.findViewById(R.id.textViewLoai);
			 //ivHinh = (ImageView) row.findViewById(R.id.imageViewHinh);
			 
			 tvMaXa.setText(xa.getIdXa());
			 tvTenXa.setText(xa.getTenXa());
			 tvLoai.setText(xa.getLoai());
			 //imageLoader.DisplayImage("http://10.0.2.2:80/bookonline/img/"+sach.getHinh(), ivHinh);
		
			 
		 }
		 return row;
	 }
}
