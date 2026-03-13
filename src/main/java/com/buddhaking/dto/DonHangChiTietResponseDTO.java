package com.buddhaking.dto;

import java.math.BigDecimal;

public class DonHangChiTietResponseDTO {

	private String tenSp;
	private Integer soLuongMua;
	private BigDecimal donGia;
	
	public String getTenSp() {
		return tenSp;
	}
	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
	public Integer getSoLuongMua() {
		return soLuongMua;
	}
	public void setSoLuongMua(Integer soLuongMua) {
		this.soLuongMua = soLuongMua;
	}
	public BigDecimal getDonGia() {
		return donGia;
	}
	public void setDonGia(BigDecimal donGia) {
		this.donGia = donGia;
	}
	public DonHangChiTietResponseDTO(String tenSp, Integer soLuongMua, BigDecimal donGia) {
		super();
		this.tenSp = tenSp;
		this.soLuongMua = soLuongMua;
		this.donGia = donGia;
	}
	public DonHangChiTietResponseDTO() {
		super();
	}
		
}
