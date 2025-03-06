package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserRoleDto;
import com.pos_main.Service.UserRoleService;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 
 * 1:39:27 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("userRole")
public class UserRoleController {
	
	@Autowired
	UserRoleService userRoleService;
	
	@PostMapping("/save")
	public ResponseDto saveUserRole(@RequestBody UserRoleDto userRoleDto) {
		log.info("UserTypeController.save() invoked");
		return userRoleService.saveUserRole(userRoleDto);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getAllUserRole() {
		log.info("UserRoleController.getAll() invoked.");
		return userRoleService.getAllUserRole();
	}

}
