package com.pos_main.Service;

import java.time.LocalDateTime;
import java.util.Map;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TransactionDto;

/**
 * Title: TransactionService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 11, 2025
 * @version 1.0
 **/

public interface TransactionService {
	
	ResponseDto getTransactionByDateRange(LocalDateTime startDate, LocalDateTime endDate);
	
	ResponseDto getTransactionByBranchId(Integer branchId);
	
	ResponseDto getTransactionById(Integer id);
	
	ResponseDto getTransactionByUserId(Integer userId);
	
	ResponseDto getTransactionByCustomerId(Integer customerId);
	
	ResponseDto getTransactionByPaymentMethodId(Integer paymentMethodId);

	ResponseDto save(TransactionDto transactionDto, String alertMessage);
	
	ResponseDto getAllTransaction();
	
	ResponseDto getTransactionByStatus(Boolean isActive);
	
	public ResponseDto updateTransaction(TransactionDto transactionDto);
	
	ResponseDto getTransactionByProductId(Integer productId);
	
	ResponseDto getXReport(Integer userId);
	
	ResponseDto getZReport(Integer userId);
	
	public ResponseDto getAllPageTransaction(int pageNumber, int pageSize, Map<String, String> searchParameters);
	
	public ResponseDto getCashTotal(Integer userId);
}
