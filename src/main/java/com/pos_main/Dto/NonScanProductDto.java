package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: NonScanProductDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:23:09
 * @version 1.0
 **/

@Data
public class NonScanProductDto {
	
	private Integer id;
	
	private String nonScanProduct;
	
	private String icon;
	
	private Double price;
	
	private Boolean isActive;

}
