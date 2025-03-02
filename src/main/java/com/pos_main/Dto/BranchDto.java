package com.pos_main.Dto;

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
public class BranchDto {

	private Integer id;
	private String branchName;
	private String address;
	private String contactNumber;
	private String emailAddress;
	private String branchCode;
	private Boolean isActive;
	
}
