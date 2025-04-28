package com.pos_main.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ShiftsDto {

	private Integer id;
	private UserDto userDto;
	private UserDto managerDto;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private String status;
	private Boolean isActive;
}
