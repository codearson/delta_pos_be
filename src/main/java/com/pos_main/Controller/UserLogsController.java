package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserLogsDto;
import com.pos_main.Service.UserLogsService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("userLogs")
public class UserLogsController {
	
	@Autowired
	UserLogsService userLogsService;
	
	@PostMapping("/login")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto login(@RequestBody UserLogsDto userLogsDto) {
        log.info("UserLogsController.login() invoked");
        return userLogsService.login(userLogsDto);
    }
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody UserLogsDto userLogsDto) {
		log.info("UserLogsController.save() invoked");
		return userLogsService.save(userLogsDto);
	}
	
	@GetMapping("/getAllPage")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAllPageUserLogs(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			@RequestParam("status") Boolean status, WebRequest webRequest) {
		log.info("UserLogsServiceController.getAllPage() invoked.");
		return userLogsService.getAllPageUserLogs(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
	}

}
