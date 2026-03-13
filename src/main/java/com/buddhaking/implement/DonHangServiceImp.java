package com.buddhaking.implement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddhaking.dao.DonHangDAO;
import com.buddhaking.dto.DonHangResponseDTO;
import com.buddhaking.dto.YeuCauDatHang;
import com.buddhaking.entity.DonHang;
import com.buddhaking.entity.DonHangChiTiet;
import com.buddhaking.entity.SanPham;
import com.buddhaking.enu.TrangThaiDonHang;
import com.buddhaking.service.DonHangChiTietService;
import com.buddhaking.service.DonHangService;
import com.buddhaking.service.MailerService;
import com.buddhaking.service.SanPhamService;
import com.buddhaking.util.MoneyUtils;

import jakarta.transaction.Transactional;

@Service
public class DonHangServiceImp implements DonHangService{

	@Autowired
	DonHangDAO dhDao;
	
	@Autowired
	SanPhamService spService;
	
	@Autowired
	DonHangChiTietService dhctService;
	
	@Override
	public List<DonHang> findAll() {
		return dhDao.findAll();
	}

	@Override
	public DonHang save(DonHang dh) {
		return dhDao.save(dh);
	}

	@Override
	public Optional<DonHang> findById(Long id) {
		return dhDao.findById(id);
	}

	@Override
	public boolean existById(Long id) {
		return dhDao.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		dhDao.deleteById(id);
	}

	@Override
	@Transactional
	public DonHang order(YeuCauDatHang ycdh) {
		//Xử lý đơn hàng
		SanPham sp = spService.findById("SP001").get();

		DonHang dh = new DonHang();
		dh.setDiaChiGiao(ycdh.getDiaChi());
		dh.setEmail(ycdh.getEmail());
		dh.setHoTen(ycdh.getHoTen());
		dh.setSoDienThoai(ycdh.getSoDienThoai());
		dh.setTrangThai(TrangThaiDonHang.CHO_XAC_NHAN);

		DonHangChiTiet dhct = new DonHangChiTiet();
		dhct.setSanPham(sp);
		dhct.setSoLuongMua(ycdh.getSoLuongMua());
		if (ycdh.getSoLuongMua() >= 100) {
			dhct.setDonGia(BigDecimal.valueOf(30000));
		} else {
			dhct.setDonGia(BigDecimal.valueOf(40000));
		}
		dhct.setTenSp(sp.getTenSanPham());

		dh.setTongTien(dhct.getDonGia().multiply(BigDecimal.valueOf(dhct.getSoLuongMua())));
		dh.addChiTiet(dhct);
		
		return this.save(dh);
	}
	
}
