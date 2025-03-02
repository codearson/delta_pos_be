package com.pos_main.Dto;

import lombok.Data;

@Data
public class DiscountDto {
	private Integer id;
	private Double discountPercentage;
	private Boolean isActive;
}
