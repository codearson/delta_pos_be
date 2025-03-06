package com.pos_main.Service;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserRoleDto;

/**
 * Feb 5, 2024 
 * 1:40:45 PM
 * @author Lathusan Thurairajah
 **/

public interface UserRoleService {
	
	public ResponseDto saveUserRole(UserRoleDto userRoleDto);
	
	public ResponseDto getAllUserRole();

}
