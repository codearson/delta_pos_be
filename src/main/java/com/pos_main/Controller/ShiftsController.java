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
import com.pos_main.Dto.ShiftsDto;
import com.pos_main.Service.ShiftsService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShiftsController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:36:33
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("shifts")
public class ShiftsController {
	
	@Autowired
	ShiftsService shiftsService;
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ShiftsDto shiftsDto) {
		log.info("ShiftsController.save() invoked");
		return shiftsService.save(shiftsDto);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto update(@RequestBody ShiftsDto shiftsDto) {
		log.info("ShiftsController.update() invoked");
		return shiftsService.update(shiftsDto);
	}
	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateStatus(@RequestParam("shiftId") Integer shiftId, @RequestParam("status") Boolean status) {
		log.info("ShiftsController.updateStatus() invoked");
		return shiftsService.updateStatus(shiftId, status);
	}
	
	@GetMapping("/getAllPage")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAllPageShifts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			WebRequest webRequest) {
		log.info("ShiftsController.getAllPage() invoked.");
		return shiftsService.getAllPageShifts(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
	}

}
