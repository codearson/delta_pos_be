package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.TransactionEmployeeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionEmployeeController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 17:44:27
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("transactionEmployee")
public class TransactionEmployeeController {
    
    @Autowired
    TransactionEmployeeService transactionEmployeeService;
    
    @GetMapping("/getByTransactionId")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getByTransactionId(@RequestParam("transactionId") Integer transactionId) {
        log.info("TransactionEmployeeController.getByTransactionId() invoked with transactionId: {}", transactionId);
        return transactionEmployeeService.getByTransactionId(transactionId);
    }
}