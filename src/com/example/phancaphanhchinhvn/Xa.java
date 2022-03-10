package com.example.phancaphanhchinhvn;

import java.io.Serializable;

public class Xa implements Serializable{
	private String idXa;
	private String tenXa;
	private String loai;
	private String caphuyen_id;
	private String tenhuyen;
	private String captinh_id;
	private String tentinh;
	public Xa(String idXa, String tenXa, String loai, String caphuyen_id,
			String tenhuyen, String captinh_id, String tentinh) {
		super();
		this.idXa = idXa;
		this.tenXa = tenXa;
		this.loai = loai;
		this.caphuyen_id = caphuyen_id;
		this.tenhuyen = tenhuyen;
		this.captinh_id = captinh_id;
		this.tentinh = tentinh;
	}
	public String getIdXa() {
		return idXa;
	}
	public void setIdXa(String idXa) {
		this.idXa = idXa;
	}
	public String getTenXa() {
		return tenXa;
	}
	public void setTenXa(String tenXa) {
		this.tenXa = tenXa;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public String getCaphuyen_id() {
		return caphuyen_id;
	}
	public void setCaphuyen_id(String caphuyen_id) {
		this.caphuyen_id = caphuyen_id;
	}
	public String getTenhuyen() {
		return tenhuyen;
	}
	public void setTenhuyen(String tenhuyen) {
		this.tenhuyen = tenhuyen;
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
	
}
