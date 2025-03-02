package com.pos_main.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TransactionPaymentMethodDto;
import com.pos_main.Service.TransactionPaymentMethodService;
import com.pos_main.Service.BL.TransactionPaymentMethodServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionPaymentMethodServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class TransactionPaymentMethodServiceImpl  implements TransactionPaymentMethodService {
	
	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	TransactionPaymentMethodServiceBL transactionPaymentMethodServiceBL;
	
	@Transactional
	@Override
	public ResponseDto getByTransactionId(Integer transactionId) {
	    log.info("TransactionPaymentMethodServiceImpl.getByTransactionId() invoked with transactionId: {}", transactionId);
	    ResponseDto responseDto = null;
	    try {
	        List<TransactionPaymentMethodDto> transactionPaymentMethodDtoList = transactionPaymentMethodServiceBL.getByTransactionId(transactionId);
	        if (transactionPaymentMethodDtoList != null && !transactionPaymentMethodDtoList.isEmpty()) {
	            log.info("TransactionPaymentMethod retrieved successfully for transactionId: {}", transactionId);
	            responseDto = serviceUtil.getServiceResponse(transactionPaymentMethodDtoList);
	        } else {
	            log.info("No transactionPaymentMethod found for transactionId: {}", transactionId);
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_PAYMENT_METHOD_BY_TRANSACTION_ID);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving transactionPaymentMethod by transactionId: {}", transactionId, e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_PAYMENT_METHOD_BY_TRANSACTION_ID);
	    }
	    return responseDto;
	}
	
}
