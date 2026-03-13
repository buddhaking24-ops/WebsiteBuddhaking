package com.buddhaking.restcontroller;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddhaking.dto.DonHangResponseDTO;
import com.buddhaking.dto.TrangThaiDonHangDTO;
import com.buddhaking.dto.YeuCauDatHang;
import com.buddhaking.entity.DonHang;
import com.buddhaking.entity.DonHangChiTiet;
import com.buddhaking.entity.NguoiDung;
import com.buddhaking.entity.SanPham;
import com.buddhaking.enu.TrangThaiDonHang;
import com.buddhaking.implement.DonHangChiTietServiceImp;
import com.buddhaking.mapper.DonHangMapper;
import com.buddhaking.service.DonHangChiTietService;
import com.buddhaking.service.DonHangService;
import com.buddhaking.service.MailerService;
import com.buddhaking.service.SanPhamService;
import com.buddhaking.util.MoneyUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/DonHang")
public class DonHangRestController {

	@Autowired
	DonHangService dhService;

	@Autowired
	DonHangChiTietService dhctService;

	@Autowired
	SanPhamService spService;

	@Autowired
	MailerService mailService;
	
	@Autowired
	DonHangMapper dhMapper;

	@GetMapping()
	public ResponseEntity<Collection<DonHangResponseDTO>> restGetAllDh(){
		
		List<DonHang> dhList = dhService.findAll();
		
		List<DonHangResponseDTO> responseList = dhList.stream().map(dhMapper::convertToDTO).toList();
		
		return ResponseEntity.ok(responseList);
	}
	
