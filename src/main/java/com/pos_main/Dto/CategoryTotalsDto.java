package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: CategoryTotalsDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 19:29:44
 * @version 1.0
 **/

@Data
public class CategoryTotalsDto {
	
	private Integer id;
	private SalesDateDetailsDto salesDateDetailsDto;
    private String categoryName;
    private Double categoryTotal;

}
