package com.buddhaking.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.buddhaking.enu.TrangThaiNguoiDung;
import com.buddhaking.enu.TrangThaiSanPham;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {

	@Id
    @Column(name = "ma_nguoi_dung", nullable = false, length = 100)
    private String maNguoiDung;

    @Column(name = "mat_khau", nullable = false)
    private String matKhau;

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "so_dt", nullable = false, unique = true)
    private String soDienThoai;

    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TrangThaiNguoiDung trangThai;
    
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PhanQuyen> phanQuyens = new ArrayList<>();
    
    @OneToMany(mappedBy = "nguoiDung")
    @JsonIgnore
    private List<DonHang> donHangs = new ArrayList<>();
    
    @PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public String getMaNguoiDung() {
		return maNguoiDung;
	}

	public void setMaNguoiDung(String maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
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

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public TrangThaiNguoiDung getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiNguoiDung trangThai) {
		this.trangThai = trangThai;
	}

	public List<PhanQuyen> getPhanQuyens() {
		return phanQuyens;
	}

	public void setPhanQuyens(List<PhanQuyen> phanQuyens) {
		this.phanQuyens = phanQuyens;
	}

	public List<DonHang> getDonHangs() {
		return donHangs;
	}

	public void setDonHangs(List<DonHang> donHangs) {
		this.donHangs = donHangs;
	}

	public NguoiDung() {
		super();
	}
	
	
}
