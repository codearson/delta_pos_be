package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Feb 5, 2024 
 * 10:09:25 AM
 * @author Lathusan Thurairajah
 **/

@Data
public class UserDto {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String password;
	private String address;
	private String emailAddress;
	private String mobileNumber;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private Boolean isActive;
	private UserRoleDto userRoleDto;
	private BranchDto branchDto;

}
