package com.buddhaking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vai_tro")
public class VaiTro {

	@Id
    @Column(name = "ma_vai_tro", nullable = false, length = 10)
    private String maVaiTro;

    @Column(name = "ten_vai_tro", nullable = false)
    private String tenVaiTro;

	public String getMaVaiTro() {
		return maVaiTro;
	}

	public void setMaVaiTro(String maVaiTro) {
		this.maVaiTro = maVaiTro;
	}

	public String getTenVaiTro() {
		return tenVaiTro;
	}

	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
	}

	public VaiTro() {
		super();
	}
	
    
}
