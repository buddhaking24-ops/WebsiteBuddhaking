package com.buddhaking.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddhaking.dao.DonHangChiTietDAO;
import com.buddhaking.entity.DonHangChiTiet;
import com.buddhaking.service.DonHangChiTietService;

@Service
public class DonHangChiTietServiceImp implements DonHangChiTietService{

	@Autowired
	DonHangChiTietDAO dhctDAO;
	
	@Override
	public List<DonHangChiTiet> findAll() {
		return dhctDAO.findAll();
	}

	@Override
	public DonHangChiTiet save(DonHangChiTiet dhct) {
		return dhctDAO.save(dhct);
	}

	@Override
	public Optional<DonHangChiTiet> findById(Long id) {
		return dhctDAO.findById(id);
	}

	@Override
	public boolean existById(Long id) {
		return dhctDAO.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		dhctDAO.deleteById(id);
	}

}
