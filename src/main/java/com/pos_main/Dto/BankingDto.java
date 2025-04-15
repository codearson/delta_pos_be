package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: BankingDto.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 4, 2025.
 * @version 1.0
 **/

@Data
public class BankingDto {

	private Integer id;
	private Double amount;
	private LocalDateTime dateTime;
	private Boolean isActive;
	private UserDto userDto;
	private LocalDateTime generatedDateTime;
	
}


