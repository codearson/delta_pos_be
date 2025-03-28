package com.pos_main.Dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.pos_main.Domain.Transaction;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.TransactionDto;

/**
 * Title: TransactionDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 11, 2025
 * @version 1.0
 **/

public interface TransactionDao extends BaseDao<Transaction> {
	
	List<TransactionDto> getTransactionByDateRange (LocalDateTime startDate, LocalDateTime endDate);
	
	List<TransactionDto> getTransactionByBranchId(Integer branchId);
	
	List<TransactionDto> getTransactionById(Integer id);
	
	List<TransactionDto> getTransactionByUserId(Integer userId);
	
	List<TransactionDto> getTransactionByCustomerId(Integer customerId);
	
	List<TransactionDto> getTransactionByPaymentMethodId(Integer paymentMethodId);
	
	List<TransactionDto> getAllTransaction();
	
	List<TransactionDto> getTransactionByStatus(Boolean isActive);
	
	TransactionDto save(TransactionDto transactionDto, String alertMessage);
	
	TransactionDto updateTransaction(TransactionDto transactionDto);
	
	List<TransactionDto> getTransactionByProductId(Integer productId);
	
	Map<String, Object> getLastTransactionInfo();
	
	LocalDateTime getFirstTransactionDateTime();
	
	boolean areAllGenerateDateTimesNull(); 
    
	LocalDateTime getDateTimeForTransactionIdOne(); 
    
    LocalDateTime getLastGenerateDateTime();
    
    LocalDateTime getNextTransactionDateTimeAfter(LocalDateTime startDate);
	
	void updateGenerateDateTime(Integer transactionId, LocalDateTime generateDateTime);
	
	PaginatedResponseDto getAllPageTransaction(int pageNumber, int pageSize, Map<String, String> searchParams);
}
