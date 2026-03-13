package com.buddhaking.enu;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TrangThaiDonHang {
	CHO_XAC_NHAN("Chờ xác nhận", "#d68910", "🕒"),
    DANG_GIAO("Đang giao", "#3498db", "🚚"),
    DA_GIAO("Đã giao", "#2ecc71", "📦"),
    DA_HUY("Đã huỷ", "#e74c3c", "❌");

    private final String displayName;
    private final String color;
    private final String icon;

    TrangThaiDonHang(String displayName, String color, String icon) {
        this.displayName = displayName;
        this.color = color;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    // Trả về HTML hoàn chỉnh (dùng cho email)
    public String toHtml() {
        return "<span style='color:" + color + ";font-weight:bold;'>"
                + icon + " " + displayName + "</span>";
    }
    
    
}
