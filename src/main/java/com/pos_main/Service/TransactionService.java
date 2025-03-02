package com.pos_main.Service;

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
	
	ResponseDto getTransactionByBranchId(Integer branchId);
	
	ResponseDto getTransactionById(Integer id);
	ResponseDto getTransactionByUserId(Integer userId);
	
	ResponseDto getTransactionByPaymentMethodId(Integer paymentMethodId);

	ResponseDto save(TransactionDto transactionDto);
	
	ResponseDto getAllTransaction();
	
	ResponseDto getTransactionByStatus(Boolean isActive);
	
	public ResponseDto updateTransaction(TransactionDto transactionDto);
	
}
