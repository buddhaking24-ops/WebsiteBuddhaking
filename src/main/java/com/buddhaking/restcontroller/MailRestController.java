package com.buddhaking.restcontroller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddhaking.entity.LienHe;
import com.buddhaking.entity.MailModel;
import com.buddhaking.service.LienHeService;
import com.buddhaking.service.MailerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Mail")

public class MailRestController {

	@Autowired
	MailerService mailService;
	
	@Autowired
	LienHeService lhService;
	
	@PostMapping()
	public boolean restSendMail(MailModel mail, @RequestBody LienHe lh) {
		String hoTen = lh.getHoTen();
		String email = lh.getEmail();
		String soDt = lh.getSoDienThoai();
		String loiNhan = lh.getLoiNhan();
		
		LienHe lhSave = new LienHe();
		
		lhSave.setEmail(email);
		lhSave.setHoTen(hoTen);
		lhSave.setLoiNhan(loiNhan);
		lhSave.setNgayGio(LocalDateTime.now());
		lhSave.setSoDienThoai(soDt);
		
		lhService.save(lhSave);
		
		//Cấu trúc mail khí gửi
		String subject = "🌿 Thảo Mộc Hương Nhang Buddha King đã nhận được liên hệ của bạn";
		
		String body =
				"<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"<meta charset='UTF-8'>" +
				"<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +

				"<style>" +
				"@media only screen and (max-width:600px) {" +
				" .container { width:100% !important; }" +
				" .padding-mobile { padding:20px !important; }" +
				" .title { font-size:22px !important; }" +
				"}" +
				"</style>" +

				"</head>" +

				"<body style='margin:0;padding:0;background:#f4f6f8;'>"+

				"<table width='100%' cellpadding='0' cellspacing='0' style='background:#f4f6f8;'>"+
				"<tr><td align='center'>"+

				"<table class='container' width='600' cellpadding='0' cellspacing='0' "+
				"style='width:600px;max-width:600px;background:#ffffff;border-radius:12px;overflow:hidden;'>"+

				/* HEADER */
				"<tr>"+
				"<td style='background:linear-gradient(135deg,#8B5E3C,#C4A484);"+
				"color:#ffffff;padding:30px;text-align:center;'>"+

				"<div class='title' style='font-size:26px;font-weight:bold;'>"+
				"Buddha King - Hương Nhang Thảo Mộc"+
				"</div>"+

				"<div style='font-size:14px;margin-top:6px;opacity:0.9;'>"+
				"Tinh Hoa Hương Việt"+
				"</div>"+

				"</td>"+
				"</tr>"+

				/* CONTENT */
				"<tr>"+
				"<td class='padding-mobile' style='padding:40px 35px;font-family:Arial,sans-serif;color:#333;'>"+

				"<h2 style='margin-top:0;color:#8B5E3C;'>Xin chào "+ hoTen +"</h2>"+

				"<p style='line-height:1.6;'>"+
				"Cảm ơn bạn đã liên hệ với <b>Buddha King</b>."+
				"</p>"+

				"<p style='line-height:1.6;'>Chúng tôi đã nhận được lời nhắn của bạn:</p>"+

				"<table width='100%' cellpadding='0' cellspacing='0'>"+
				"<tr>"+
				"<td style='background:#f7f3ef;border-left:4px solid #8B5E3C;"+
				"padding:15px;border-radius:6px;'>"+
				loiNhan +
				"</td>"+
				"</tr>"+
				"</table>"+

				"<p style='margin-top:25px;line-height:1.6;'>"+
				"Đội ngũ của chúng tôi sẽ phản hồi sớm nhất có thể."+
				"</p>"+

				"<p style='margin-top:30px;'>"+
				"Trân trọng,<br>"+
				"<b>Buddha King - Hương Nhang Thảo Mộc</b>"+
				"</p>"+

				"</td>"+
				"</tr>"+

				/* FOOTER */
				"<tr>"+
				"<td style='background:#fafafa;padding:20px;text-align:center;"+
				"font-size:12px;color:#777;font-family:Arial,sans-serif;'>"+
				"© 2026 Buddha King - Hương Nhang Thảo Mộc"+
				"</td>"+
				"</tr>"+

				"</table>"+

				"</td></tr></table>"+

				"</body></html>";
		
		mailService.push(email, subject, body);
		return true;
	}
	
}
