package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.LoginRequestDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * Feb 5, 2024 
 * 11:05:29 AM
 * @author Lathusan Thurairajah
 **/

public interface UserService {
		
	public ResponseDto registerUser(UserDto userDto);

	public ResponseDto login(LoginRequestDto loginDto);

	public ResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParameters);
	
	public ResponseDto getUserByName(String firstName, String lastName);
	
	public ResponseDto getUserById(Integer id);
	
	public ResponseDto getUserByRole(String userRole);
	
	public ResponseDto updateUserDetails(UserDto userDto);
	
	public ResponseDto updateUserStatus(Integer userId, Boolean status);
	
	public ResponseDto updatePassword(Integer userId, String password, Integer changedByUserId);
	
	public ResponseDto getUserByEmailAddress(String emailAddress);

	public ResponseDto sendEmail(MultipartFile file, String period, String email);

}
