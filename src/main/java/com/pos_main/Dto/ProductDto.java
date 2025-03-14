package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Feb 13, 2024 
 * 10:16:14 PM
 * @author Lathusan Thurairajah
 **/

@Data
public class ProductDto {
	
	private Integer id;
	private String name;
	private String barcode;
	private Double pricePerUnit;
	private TaxDto taxDto;
	private LocalDateTime createdDate;
	private Boolean isActive;
	private ProductCategoryDto productCategoryDto;
	private Integer quantity;
	private Integer lowStock;
	private Double purchasePrice;

}
