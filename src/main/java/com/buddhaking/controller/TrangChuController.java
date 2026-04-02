package com.buddhaking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TrangChuController {

	@GetMapping("")
	public String Home(Model model) {
		model.addAttribute("title","Hương nhang thảo mộc Buddha King – Nhang sạch tinh khiết");
		 model.addAttribute("canonical", "https://buddhaking.vn/");
		model.addAttribute("description","Hương nhang thảo mộc Buddha King – nhang sạch tinh khiết từ nguyên liệu tự nhiên, an toàn cho sức khỏe và môi trường.");
		model.addAttribute("content","home");
		return "index";
	}
	
	@GetMapping("/lien-he")
	public String contact(Model model) {
		model.addAttribute("title","Liên hệ Buddha King | Thông tin, hotline, hỗ trợ khách hàng");
		model.addAttribute("canonical", "https://buddhaking.vn/lien-he");
		model.addAttribute("description",
			    "Liên hệ Buddha King để được tư vấn sản phẩm, hỗ trợ nhanh chóng qua hotline, email");
		model.addAttribute("content","contact");
		return "index";
	}
	
	@GetMapping("/blog/cong-he-sinh-hoc-nhang-buddhaking")
	public String CNSH(Model model) {
		model.addAttribute("title","Công nghệ sản xuất nhang sạch thảo mộc | Buddha King");
		 model.addAttribute("canonical", "https://buddhaking.vn/blog/cong-he-sinh-hoc-nhang-buddhaking");
		model.addAttribute("description","Công nghệ sản xuất nhang sạch thảo mộc Buddha King sử dụng quy trình “Thanh lọc sinh học” (Bioremediation) độc quyền, giúp loại bỏ tạp chất, giữ trọn tinh dầu gỗ quý và thảo mộc tự nhiên");
		model.addAttribute("content","CNSH");
		return "index";
	}
	
	@GetMapping("/blog/gioi-thieu")
	public ResponseEntity<Void> redirect0(Model model) {
		return ResponseEntity.status(301).header("Location", "/gioi-thieu").build();
	}
	
	@GetMapping("/gioi-thieu")
	public String about(Model model) {
		model.addAttribute("title","Giới thiệu thương hiệu nhang thảo mộc | Buddha King");
		model.addAttribute("canonical", "https://buddhaking.vn/gioi-thieu");
		model.addAttribute("description","Tìm hiểu về thương hiệu Buddha King – chuyên cung cấp nhang thảo mộc sạch, an toàn, nguồn gốc tự nhiên, mang đến không gian thanh tịnh và an lành.");
		model.addAttribute("content","about");
		return "index";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		model.addAttribute("title","Đặt mua nhang thảo mộc chính hãng | Buddha King");
		model.addAttribute("description","Đặt mua nhang thảo mộc Buddha King chính hãng nhanh chóng, tiện lợi. Sản phẩm tự nhiên, không hóa chất, giao hàng toàn quốc.");
		model.addAttribute("robots","noindex,nofollow");
		model.addAttribute("content","cart");
		return "index";
	}
	
}
