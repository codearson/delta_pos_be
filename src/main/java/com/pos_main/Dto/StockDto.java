package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: StockDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:21:37
 * @version 1.0
 **/

@Data
public class StockDto {
	
	private Integer id;
	private ProductDto productDto;
	private BranchDto branchDto;
	private Integer quantity;
	private Boolean isActive;

}
