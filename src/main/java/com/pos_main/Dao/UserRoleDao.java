package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.UserRole;
import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.UserRoleDto;

/**
 * Feb 5, 2024 
 * 1:40:04 PM
 * @author Lathusan Thurairajah
 **/

public interface UserRoleDao extends BaseDao<UserRole>{
	
	UserRoleDto saveUserRole(UserRoleDto userRoleDto);
	
	List<UserRoleDto> getAllUserRole();

}
