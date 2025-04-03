package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: BranchDto.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Data
public class PayoutDto {

	private Integer id;
	private Double amount;
	private String type;
	private LocalDateTime dateTime;
	private UserDto userDto;
	private Boolean isActive;
	
}
