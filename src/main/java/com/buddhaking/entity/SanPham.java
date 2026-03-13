package com.buddhaking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.buddhaking.enu.TrangThaiSanPham;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="san_pham")
public class SanPham {

	@Id
    @Column(name = "ma_san_pham", nullable = false, length = 10)
    private String maSanPham;

    @Column(name = "ten_san_pham", nullable = false)
    private String tenSanPham;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "gia_tien", nullable = false, precision = 15, scale = 2)
    private BigDecimal giaTien;

    @Column(name = "mo_ta")
    private String moTa;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TrangThaiSanPham trangThai;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public BigDecimal getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(BigDecimal giaTien) {
		this.giaTien = giaTien;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public TrangThaiSanPham getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiSanPham trangThai) {
		this.trangThai = trangThai;
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

	public SanPham() {
		super();
	}
	
	
	
}
