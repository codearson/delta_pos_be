package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: ManagerToggleDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 19:38:03
 * @version 1.0
 **/

@Data
public class ManagerToggleDto {
	
	private Integer id;
	
	private String action;
	
	private Boolean isActive;
	
	private Boolean adminActive;

}
