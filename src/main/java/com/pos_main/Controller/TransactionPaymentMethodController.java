package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.TransactionPaymentMethodService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionPaymentMethodController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("transactionPaymentMethod")
public class TransactionPaymentMethodController {

	@Autowired
	TransactionPaymentMethodService transactionDetailsListService;
	
	@GetMapping("/getByTransactionId")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getByTransactionId(@RequestParam("transactionId") Integer transactionId) {
	    log.info("TransactionPaymentMethodController.getByTransactionId() invoked with transactionId: {}", transactionId);
	    return transactionDetailsListService.getByTransactionId(transactionId);
	}
	
}
