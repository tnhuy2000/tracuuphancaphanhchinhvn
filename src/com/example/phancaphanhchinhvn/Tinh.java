package com.example.phancaphanhchinhvn;

import java.io.Serializable;

public class Tinh implements Serializable{
	private String idTinh;
	private String tenTinh;
	private String loai;
	private String vung;
	private String dientich;
	private int danso;
	private String mota;
	public Tinh(String idTinh, String tenTinh, String loai, String vung,
			String dientich, int danso, String mota) {
		super();
		this.idTinh = idTinh;
		this.tenTinh = tenTinh;
		this.loai = loai;
		this.vung = vung;
		this.dientich = dientich;
		this.danso = danso;
		this.mota = mota;
	}
	public String getIdTinh() {
		return idTinh;
	}
	public void setIdTinh(String idTinh) {
		this.idTinh = idTinh;
	}
	public String getTenTinh() {
		return tenTinh;
	}
	public void setTenTinh(String tenTinh) {
		this.tenTinh = tenTinh;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public String getVung() {
		return vung;
	}
	public void setVung(String vung) {
		this.vung = vung;
	}
	public String getDientich() {
		return dientich;
	}
	public void setDientich(String dientich) {
		this.dientich = dientich;
	}
	
	public int getDanso() {
		return danso;
	}
	public void setDanso(int danso) {
		this.danso = danso;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	@Override
	
	public String toString() {
		return tenTinh;
	}
	
}
