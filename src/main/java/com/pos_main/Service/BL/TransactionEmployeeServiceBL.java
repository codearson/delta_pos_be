package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.TransactionEmployeeDao;
import com.pos_main.Dto.TransactionEmployeeDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionEmployeeServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 17:45:29
 * @version 1.0
 **/

@Slf4j
@Service
public class TransactionEmployeeServiceBL {
    
    @Autowired
    TransactionEmployeeDao transactionEmployeeDao;
    
    public List<TransactionEmployeeDto> getByTransactionId(Integer transactionId) {
        log.info("TransactionEmployeeServiceBL.getByTransactionId() invoked with transactionId: {}", transactionId);
        return transactionEmployeeDao.getByTransactionId(transactionId);
    }
}