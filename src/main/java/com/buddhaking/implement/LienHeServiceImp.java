package com.buddhaking.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddhaking.dao.LienHeDAO;
import com.buddhaking.entity.LienHe;
import com.buddhaking.service.LienHeService;

@Service
public class LienHeServiceImp implements LienHeService{

	@Autowired
	LienHeDAO lhDao;
	
	@Override
	public List<LienHe> findAll() {
		return lhDao.findAll();
	}

	@Override
	public Optional<LienHe> findById(Integer id) {
		return lhDao.findById(id);
	}

	@Override
	public LienHe save(LienHe lienHe) {
		return lhDao.save(lienHe);
	}

	@Override
	public boolean existById(Integer id) {
		return lhDao.existsById(id);
	}

	@Override
	public void deleteById(Integer id) {
		lhDao.deleteById(id);
	}

}
