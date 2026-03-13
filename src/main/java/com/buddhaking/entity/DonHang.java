package com.buddhaking.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.buddhaking.enu.TrangThaiDonHang;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "don_hang")
public class DonHang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_don_hang")
	private Long maDonHang;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ma_nd", nullable = true)
	private NguoiDung nguoiDung;

	@Column(name = "ho_ten", nullable = false)
	private String hoTen;

	@Column(name = "email")
	private String email;

	@Column(name = "so_dt", nullable = false)
	private String soDienThoai;

	@Column(name = "dia_chi_giao", nullable = false)
	private String diaChiGiao;

	@Enumerated(EnumType.STRING)
	@Column(name = "trang_thai", nullable = false)
	private TrangThaiDonHang trangThai;

	@Column(name = "tong_tien", nullable = false, precision = 15, scale = 2)
	private BigDecimal tongTien;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	@OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DonHangChiTiet> chiTietList = new ArrayList<>();
	
	@PrePersist
	public void prePersist() {
		this.createdAt = Instant.now();
		this.updatedAt = Instant.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = Instant.now();
	}
	
	public void addChiTiet(DonHangChiTiet ct) {
		this.chiTietList.add(ct);
		ct.setDonHang(this);
	}

	public Long getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(Long maDonHang) {
		this.maDonHang = maDonHang;
	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChiGiao() {
		return diaChiGiao;
	}

	public void setDiaChiGiao(String diaChiGiao) {
		this.diaChiGiao = diaChiGiao;
	}

	public TrangThaiDonHang getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiDonHang trangThai) {
		this.trangThai = trangThai;
	}

	public BigDecimal getTongTien() {
		return tongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<DonHangChiTiet> getChiTietList() {
		return chiTietList;
	}

	public void setChiTietList(List<DonHangChiTiet> chiTietList) {
		this.chiTietList = chiTietList;
	}

	public DonHang() {
		super();
	}
	
}
