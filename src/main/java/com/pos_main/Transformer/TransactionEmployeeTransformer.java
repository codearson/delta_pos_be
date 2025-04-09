package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.TransactionEmployee;
import com.pos_main.Dto.TransactionEmployeeDto;

/**
 * Title: TransactionEmployeeTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 17:44:05
 * @version 1.0
 **/

@Component
public class TransactionEmployeeTransformer implements BaseTransformer<TransactionEmployee, TransactionEmployeeDto> {
	
	@Autowired
	TransactionTransformer transactionTransformer;
	
	@Autowired
	UserTransfomer userTransformer;
	
	@Override
	public TransactionEmployeeDto transform(TransactionEmployee transactionEmployee) {
		TransactionEmployeeDto transactionEmployeeDto= null;
		if (transactionEmployee != null) {
			transactionEmployeeDto = new TransactionEmployeeDto();
			transactionEmployeeDto.setId(transactionEmployee.getId());
			if (transactionEmployee.getTransaction() != null) {
				transactionEmployeeDto.setTransactionDto(transactionTransformer.transform(transactionEmployee.getTransaction()));
			}
			if (transactionEmployee.getUser() != null) {
				transactionEmployeeDto.setUserDto(userTransformer.transform(transactionEmployee.getUser()));
			}
		}
		return transactionEmployeeDto;
	}

	@Override
	public TransactionEmployee reverseTransform(TransactionEmployeeDto transactionEmployeeDto) {
		TransactionEmployee transactionEmployee= null;
		if (transactionEmployeeDto != null) {
			transactionEmployee = new TransactionEmployee();
			transactionEmployee.setId(transactionEmployeeDto.getId());
			if (transactionEmployeeDto.getTransactionDto() != null) {
				transactionEmployee.setTransaction(transactionTransformer.reverseTransform(transactionEmployeeDto.getTransactionDto()));
			}
			if (transactionEmployeeDto.getUserDto() != null) {
				transactionEmployee.setUser(userTransformer.reverseTransform(transactionEmployeeDto.getUserDto()));
			}
		}
		return transactionEmployee;
	}

}
