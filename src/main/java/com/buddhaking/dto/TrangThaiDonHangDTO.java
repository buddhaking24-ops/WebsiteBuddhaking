package com.buddhaking.dto;

public class TrangThaiDonHangDTO {

	private String code;
	private String displayName;
	private String color;
	private String icon;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public TrangThaiDonHangDTO(String code, String displayName, String color, String icon) {
		super();
		this.code = code;
		this.displayName = displayName;
		this.color = color;
		this.icon = icon;
	}
	
	
}
