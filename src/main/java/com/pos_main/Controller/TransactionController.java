package com.pos_main.Controller;

import java.time.LocalDateTime;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	@GetMapping("/getByDateRange")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto getTransactionByDateRange(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDate) {
        log.info("TransactionController.getTransactionByDateRange() invoked" );
        return transactionService.getTransactionByDateRange(startDate, endDate);
    }
	
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
	
	@GetMapping("/getByCustomerId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getTransactionByCustomerId(@RequestParam("customerId") Integer customerId) {
	    log.info("TransactionController.getTransactionByCustomerId() invoked with customerId: {}", customerId);
	    return transactionService.getTransactionByCustomerId(customerId);
	}
	
	@GetMapping("/getByPaymentMethodId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getTransactionByPaymentMethodId(@RequestParam("paymentMethodId") Integer paymentMethodId) {
	    log.info("TransactionController.getTransactionByPaymentMethodId() invoked with paymentMethodId: {}", paymentMethodId);
	    return transactionService.getTransactionByPaymentMethodId(paymentMethodId);
	}
	
	@PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto save(@RequestBody TransactionDto transactionDto, String alertMessage) {
        log.info("TransactionController.save() invoked");
        return transactionService.save(transactionDto, alertMessage);
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
	
	@GetMapping("/getByProductId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getTransactionByProductId(@RequestParam("productId") Integer productId) {
	    log.info("TransactionController.getTransactionByProductId() invoked with productId: {}", productId);
	    return transactionService.getTransactionByProductId(productId);
	}
	
	@GetMapping("/xReport")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getXReport(@RequestParam("userId") Integer userId) {
	    log.info("TransactionController.getXReport() invoked with userId: {}", userId);
	    return transactionService.getXReport(userId);
	}
	
	@GetMapping("/zReport")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getZReport(@RequestParam("userId") Integer userId) {
	    log.info("TransactionController.getZReport() invoked with userId: {}", userId);
	    return transactionService.getZReport(userId);
	}
}
