package com.buddhaking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping()
	public String manager(Model model) {
		model.addAttribute("contentAdmin","adminzz/page/ThongKe");
		return "adminzz/admin";
	}
	
	@GetMapping("/KhachPhanHoi")
	public String managerWarehouse(Model model) {
		model.addAttribute("contentAdmin","adminzz/page/KhachPhanHoi");
		return "adminzz/admin";
	}
	
	@GetMapping("/QuanLyDonHang")
	public String lichSuManager(Model model) {
		model.addAttribute("contentAdmin","adminzz/page/QuanLyDonHang");
		return "adminzz/admin";
	}
	
}
