package com.buddhaking.mapper;
import com.buddhaking.restcontroller.DonHangRestController;
import org.springframework.stereotype.Component;

import com.buddhaking.dto.TrangThaiDonHangDTO;
import com.buddhaking.enu.TrangThaiDonHang;

@Component
public class TrangThaiDonHangMapper {

    private final DonHangRestController donHangRestController;

    TrangThaiDonHangMapper(DonHangRestController donHangRestController) {
        this.donHangRestController = donHangRestController;
    }
	
	public TrangThaiDonHangDTO convertToDto(TrangThaiDonHang ttdh) {
		
		TrangThaiDonHangDTO ttDto = new TrangThaiDonHangDTO(ttdh.name(), ttdh.getDisplayName(), ttdh.getColor(), ttdh.getIcon());
		
		return ttDto;
		
	}
	
}
