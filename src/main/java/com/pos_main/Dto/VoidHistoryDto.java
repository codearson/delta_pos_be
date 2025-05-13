package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: VoidHistoryDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 5, 2025
 * @version 1.0
 **/
@Data
public class VoidHistoryDto {

	private Integer id;
	private String itemName;
	private Double quantity;
	private Double price;
	private Double total;
	private LocalDateTime dateTime;
	private UserDto userDto;
	
}
