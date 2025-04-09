package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: TransactionEmployeeDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 17:43:30
 * @version 1.0
 **/

@Data
public class TransactionEmployeeDto {

	private Integer id;
	
	private TransactionDto transactionDto;
	
	private UserDto userDto;

}
