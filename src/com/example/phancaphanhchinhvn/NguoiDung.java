package com.example.phancaphanhchinhvn;

import java.io.Serializable;

public class NguoiDung implements Serializable{
	private int id;
	private String hoten;
	private String sodienthoai;
	private String email;
	private String loai;
	public NguoiDung(int id, String hoten, String sodienthoai, String email,
			String loai) {
		super();
		this.id = id;
		this.hoten = hoten;
		this.sodienthoai = sodienthoai;
		this.email = email;
		this.loai = loai;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	
	
}
