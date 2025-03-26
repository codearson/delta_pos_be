package com.pos_main.Dto;

import java.time.LocalDate;
import lombok.Data;

/**
 * Title: StaffLeaveDto.java. Company: www.codearson.com | Copyright: Copyright
 * (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Mar 26, 2025.
 * @version 1.0
 **/
@Data
public class StaffLeaveDto {

	private Integer id;
	private LocalDate createdOn;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
	private String status;
	private Boolean isActive;
	private UserDto userDto;

}
