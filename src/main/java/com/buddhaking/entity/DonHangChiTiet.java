package com.buddhaking.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="don_hang_chi_tiet",uniqueConstraints = @UniqueConstraint(columnNames = {"ma_sp","ma_dh"}))
public class DonHangChiTiet {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ma_sp", nullable = false)
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "ma_dh", nullable = false)
    private DonHang donHang;

    @Column(name = "ten_sp", nullable = false)
    private String tenSp;

    @Column(name = "so_luong_mua", nullable = false)
    private Integer soLuongMua;

    @Column(name = "don_gia", nullable = false, precision = 15, scale = 2)
    private BigDecimal donGia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

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

	public DonHangChiTiet() {
		super();
	}
    
    
	
}
