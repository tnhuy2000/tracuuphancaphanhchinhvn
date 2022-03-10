package com.example.phancaphanhchinhvn;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class GioiThieuActivity extends Activity {
	WebView web;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gioi_thieu);
		web=(WebView)findViewById(R.id.webView1);
		
		//Bundle bundle=getIntent().getBundleExtra("data");
		//String url=bundle.getString("url");
		
		Uri htmlUri = Uri.fromFile(new File("//android_asset/"+"phancap.htm"));
		web.loadUrl(htmlUri.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gioi_thieu, menu);
		return true;
	}

}
