package com.buddhaking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TrangChuController {

	@GetMapping("")
	public String Home(Model model) {
		model.addAttribute("title","Hương nhang thảo mộc Buddha King – Nhang sạch tinh khiết");
		model.addAttribute("content","home");
		return "index";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("title","Thông tin liên hệ Buddha King – Hỗ trợ khách hàng");
		model.addAttribute("content","contact");
		return "index";
	}
	
	@GetMapping("/CNSH")
	public String CNSH(Model model) {
		model.addAttribute("title","Công nghệ sản xuất nhang sạch thảo mộc | Buddha King");
		model.addAttribute("content","CNSH");
		return "index";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title","Giới thiệu thương hiệu nhang thảo mộc | Buddha King");
		model.addAttribute("content","about");
		return "index";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		model.addAttribute("title","Đặt mua nhang thảo mộc chính hãng | Buddha King");
		model.addAttribute("content","cart");
		return "index";
	}
	
}
