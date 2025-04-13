package com.pos_main.Dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductDiscountDto {
	private Integer id;
	private Double discount;
	private Integer quantity;
	private LocalDate endDate;
	private Boolean isActive;
	private ProductDiscountTypeDto productDiscountTypeDto;
	private ProductDto productDto;
}
