package com.pos_main.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TransactionDto;
import com.pos_main.Service.TransactionService;
import com.pos_main.Service.BL.TransactionServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionServiceImpl.java. Company: www.codearson.com Copyright:
 * Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 11, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	TransactionServiceBL transactionServiceBL;

	@Transactional
	@Override
	public ResponseDto getTransactionByBranchId(Integer branchId) {
		log.info("TransactionServiceImpl.getTransactionByBranchId() invoked with branchId: {}", branchId);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionByBranchId(branchId);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for branchId: {}", branchId);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for branchId: {}", branchId);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_BRANCH_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by branchId: {}", branchId, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_BRANCH_ID);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto getTransactionById(Integer id) {
		log.info("TransactionServiceImpl.getTransactionById() invoked with id: {}", id);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionById(id);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for id: {}", id);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for id: {}", id);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by id: {}", id, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_ID);
		}
		return responseDto;
	}

	public ResponseDto getTransactionByUserId(Integer userId) {
		log.info("TransactionServiceImpl.getTransactionByUserId() invoked with userId: {}", userId);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionByUserId(userId);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for userId: {}", userId);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for userId: {}", userId);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_USER_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by userId: {}", userId, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_USER_ID);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto getTransactionByPaymentMethodId(Integer paymentMethodId) {
		log.info("TransactionServiceImpl.getTransactionByPaymentMethodId() invoked with paymentMethodId: {}",
				paymentMethodId);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL
					.getTransactionByPaymentMethodId(paymentMethodId);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for paymentMethodId: {}", paymentMethodId);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for paymentMethodId: {}", paymentMethodId);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_PAYMENT_METHOD_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by paymentMethodId: {}", paymentMethodId, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_PAYMENT_METHOD_ID);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(TransactionDto transactionDto) {
		log.info("TransactionServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			TransactionDto savedTransaction = transactionServiceBL.save(transactionDto);
			if (savedTransaction != null) {
				log.info("Transaction details saved.");
				responseDto = serviceUtil.getServiceResponse(savedTransaction);
			} else {
				log.info("Unable to save transaction details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_TRANSACTION_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving transaction details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_TRANSACTION_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateTransaction(TransactionDto transactionDto) {
		log.info("TransactionServiceImpl.update(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			TransactionDto updateTransactionDto = transactionServiceBL.updateTransaction(transactionDto);
			if (updateTransactionDto != null) {
				log.info("transaction Details updated.");
				responseDto = serviceUtil.getServiceResponse(updateTransactionDto);
			} else {
				log.info("Unable to update Transaction details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_TRANSACTION_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Transaction details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_TRANSACTION_DETAILS);
		}
		return responseDto;

	}

	@Transactional
	@Override
	public ResponseDto getAllTransaction() {
		log.info("TransactionServiceImpl.getAllTransaction() invoked");
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getAllTransaction();
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("All transactions retrieved successfully.");
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_TRANSACTION);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving all transactions.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_TRANSACTION);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getTransactionByStatus(Boolean isActive) {
		log.info("TransactionServiceImpl.getTransactionByStatus() invoked with isActive: {}", isActive);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionByStatus(isActive);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for status: {}", isActive);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for status: {}", isActive);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by status: {}", isActive, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_STATUS);
		}
		return responseDto;
	}
}
