package com.pos_main.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TransactionEmployeeDto;
import com.pos_main.Service.TransactionEmployeeService;
import com.pos_main.Service.BL.TransactionEmployeeServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionEmployeeServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 17:45:06
 * @version 1.0
 **/

@Slf4j
@Service
public class TransactionEmployeeServiceImpl implements TransactionEmployeeService{
    
    @Autowired
    ServiceUtil serviceUtil;

    @Autowired
    TransactionEmployeeServiceBL transactionEmployeeServiceBL;
    
    @Transactional
    @Override
    public ResponseDto getByTransactionId(Integer transactionId) {
        log.info("TransactionEmployeeServiceImpl.getByTransactionId() invoked with transactionId: {}", transactionId);
        ResponseDto responseDto = null;
        try {
            List<TransactionEmployeeDto> transactionEmployeeDtoList = transactionEmployeeServiceBL.getByTransactionId(transactionId);
            if (transactionEmployeeDtoList != null && !transactionEmployeeDtoList.isEmpty()) {
                log.info("TransactionEmployee retrieved successfully for transactionId: {}", transactionId);
                responseDto = serviceUtil.getServiceResponse(transactionEmployeeDtoList);
            } else {
                log.info("No TransactionEmployee found for transactionId: {}", transactionId);
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_EMPLOYEE_BY_TRANSACTION_ID);
            }
        } catch (Exception e) {
            log.error("Exception occurred while retrieving TransactionEmployee by transactionId: {}", transactionId, e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_EMPLOYEE_BY_TRANSACTION_ID);
        }
        return responseDto;
    }
}