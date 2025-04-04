package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: EmployeeDiscountDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 22:54:03
 * @version 1.0
 **/

@Data
public class EmployeeDiscountDto {

	private Integer id;
	
	private UserDto userDto;
	
	private Double discount;
	
	private Boolean isActive;
}