	@GetMapping("/TrangThai")
	public ResponseEntity<Collection<TrangThaiDonHangDTO>> restGetAllTT(){
		
		List<TrangThaiDonHangDTO> TrangThais = Arrays.stream(TrangThaiDonHang.values())
					.map(tt -> new TrangThaiDonHangDTO(
							tt.name(), tt.getDisplayName(), 
							tt.getColor(), 
							tt.getIcon())).toList();
		
		return ResponseEntity.ok(TrangThais);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DonHangResponseDTO> restGetDhById(@PathVariable Long id){
		
		Optional<DonHang> dhOptional = dhService.findById(id);
		
		if(dhOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		DonHangResponseDTO dhResponse = dhMapper.convertToDTO(dhOptional.get());
		
		return ResponseEntity.ok(dhResponse);
		
	}

	@PostMapping("/DatHang")
	public ResponseEntity<Boolean> restDatHang(@RequestBody YeuCauDatHang ycdh) {
		
		DonHang dh = dhService.order(ycdh);
		DonHangChiTiet dhct = dh.getChiTietList().get(0);
		
		//gửi mail
		String hoTen = ycdh.getHoTen();
		String email = ycdh.getEmail();
		String soDt = ycdh.getSoDienThoai();
		String loiNhan = "";

		// Cấu trúc mail khi gửi
		String subject = "🧾 Xác nhận đơn hàng từ Buddha King - Đơn của bạn ở trạng thái đang "+dh.getTrangThai().getDisplayName().toLowerCase();

		String body = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset='UTF-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "<style>"
				+ "@media only screen and (max-width:600px) {"
				+ " .container { width:100% !important; }"
				+ " .padding-mobile { padding:20px !important; }"
				+ " .title { font-size:22px !important; }"
				+ "}"
				+ "</style>"
				+ "</head>"

				+ "<body style='margin:0;padding:0;background:#f4f6f8;'>"

				+ "<table width='100%' cellpadding='0' cellspacing='0' style='background:#f4f6f8;'>"
				+ "<tr><td align='center'>"

				+ "<table class='container' width='600' cellpadding='0' cellspacing='0' "
				+ "style='width:600px;max-width:600px;background:#ffffff;border-radius:12px;overflow:hidden;'>"

				+ "<tr>"
				+ "<td style='background:linear-gradient(135deg,#8B5E3C,#C4A484);"
				+ "color:#ffffff;padding:30px;text-align:center;'>"
				+ "<div class='title' style='font-size:26px;font-weight:bold;'>"
				+ "Buddha King - Hương Nhang Thảo Mộc"
				+ "</div>"
				+ "<div style='font-size:14px;margin-top:6px;opacity:0.9;'>Tinh Hoa Hương Việt</div>"
				+ "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td class='padding-mobile' style='padding:40px 35px;font-family:Arial,sans-serif;color:#333;'>"

				+ "<h2 style='margin-top:0;color:#8B5E3C;'>Xin chào " + hoTen + ",</h2>"

				+ "<p style='line-height:1.6;'>"
				+ "Cảm ơn bạn đã tin tưởng và đặt hàng tại <b>Buddha King</b> 🌿."
				+ "</p>"

				+ "<p style='line-height:1.6;'>Đơn hàng của bạn đã được tiếp nhận với thông tin như sau:</p>"

				+ "<table width='100%' cellpadding='8' cellspacing='0' "
				+ "style='border-collapse:collapse;margin-top:15px;'>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Sản phẩm</b></td>"
				+ "<td style='border:1px solid #eee;'>" + dhct.getTenSp() + "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Số lượng</b></td>"
				+ "<td style='border:1px solid #eee;'>" + dhct.getSoLuongMua() + "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Đơn giá</b></td>"
				+ "<td style='border:1px solid #eee;'>" + MoneyUtils.format(dhct.getDonGia()) + " VNĐ</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Tổng tiền</b></td>"
				+ "<td style='border:1px solid #eee;color:#8B5E3C;font-weight:bold;'>"
				+ MoneyUtils.format(dh.getTongTien()) + " VNĐ</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Trạng thái</b></td>"
				+ "<td style='border:1px solid #eee;color:#d68910;font-weight:bold;'>"
				+ dh.getTrangThai().toHtml()
				+ "</td>"
				+ "</tr>"

				+ "</table>"

				+ "<p style='margin-top:25px;line-height:1.6;'>"
				+ "Đội ngũ Buddha King sẽ sớm liên hệ với bạn để xác nhận và tiến hành giao hàng."
				+ "</p>"

				+ "<p style='line-height:1.6;'>"
				+ "Nếu cần hỗ trợ thêm, vui lòng liên hệ lại qua email hoặc số điện thoại của chúng tôi."
				+ "</p>"

				+ "<p style='margin-top:30px;'>"
				+ "Trân trọng,<br>"
				+ "<b>Buddha King - Hương Nhang Thảo Mộc</b>"
				+ "</p>"

				+ "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#fafafa;padding:20px;text-align:center;"
				+ "font-size:12px;color:#777;font-family:Arial,sans-serif;'>"
				+ "© 2026 Buddha King - Hương Nhang Thảo Mộc | Tinh Hoa Hương Việt"
				+ "</td>"
				+ "</tr>"

				+ "</table>"
				+ "</td></tr></table>"
				+ "</body></html>";


		mailService.push(email, subject, body);
		
		return ResponseEntity.ok(true);
	}
	
	
	@PutMapping("/{maDh}/{trangThai}")
	public ResponseEntity<DonHangResponseDTO> restUpdateDh(@PathVariable("maDh") Long maDh ,@PathVariable("trangThai") String tt){
		
		DonHang dh = dhService.findById(maDh).get();
		DonHangChiTiet dhct = dh.getChiTietList().get(0);
		
		switch (tt) {
		case "CHO_XAC_NHAN": {
			dh.setTrangThai(TrangThaiDonHang.CHO_XAC_NHAN);
			break;
		}
		case "DANG_GIAO": {
			dh.setTrangThai(TrangThaiDonHang.DANG_GIAO);
			break;
		}
		case "DA_GIAO": {
			dh.setTrangThai(TrangThaiDonHang.DA_GIAO);
			break;
		}
		case "DA_HUY": {
			dh.setTrangThai(TrangThaiDonHang.DA_HUY);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + tt);
		}
		
		dhService.save(dh);
		
		String email = dh.getEmail();
		String hoTen = dh.getHoTen();
		String soDt = dh.getSoDienThoai();
		
		String subject = "🧾 Cập nhật đơn hàng - Trạng thái đơn hàng của bạn đã được cập nhật thành "+dh.getTrangThai().getDisplayName();
		
		String body = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset='UTF-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "<style>"
				+ "@media only screen and (max-width:600px) {"
				+ " .container { width:100% !important; }"
				+ " .padding-mobile { padding:20px !important; }"
				+ " .title { font-size:22px !important; }"
				+ "}"
				+ "</style>"
				+ "</head>"

				+ "<body style='margin:0;padding:0;background:#f4f6f8;'>"

				+ "<table width='100%' cellpadding='0' cellspacing='0' style='background:#f4f6f8;'>"
				+ "<tr><td align='center'>"

				+ "<table class='container' width='600' cellpadding='0' cellspacing='0' "
				+ "style='width:600px;max-width:600px;background:#ffffff;border-radius:12px;overflow:hidden;'>"

				+ "<tr>"
				+ "<td style='background:linear-gradient(135deg,#8B5E3C,#C4A484);"
				+ "color:#ffffff;padding:30px;text-align:center;'>"
				+ "<div class='title' style='font-size:26px;font-weight:bold;'>"
				+ "Buddha King - Hương Nhang Thảo Mộc"
				+ "</div>"
				+ "<div style='font-size:14px;margin-top:6px;opacity:0.9;'>Tinh Hoa Hương Việt</div>"
				+ "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td class='padding-mobile' style='padding:40px 35px;font-family:Arial,sans-serif;color:#333;'>"

				+ "<h2 style='margin-top:0;color:#8B5E3C;'>Xin chào " + hoTen + ",</h2>"

				+ "<p style='line-height:1.6;'>"
				+ "Cảm ơn bạn đã tin tưởng và đặt hàng tại <b>Buddha King</b> 🌿."
				+ "</p>"

				+ "<p style='line-height:1.6;'>Đơn hàng của bạn đã được tiếp nhận với thông tin như sau:</p>"

				+ "<table width='100%' cellpadding='8' cellspacing='0' "
				+ "style='border-collapse:collapse;margin-top:15px;'>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Sản phẩm</b></td>"
				+ "<td style='border:1px solid #eee;'>" + dhct.getTenSp() + "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Số lượng</b></td>"
				+ "<td style='border:1px solid #eee;'>" + dhct.getSoLuongMua() + "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Đơn giá</b></td>"
				+ "<td style='border:1px solid #eee;'>" + MoneyUtils.format(dhct.getDonGia()) + " VNĐ</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Tổng tiền</b></td>"
				+ "<td style='border:1px solid #eee;color:#8B5E3C;font-weight:bold;'>"
				+ MoneyUtils.format(dh.getTongTien()) + " VNĐ</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='background:#f7f3ef;border:1px solid #eee;'><b>Trạng thái</b></td>"
				+ "<td style='border:1px solid #eee;color:#d68910;font-weight:bold;'>"
				+ dh.getTrangThai().toHtml()
				+ "</td>"
				+ "</tr>"

				+ "</table>"

				+ "<p style='margin-top:25px;line-height:1.6;'>"
				+ "Đội ngũ Buddha King sẽ sớm liên hệ với bạn để xác nhận và tiến hành giao hàng."
				+ "</p>"

				+ "<p style='line-height:1.6;'>"
				+ "Nếu cần hỗ trợ thêm, vui lòng liên hệ lại qua email hoặc số điện thoại của chúng tôi."
				+ "</p>"

				+ "<p style='margin-top:30px;'>"
				+ "Trân trọng,<br>"
				+ "<b>Buddha King - Hương Nhang Thảo Mộc</b>"
				+ "</p>"

				+ "</td>"
				+ "</tr>"

				+ "<tr>"
		 		+ "<td style='background:#fafafa;padding:20px;text-align:center;"
				+ "font-size:12px;color:#777;font-family:Arial,sans-serif;'>"
				+ "© 2026 Buddha King - Hương Nhang Thảo Mộc | Tinh Hoa Hương Việt"
				+ "</td>"
				+ "</tr>"

				+ "</table>"
				+ "</td></tr></table>"
				+ "</body></html>";
		
		mailService.push(email, subject, body);
		
		return ResponseEntity.ok(dhMapper.convertToDTO(dh));
	}

}
