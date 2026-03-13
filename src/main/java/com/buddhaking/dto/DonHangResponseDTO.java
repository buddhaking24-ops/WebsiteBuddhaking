package com.buddhaking.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import com.buddhaking.entity.DonHang;
import com.buddhaking.entity.DonHangChiTiet;
import com.buddhaking.enu.TrangThaiDonHang;

public class DonHangResponseDTO {
	
	private Long id;
	private Instant createAt;
	private Instant updateAt;
	private String hoTen;
	private String soDt;
	private String email;
	private BigDecimal tongTien;
	private TrangThaiDonHang trangThai;
	private List<DonHangChiTietResponseDTO> listChiTiet;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Instant createAt) {
		this.createAt = createAt;
	}
	public Instant getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Instant updateAt) {
		this.updateAt = updateAt;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getSoDt() {
		return soDt;
	}
	public void setSoDt(String soDt) {
		this.soDt = soDt;
	}
	public BigDecimal getTongTien() {
		return tongTien;
	}
	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}
	public TrangThaiDonHang getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThaiDonHang trangThai) {
		this.trangThai = trangThai;
	}
	public List<DonHangChiTietResponseDTO> getListChiTiet() {
		return listChiTiet;
	}
	public void setListChiTiet(List<DonHangChiTietResponseDTO> listChiTiet) {
		this.listChiTiet = listChiTiet;
	}
	
	public DonHangResponseDTO() {
		super();
	}
	
	public DonHangResponseDTO(Long id, Instant createAt, Instant updateAt, String hoTen, String soDt, String email,
			BigDecimal tongTien, TrangThaiDonHang trangThai, List<DonHangChiTietResponseDTO> listChiTiet) {
		super();
		this.id = id;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.hoTen = hoTen;
		this.soDt = soDt;
		this.email = email;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
		this.listChiTiet = listChiTiet;
	}
	
	
}
