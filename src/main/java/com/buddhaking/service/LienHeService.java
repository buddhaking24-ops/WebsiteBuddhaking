package com.buddhaking.service;

import java.util.List;
import java.util.Optional;

import com.buddhaking.entity.LienHe;

public interface LienHeService {
	
	public List<LienHe> findAll();
	
	public Optional<LienHe> findById(Integer id);
	
	public LienHe save(LienHe lienHe);
	
	public boolean existById(Integer id);
	
	public void deleteById(Integer id);
	
}
