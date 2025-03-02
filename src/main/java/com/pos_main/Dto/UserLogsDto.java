package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserLogsDto {
	
	private Integer id;
	private UserDto userDto;
	private LocalDateTime logIn;
	private LocalDateTime logOut;
	private Boolean signOff;
	private String description;

}
