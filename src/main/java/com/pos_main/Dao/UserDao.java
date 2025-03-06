package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.User;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.UserDto;

/**
 * Feb 5, 2024 
 * 11:20:57 AM
 * @author Lathusan Thurairajah
 **/

public interface UserDao extends BaseDao<User>{
	
	UserDto saveUser(UserDto userDto);

	User loadByUsername(String username);

	User findByByEmail(String email);

	PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	List<UserDto> getUserByName(String firstName, String lastName);
	
	List<UserDto> getUserById(Integer id);
	
	List<UserDto> getUserByRole(String userRole);
	
	UserDto update(UserDto userDto);
	
	UserDto checkUserAvailability(Integer userId);
	
	List<UserDto> getUserByEmailAddress(String emailAddress);

}
