package com.pos_main.Controller;

import com.pos_main.Dto.LoginRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserDto;
import com.pos_main.Service.UserService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 11:03:12 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseDto registerUser(@RequestBody UserDto userDto) {
		log.info("UserController.registerUser() invoked");
		return userService.registerUser(userDto);
	}

	@PostMapping("/login")
	public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
		log.info("UserController.getUserByName() invoked");
		return userService.login(loginRequestDto);
	}

	@PostMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAdminMessage() {
		return "Hi from admin !!!";
	}

	@PostMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String getByUserMessage() {
		return "Hi from user !!!";
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			WebRequest webRequest) {
		log.info("UserController.getAll() invoked.");
		return userService.getAll(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
	}
	
	@GetMapping("/getByName")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getUserByName(
	        @RequestParam(value = "firstName", required = false) String firstName,
	        @RequestParam(value = "lastName", required = false) String lastName) {
	    log.info("UserController.getUserByName() invoked with firstName: {}, lastName: {}", firstName, lastName);
	    return userService.getUserByName(firstName, lastName);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getUserById(@RequestParam("id") Integer id) {
	    log.info("UserController.getUserById() invoked with id", id);
	    return userService.getUserById(id);
	}
	
	@GetMapping("/getByRole")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getUserByRole(@RequestParam("userRole") String userRole) {
	    log.info("UserController.getUserByRole() invoked with userRole", userRole);
	    return userService.getUserByRole(userRole);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto updateUserDetails(@RequestBody UserDto userDto) {
		log.info("UserController.updateUserDetails() invoked");
		return userService.updateUserDetails(userDto);
	}

	@PutMapping("/updateStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto updateInvoiceStatus(@RequestParam("userId") Integer userId, @RequestParam("status") Boolean status) {
		log.info("UserController.updateInvoiceStatus() invoked.");
		return userService.updateUserStatus(userId, status);
	}
	
	@GetMapping("/getByEmailAddress")
	public ResponseDto getUserByEmailAddress(@RequestParam("emailAddress") String emailAddress) {
	    log.info("UserController.getUserByEmailAddress() invoked with emailAddress", emailAddress);
	    return userService.getUserByEmailAddress(emailAddress);
	}

}
