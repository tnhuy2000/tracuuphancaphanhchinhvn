package com.example.phancaphanhchinhvn;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View.OnClickListener;

@SuppressLint("NewApi") public class LopChucNang {
    
	public static void showMessage(Context c,String message, String title)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(c,AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

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
