package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.TransactionDetailsListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("transactionDetailsList")
public class TransactionDetailsListController {
	
	@Autowired
	TransactionDetailsListService transactionDetailsListService;
	
	@GetMapping("/getByTransactionId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getByTransactionId(@RequestParam("transactionId") Integer transactionId) {
	    log.info("TransactionDetailsListController.getByTransactionId() invoked with transactionId: {}", transactionId);
	    return transactionDetailsListService.getByTransactionId(transactionId);
	}
	
}
