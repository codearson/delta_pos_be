package com.pos_main.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TransactionDetailsListDto;
import com.pos_main.Service.TransactionDetailsListService;
import com.pos_main.Service.BL.TransactionDetailsListServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionDetailsListServiceImpl implements TransactionDetailsListService {
	
	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	TransactionDetailsListServiceBL transactionDetailsListServiceBL;
	
	@Transactional
	@Override
	public ResponseDto getByTransactionId(Integer transactionId) {
	    log.info("TransactionDetailsListServiceImpl.getByTransactionId() invoked with transactionId: {}", transactionId);
	    ResponseDto responseDto = null;
	    try {
	        List<TransactionDetailsListDto> transactionDetailsListDtoList = transactionDetailsListServiceBL.getByTransactionId(transactionId);
	        if (transactionDetailsListDtoList != null && !transactionDetailsListDtoList.isEmpty()) {
	            log.info("TransactionDetailsList retrieved successfully for transactionId: {}", transactionId);
	            responseDto = serviceUtil.getServiceResponse(transactionDetailsListDtoList);
	        } else {
	            log.info("No transactionDetailsList found for transactionId: {}", transactionId);
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_DETAILS_LIST_BY_TRANSACTION_ID);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving transactionDetailsList by transactionId: {}", transactionId, e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_DETAILS_LIST_BY_TRANSACTION_ID);
	    }
	    return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getAll() {
	    log.info("TransactionDetailsListServiceImpl.getAll() invoked");
	    ResponseDto responseDto = null;
	    try {
	        List<TransactionDetailsListDto> transactionDetailsListDtoList = transactionDetailsListServiceBL.getAll();
	        if (transactionDetailsListDtoList != null && !transactionDetailsListDtoList.isEmpty()) {
	            log.info("All TransactionDetailsList retrieved successfully");
	            responseDto = serviceUtil.getServiceResponse(transactionDetailsListDtoList);
	        } else {
	            log.info("No TransactionDetailsList found");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_TRANSACTION_DETAILS_LIST);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving all TransactionDetailsList", e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_TRANSACTION_DETAILS_LIST);
	    }
	    return responseDto;
	}
	
	
}
