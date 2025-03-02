package com.pos_main.Dto;

import lombok.Data;

@Data
public class PaymentMethodDto {
	
	private Integer id;
	private String type;
	private Boolean isActive;

}
