package com.buddhaking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "lien_he")
public class LienHe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Integer id;
	
	@Column(name="created_at", nullable = false, updatable = false)
	private LocalDateTime ngayGio;
	
	@Column(name="ho_ten", nullable = false)
	private String hoTen;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="so_dt", nullable = false)
	private String soDienThoai;
	
	@Column(name="loi_nhan", nullable = false)
	private String loiNhan;
	
	@PrePersist
	public void prePersist() {
		this.ngayGio = LocalDateTime.now();
	}

	public LienHe(Integer id, LocalDateTime ngayGio, String hoTen, String email, String soDienThoai, String loiNhan) {
		super();
		this.id = id;
		this.ngayGio = ngayGio;
		this.hoTen = hoTen;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.loiNhan = loiNhan;
	}

	public LienHe() {
		super();
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

	public String getLoiNhan() {
		return loiNhan;
	}

	public void setLoiNhan(String loiNhan) {
		this.loiNhan = loiNhan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getNgayGio() {
		return ngayGio;
	}

	public void setNgayGio(LocalDateTime ngayGio) {
		this.ngayGio = ngayGio;
	}
	
}
