package com.pos_main.Service.BL;

import com.pos_main.Domain.CustomUserDetails;
import com.pos_main.Domain.User;
import com.pos_main.Dto.JwtResponseDto;
import com.pos_main.Dto.LoginRequestDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Exception.EmailDuplicationException;
import com.pos_main.Service.EmailService;
import com.pos_main.Service.Utils.JwtUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.UserDao;
import com.pos_main.Dto.UserDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 11:17:32 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserServiceBL {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private EmailService emailService;

	public UserDto saveUser(UserDto userDto) {
		log.info("UserServiceBL.saveUser() invoked.");
		User user = userDao.findByByEmail(userDto.getEmailAddress());
		if (user != null) {
			log.info("exception thrown because of email duplication");
			throw new EmailDuplicationException(
					"User with the email id " + userDto.getEmailAddress() + " already exists.");
		} else {
			String encodedPassword = passwordEncoder.encode(userDto.getPassword());
			userDto.setPassword(encodedPassword);
			return userDao.saveUser(userDto);
		}

	}

	public User getUserByUserName(String username) {
		log.info("UserServiceBL.getUserByUserName() invoked");
		return userDao.loadByUsername(username);
	}

	public JwtResponseDto login(LoginRequestDto request) {
		log.info("UserServiceBL.login() invoked");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if (authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			return JwtResponseDto.builder().accessToken(jwtUtil.generateToken(userDetails)).build();
		} else {
			throw new RuntimeException("Authentication failed.");
		}
	}

	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("UserServiceBL.getAll()invoked");
		return userDao.getAll(pageNumber, pageSize, searchParams);
	}
	
	public List<UserDto> getUserByName(String firstName, String lastName) {
		log.info("UserServiceBL.getUserByName()invoked");
		return userDao.getUserByName(firstName, lastName);
	}
	
	public List<UserDto> getUserById(Integer id) {
		log.info("UserServiceBL.getUserById()invoked");
		return userDao.getUserById(id);
	}
	
	public List<UserDto> getUserByRole(String userRole) {
		log.info("UserServiceBL.getUserByRole()invoked");
		return userDao.getUserByRole(userRole);
	}
	
	public UserDto updateUserDetails(UserDto userDto) {
	    log.info("UserServiceBL.updateUserDetails() invoked.");
//	    String encodedPassword = passwordEncoder.encode(userDto.getPassword());
//		userDto.setPassword(encodedPassword);
	    userDto.setModifiedDate(LocalDateTime.now()); 
	    userDto.setCreatedDate(userDto.getCreatedDate());
	    return userDao.update(userDto);
	}
	
	public UserDto updateUserStatus(Integer userId, Boolean status) {
		UserDto userDto = userDao.checkUserAvailability(userId);
		if (userDto != null) {
			userDto.setIsActive(status);
			return userDao.updateStatus(userDto);
		} else {
			return null;
		}
	}
	
	public UserDto updatePassword(Integer userId, String password, Integer changedByUserId) {
	    UserDto userDto = userDao.checkUserAvailability(userId);
	    if (userDto != null) {
	        UserDto adminUser = userDao.checkUserAvailability(changedByUserId);
	        if (adminUser != null) {
	            String encodedPassword = passwordEncoder.encode(password);
	            userDto.setPassword(encodedPassword);
	            userDto.setModifiedDate(LocalDateTime.now());
	            
	            UserDto updatedUser = userDao.update(userDto);
	            if (updatedUser != null) {
	                String emailText = String.format(
	                    "Your password has been changed by %s.\n" +
	                    "Your new password is: %s",
	                    adminUser.getFirstName(),
	                    password
	                );
	                emailService.sendEmail(
	                    userDto.getEmailAddress(),
	                    "Password Change Notification",
	                    emailText
	                );
	                return updatedUser;
	            }
	        }
	    }
	    return null;
	}
	
	public List<UserDto> getUserByEmailAddress(String emailAddress) {
		log.info("UserServiceBL.getUserByEmailAddress()invoked");
		return userDao.getUserByEmailAddress(emailAddress);
	}

}
