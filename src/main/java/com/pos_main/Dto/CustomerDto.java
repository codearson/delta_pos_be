package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Feb 13, 2024 
 * 10:15:37 PM
 * @author Lathusan Thurairajah
 **/

@Data
public class CustomerDto {
	
	private Integer id;
	private String name;
	private String mobileNumber;
	private LocalDateTime createdDate;
	private Boolean isActive;

}