package com.buddhaking.service;

import java.util.List;
import java.util.Optional;

import com.buddhaking.dto.DonHangResponseDTO;
import com.buddhaking.dto.YeuCauDatHang;
import com.buddhaking.entity.DonHang;

public interface DonHangService {

	List<DonHang> findAll();
	
	DonHang save(DonHang dh);
	
	DonHang order(YeuCauDatHang ycdh);
	
	Optional<DonHang> findById(Long id);
	
	boolean existById(Long id);
	
	void deleteById(Long id);
	
}
