package com.pos_main.Dto;

import lombok.Data;

@Data
public class TaxDto {
	
	private Integer id;
	private double taxPercentage;
	private Boolean isActive;
	
}
