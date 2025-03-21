package com.pos_main.Dto;

import lombok.Data;

@Data
public class ShopDetailsDto {
	
	private Integer id;
	private String name;
	private String address;
	private String contactNumber;
	private String whatsappNumber;
	private String email;
	private Boolean isActive;


}
