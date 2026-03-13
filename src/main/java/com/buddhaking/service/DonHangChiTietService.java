package com.buddhaking.service;

import java.util.List;
import java.util.Optional;

import com.buddhaking.entity.DonHangChiTiet;

public interface DonHangChiTietService {
	
	List<DonHangChiTiet> findAll();
	
	DonHangChiTiet save(DonHangChiTiet dhct);
	
	Optional<DonHangChiTiet> findById(Long id);
	
	boolean existById(Long id);
	
	void deleteById(Long id);
	
}
