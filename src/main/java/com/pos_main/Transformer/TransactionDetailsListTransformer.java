package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.TransactionDetailsList;
import com.pos_main.Dto.TransactionDetailsListDto;

@Component
public class TransactionDetailsListTransformer implements BaseTransformer<TransactionDetailsList, TransactionDetailsListDto> {
	
	@Autowired
	TransactionTransformer transactionTransformer;
	
	@Autowired
	ProductTransformer productTransformer;
	
	@Override
	public TransactionDetailsListDto transform(TransactionDetailsList transactionDetailsList) {
		TransactionDetailsListDto transactionDetailsListDto= null;
		if (transactionDetailsList != null) {
			transactionDetailsListDto = new TransactionDetailsListDto();
			transactionDetailsListDto.setId(transactionDetailsList.getId());
			if (transactionDetailsList.getTransaction() != null) {
				transactionDetailsListDto.setTransactionDto(transactionTransformer.transform(transactionDetailsList.getTransaction()));
			}
			if (transactionDetailsList.getProduct() != null) {
				transactionDetailsListDto.setProductDto(productTransformer.transform(transactionDetailsList.getProduct()));
			}
			transactionDetailsListDto.setUnitPrice(transactionDetailsList.getUnitPrice());
			transactionDetailsListDto.setQuantity(transactionDetailsList.getQuantity());
			transactionDetailsListDto.setDiscount(transactionDetailsList.getDiscount());
			transactionDetailsListDto.setManualDiscount(transactionDetailsList.getManualDiscount());
		}
		return transactionDetailsListDto;
	}

	@Override
	public TransactionDetailsList reverseTransform(TransactionDetailsListDto transactionDetailsListDto) {
		TransactionDetailsList transactionDetailsList= null;
		if (transactionDetailsListDto != null) {
			transactionDetailsList = new TransactionDetailsList();
			transactionDetailsList.setId(transactionDetailsListDto.getId());
			if (transactionDetailsListDto.getTransactionDto() != null) {
				transactionDetailsList.setTransaction(transactionTransformer.reverseTransform(transactionDetailsListDto.getTransactionDto()));
			}
			if (transactionDetailsListDto.getProductDto() != null) {
				transactionDetailsList.setProduct(productTransformer.reverseTransform(transactionDetailsListDto.getProductDto()));
			}
			transactionDetailsList.setUnitPrice(transactionDetailsListDto.getUnitPrice());
			transactionDetailsList.setQuantity(transactionDetailsListDto.getQuantity());
			transactionDetailsList.setDiscount(transactionDetailsListDto.getDiscount());
			transactionDetailsList.setManualDiscount(transactionDetailsListDto.getManualDiscount());
		}
		return transactionDetailsList;
	}
	
}
