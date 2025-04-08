package com.pos_main.Dto;


import lombok.Data;

@Data
public class PayoutCategoryDto {
   
	private Integer id;
	private String payoutCategory;
	private Boolean isActive;
}
