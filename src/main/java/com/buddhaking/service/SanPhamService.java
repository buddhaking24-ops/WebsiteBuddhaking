package com.buddhaking.service;

import java.util.List;
import java.util.Optional;

import com.buddhaking.entity.SanPham;

public interface SanPhamService {

	List<SanPham> findAll();
	
	Optional<SanPham> findById(String id);
	
	SanPham save(SanPham sp);
	
	boolean existById(String id);
	
	void deleteById(String id);
	
}
