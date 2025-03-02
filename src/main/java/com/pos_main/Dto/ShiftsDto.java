package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShiftsDto {

	private Integer id;
	private UserDto userDto;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Boolean isActive;
}
