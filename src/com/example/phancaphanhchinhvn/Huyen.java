package com.example.phancaphanhchinhvn;

import java.io.Serializable;

public class Huyen implements Serializable{

	private String idHuyen;
	private String tenHuyen;
	private String loai;
	private String captinh_id;
	private String tentinh;
	
	
	public String getIdHuyen() {
		return idHuyen;
	}

	public void setIdHuyen(String idHuyen) {
		this.idHuyen = idHuyen;
	}

	public String getTenHuyen() {
		return tenHuyen;
	}

	public void setTenHuyen(String tenHuyen) {
		this.tenHuyen = tenHuyen;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public String getCaptinh_id() {
		return captinh_id;
	}

	public void setCaptinh_id(String captinh_id) {
		this.captinh_id = captinh_id;
	}

	public String getTentinh() {
		return tentinh;
	}

	public void setTentinh(String tentinh) {
		this.tentinh = tentinh;
	}

	

	public Huyen(String idHuyen, String tenHuyen, String loai,
			String captinh_id, String tentinh) {
		super();
		this.idHuyen = idHuyen;
		this.tenHuyen = tenHuyen;
		this.loai = loai;
		this.captinh_id = captinh_id;
		this.tentinh = tentinh;
		
	}

	public String toString() {
		return tenHuyen;
	}
	
}
