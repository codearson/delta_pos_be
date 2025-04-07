package com.pos_main.Dto;


import lombok.Data;

/**
 * Feb 13, 2024 
 * 10:16:40 PM
 * @author Lathusan Thurairajah
 **/

@Data
public class ProductCategoryDto{
	
	private Integer id;
	private String productCategoryName;
	private Boolean isActive;
	private Boolean agevalidation;

}
