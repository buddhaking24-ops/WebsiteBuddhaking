package com.buddhaking.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.buddhaking.dto.DonHangChiTietResponseDTO;
import com.buddhaking.dto.DonHangResponseDTO;
import com.buddhaking.entity.DonHang;

@Component
public class DonHangMapper {
	
	TrangThaiDonHangMapper mapper;

	public DonHangResponseDTO convertToDTO(DonHang dh) {

	    List<DonHangChiTietResponseDTO> chiTietList =
	            dh.getChiTietList().stream()
	              .map(ct -> new DonHangChiTietResponseDTO(
	                      ct.getTenSp(),
	                      ct.getSoLuongMua(),
	                      ct.getDonGia()
	              ))
	              .toList();

	    return new DonHangResponseDTO(
	            dh.getMaDonHang(),
	            dh.getCreatedAt(),
	            dh.getUpdatedAt(),
	            dh.getHoTen(),
	            dh.getSoDienThoai(),
	            dh.getEmail(),
	            dh.getTongTien(),
	            dh.getTrangThai(),
	            chiTietList
	    );
	}
	
}
