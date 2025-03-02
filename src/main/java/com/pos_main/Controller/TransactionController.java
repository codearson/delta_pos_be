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

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TransactionDto;
import com.pos_main.Service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 11, 2025
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/getByBranchId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getTransactionByBranchId(@RequestParam("branchId") Integer branchId) {
	    log.info("TransactionController.getTransactionByBranchId() invoked with branchId: {}", branchId);
	    return transactionService.getTransactionByBranchId(branchId);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getTransactionById(@RequestParam("id") Integer id) {
	    log.info("TransactionController.getTransactionById() invoked with id: {}", id);
	    return transactionService.getTransactionById(id);
	}
	
	@GetMapping("/getByUserId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getTransactionByUserId(@RequestParam("userId") Integer userId) {
	    log.info("TransactionController.getTransactionByUserId() invoked with userId: {}", userId);
	    return transactionService.getTransactionByUserId(userId);
	}
	
	@GetMapping("/getByPaymentMethodId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getTransactionByPaymentMethodId(@RequestParam("paymentMethodId") Integer paymentMethodId) {
	    log.info("TransactionController.getTransactionByPaymentMethodId() invoked with paymentMethodId: {}", paymentMethodId);
	    return transactionService.getTransactionByPaymentMethodId(paymentMethodId);
	}
	
	@PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto save(@RequestBody TransactionDto transactionDto) {
        log.info("TransactionController.save() invoked");
        return transactionService.save(transactionDto);
    }
	
	@PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto updateTransaction(@RequestBody TransactionDto transactionDto) {
        log.info("TransactionController.updateTransaction() invoked");
        return transactionService.updateTransaction(transactionDto);
    }
	
	@GetMapping("/getAll")
	public ResponseDto getAllTransaction() {
		log.info("TransactionController.gellAllTransaction() invoked");
		return transactionService.getAllTransaction();
	}
	
	@GetMapping("/getByStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getTransactionByStatus(@RequestParam("isActive") Boolean isActive) {
	    log.info("TransactionController.getTransactionByStatus() invoked with isActive: {}", isActive);
	    return transactionService.getTransactionByStatus(isActive);
	}
}
