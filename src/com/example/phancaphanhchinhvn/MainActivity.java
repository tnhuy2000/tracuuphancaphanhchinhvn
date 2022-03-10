package com.example.phancaphanhchinhvn;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	//Button buttonXemTinh;
	
	
	private Button btnDangNhap;
	private View btnThoat;
	private ImageButton btnGioiThieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        btnDangNhap=(Button)findViewById(R.id.btnDangNhap);
        btnGioiThieu=(ImageButton)findViewById(R.id.btnGioiThieu);
        btnThoat=(Button)findViewById(R.id.btnThoat);
        
        btnDangNhap.setOnClickListener(this);
        
        btnThoat.setOnClickListener(this);
        btnGioiThieu.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@SuppressLint("NewApi") @Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==btnDangNhap)
		{
			Intent dangnhap=new Intent(this,DangNhapActivity.class);
			startActivity(dangnhap);
		}
		
		else if(arg0==btnGioiThieu)
		{
			Intent xemds=new Intent(this,GioiThieuActivity.class);
			startActivity(xemds);
		}
		else if(arg0==btnThoat){
			AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this,
					AlertDialog.THEME_HOLO_LIGHT);
			builder.setMessage("Are you sure you want to exit?");
			builder.setTitle("Question?");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					MainActivity.this.finish();
				}
			});
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			AlertDialog dialog=builder.create();
			dialog.show();
		}
	}


	
    
}
