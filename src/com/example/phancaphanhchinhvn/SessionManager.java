package com.example.phancaphanhchinhvn;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
	SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    @SuppressLint("NewApi") public void createSession(String name, String email, String phone){

        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(PHONE, phone);
        editor.apply();

    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if (!this.isLoggin()){
            Intent i = new Intent(context, DangNhapActivity.class);
            context.startActivity(i);
            ((QuanLyActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<String, String>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(PHONE, sharedPreferences.getString(PHONE, null));

        return user;
    }

    public void logoutQuanLy(){

    	
        editor.clear();
        editor.commit();
        ((QuanLyActivity) context).finish();
       
        Intent i = new Intent(context, DangNhapActivity.class);
        context.startActivity(i);
        

    }
    public void logoutNguoiDung(){

    	
        editor.clear();
        editor.commit();
        
        ((TrangChuActivity) context).finish();
        Intent i = new Intent(context, DangNhapActivity.class);
        context.startActivity(i);
        

    }
}
