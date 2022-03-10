package com.example.phancaphanhchinhvn;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.phancaphanhchinhvn.R;

public class DSNguoiDungArrayAdapter_Gridview extends ArrayAdapter<NguoiDung>{
	ArrayList<NguoiDung> array;
	 int resource;
	 
	 TextView tvhoten;
	 TextView tvloai;
	 TextView tvemail;
	 //ImageView ivHinh;
	 
	 Activity context;
	 NguoiDung nguoidung;
	 //ImageLoader imageLoader;

	 public DSNguoiDungArrayAdapter_Gridview(Activity context,ArrayList<NguoiDung> array) {
		 super(context, R.layout.nguoidung_item,array);
		 // TODO Auto-generated constructor stub
		 
		 this.context = context;
		 //imageLoader = new ImageLoader(this.context);
		 this.array = array;
	 }
	 
	 public View getView(int position, View convertView, ViewGroup parent){
		
		 LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.nguoidung_item, null);
		 
		 
		 nguoidung = array.get(position);
		 if(nguoidung !=null){
			 tvhoten = (TextView) row.findViewById(R.id.textViewTenNguoiDung);
			 tvloai = (TextView) row.findViewById(R.id.textViewLoaiNguoiDung);
			 tvemail = (TextView) row.findViewById(R.id.textViewEmailNguoiDung);
			 //ivHinh = (ImageView) row.findViewById(R.id.imageViewHinh);
			 
			 tvhoten.setText(nguoidung.getHoten());
			 tvloai.setText("Quyền: "+nguoidung.getLoai());
			 tvemail.setText(nguoidung.getEmail());
			 //imageLoader.DisplayImage("http://10.0.2.2:80/bookonline/img/"+sach.getHinh(), ivHinh);
		
			 
		 }
		 return row;
	 }
}
