package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.TransactionPaymentMethod;
import com.pos_main.Dto.TransactionPaymentMethodDto;

/**
 * Title: TransactionPaymentMethodTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Jan 29, 2025
 * @version 1.0
 **/

@Component
public class TransactionPaymentMethodTransformer implements BaseTransformer<TransactionPaymentMethod,TransactionPaymentMethodDto> {
	
	@Autowired
	TransactionTransformer transactionTransformer;
	
	@Autowired
	PaymentMethodTransformer paymentMethodTransformer;
	
	@Override
	public TransactionPaymentMethodDto transform(TransactionPaymentMethod transactionPaymentMethod) {
		TransactionPaymentMethodDto transactionPaymentMethodDto = null;
		if (transactionPaymentMethod != null) {
			transactionPaymentMethodDto = new TransactionPaymentMethodDto();
			transactionPaymentMethodDto.setId(transactionPaymentMethod.getId());
			transactionPaymentMethodDto.setAmount(transactionPaymentMethod.getAmount());
			if (transactionPaymentMethod.getTransaction() != null) {
				transactionPaymentMethodDto.setTransactionDto(transactionTransformer.transform(transactionPaymentMethod.getTransaction()));
			}
			if (transactionPaymentMethod.getPaymentMethod() != null) {
				transactionPaymentMethodDto.setPaymentMethodDto(paymentMethodTransformer.transform(transactionPaymentMethod.getPaymentMethod()));
			}
			transactionPaymentMethodDto.setIsActive(transactionPaymentMethod.getIsActive());
		}
		return transactionPaymentMethodDto;
	}
	
	@Override
	public TransactionPaymentMethod reverseTransform(TransactionPaymentMethodDto transactionPaymentMethodDto) {
		TransactionPaymentMethod transactionPaymentMethod = null;
		if (transactionPaymentMethodDto != null) {
			transactionPaymentMethod = new TransactionPaymentMethod();
			transactionPaymentMethod.setId(transactionPaymentMethodDto.getId());
			transactionPaymentMethod.setAmount(transactionPaymentMethodDto.getAmount());
			if (transactionPaymentMethodDto.getTransactionDto() != null) {
				transactionPaymentMethod.setTransaction(transactionTransformer.reverseTransform(transactionPaymentMethodDto.getTransactionDto()));
			}
			if (transactionPaymentMethodDto.getPaymentMethodDto() != null) {
				transactionPaymentMethod.setPaymentMethod(paymentMethodTransformer.reverseTransform(transactionPaymentMethodDto.getPaymentMethodDto()));
			}
			transactionPaymentMethod.setIsActive(transactionPaymentMethodDto.getIsActive());
		}
		return transactionPaymentMethod;
	}
	
}
