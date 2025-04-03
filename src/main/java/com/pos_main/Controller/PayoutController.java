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

import com.pos_main.Dto.PayoutDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PayoutService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Branch.java. Company: www.codearson.com Copyright: Copyright (c) 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("payout")
public class PayoutController {
	
	@Autowired
	PayoutService  payoutService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto gellAllPayout() {
		log.info("PayoutController.gellAllPayout() invoked");
		return payoutService.getAllPayout();
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody PayoutDto payoutDto) {
		log.info("PayoutController.save() invoked");
		return payoutService.save(payoutDto);
	}
	
	
	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updatePayout(@RequestBody PayoutDto payoutDto) {
	    log.info("PayoutController.updatePayout() invoked");
	    return payoutService.updatePayout(payoutDto);
	}

	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateInvoiceStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
		log.info("BranchController.updateInvoiceStatus() invoked.");
		return payoutService.updatePayoutStatus(id, status);
	}
	

}
