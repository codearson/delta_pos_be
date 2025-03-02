package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Feb 13, 2024 
 * 10:16:30 PM
 * @author Lathusan Thurairajah
 **/

@Data
public class SupplierDto {
	
	private Integer id;
	private String name;
	private String emailAddress;
	private String mobileNumber;
	private String whatsappNumber;
	private LocalDateTime createdDate;
	private Boolean isActive;

}
