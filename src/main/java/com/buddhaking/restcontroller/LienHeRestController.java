package com.buddhaking.restcontroller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddhaking.entity.LienHe;
import com.buddhaking.service.LienHeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/LienHe")
public class LienHeRestController {
	
	@Autowired
	LienHeService lhService;
	
	@GetMapping
	public ResponseEntity<Collection<LienHe>> getAllLh(){
		List<LienHe> listLh = lhService.findAll();
		if(listLh.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listLh);
	}
	
	@GetMapping("{x}")
	public ResponseEntity<LienHe> getLhById(@PathVariable("x") int id){
		Optional<LienHe> lh = lhService.findById(id);
		if(lh.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lh.get());
	}
	
	@PostMapping
	public ResponseEntity<LienHe> createNewLh(@RequestBody LienHe lienHe){
		LienHe lh = new LienHe();
		lh.setEmail(lienHe.getEmail());
		lh.setHoTen(lienHe.getHoTen());
		lh.setLoiNhan(lienHe.getLoiNhan());
		lh.setNgayGio(LocalDateTime.now());
		lh.setSoDienThoai(lienHe.getSoDienThoai());
		lhService.save(lh);
		return ResponseEntity.ok(lh);
	}
	
}
