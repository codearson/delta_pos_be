package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.StaffLeaveDto;
import com.pos_main.Service.StaffLeaveService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StaffLeaveController.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Mar 26, 2025.
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("staffLeave")
public class StaffLeaveController {
	
	@Autowired
	StaffLeaveService staffLeaveService;
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
	public ResponseDto save(@RequestBody StaffLeaveDto staffLeaveDto) {
		log.info("StaffLeaveController.save() invoked");
		return staffLeaveService.save(staffLeaveDto);
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto update(@RequestBody StaffLeaveDto staffLeaveDto) {
        log.info("StaffLeaveController.update() invoked");
        return staffLeaveService.update(staffLeaveDto);
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
        log.info("StaffLeaveController.updateStatus() invoked");
        return staffLeaveService.updateStatus(id, status);
    }
    
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAll() {
        log.info("StaffLeaveController.getAll() invoked");
        return staffLeaveService.getAll();
    }
    
	@GetMapping("/getAllPage")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAllPageStaffLeave(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			@RequestParam("status") Boolean status, WebRequest webRequest) {
		log.info("StaffLeaveController.getAllPage() invoked.");
		return staffLeaveService.getAllPageStaffLeave(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
	}
	
	@PostMapping("/sendEmail")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto sendEmail(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("body") String body) {
	    log.info("StaffLeaveController.sendEmail() invoked");
	    return staffLeaveService.sendEmail(to, subject, body);
	}

}
