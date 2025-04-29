package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: DeviceAuthDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:34:51
 * @version 1.0
 **/

@Data
public class DeviceAuthDto {
	
	private Integer id;
	
	private String tillName;
	
	private String tillId;
	
	private String approveStatus;
	
	private String loginStatus;
	
	private String isActive;

}
