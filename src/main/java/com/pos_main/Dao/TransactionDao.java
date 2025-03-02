package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.Transaction;
import com.pos_main.Dto.TransactionDto;

/**
 * Title: TransactionDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 11, 2025
 * @version 1.0
 **/

public interface TransactionDao extends BaseDao<Transaction> {
	
	List<TransactionDto> getTransactionByBranchId(Integer branchId);
	
	List<TransactionDto> getTransactionById(Integer id);
	List<TransactionDto> getTransactionByUserId(Integer userId);
	
	List<TransactionDto> getTransactionByPaymentMethodId(Integer paymentMethodId);
	
	List<TransactionDto> getAllTransaction();
	
	List<TransactionDto> getTransactionByStatus(Boolean isActive);
	
	TransactionDto save(TransactionDto transactionDto);
	
	TransactionDto updateTransaction(TransactionDto transactionDto);
	
	
}
