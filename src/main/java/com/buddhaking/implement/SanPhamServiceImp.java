package com.buddhaking.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddhaking.dao.SanPhamDAO;
import com.buddhaking.entity.SanPham;
import com.buddhaking.service.SanPhamService;

@Service
public class SanPhamServiceImp implements SanPhamService{

	@Autowired
	SanPhamDAO spDao;
	
	@Override
	public List<SanPham> findAll() {
		return spDao.findAll();
	}

	@Override
	public Optional<SanPham> findById(String id) {
		return spDao.findById(id);
	}

	@Override
	public SanPham save(SanPham sp) {
		return spDao.save(sp);
	}

	@Override
	public boolean existById(String id) {
		return spDao.existsById(id);
	}

	@Override
	public void deleteById(String id) {
		spDao.deleteById(id);
	}

}
