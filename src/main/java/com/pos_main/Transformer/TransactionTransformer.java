package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.Transaction;
import com.pos_main.Dto.TransactionDto;

@Component
public class TransactionTransformer implements BaseTransformer<Transaction,TransactionDto>{

	@Autowired
	BranchTransformer branchTransformer;
	
	@Autowired
	ShopDetailsTransformer shopDetailsTransformer;
	
	@Autowired
	UserTransfomer userTransformer;
	
	@Autowired
	CustomerTransfomer customerTransformer;
	
	@Autowired
	TransactionDetailsListTransformer transactionDetailsListTransformer;
	
	@Autowired
	TransactionPaymentMethodTransformer transactionPaymentMethodTransformer;
	
	@Autowired
	TransactionEmployeeTransformer transactionEmployeeTransformer;
	
	@Override
	public TransactionDto transform(Transaction transaction) {
		TransactionDto transactionDto = null;
		if (transaction != null) {
			transactionDto = new TransactionDto();
			transactionDto.setId(transaction.getId());
			transactionDto.setDateTime(transaction.getDateTime());
			transactionDto.setTotalAmount(transaction.getTotalAmount());
			transactionDto.setStatus(transaction.getStatus());
			if (transaction.getBranch() != null) {
				transactionDto.setBranchDto(branchTransformer.transform(transaction.getBranch()));
			}
			if (transaction.getShopDetails() != null) {
				transactionDto.setShopDetailsDto(shopDetailsTransformer.transform(transaction.getShopDetails()));
			}
			if (transaction.getUser() != null) {
				transactionDto.setUserDto(userTransformer.transform(transaction.getUser()));
			}
			if (transaction.getCustomer() != null) {
				transactionDto.setCustomerDto(customerTransformer.transform(transaction.getCustomer()));
			}
			transactionDto.setIsActive(transaction.getIsActive());
			transactionDto.setGenerateDateTime(transaction.getGenerateDateTime());
			transactionDto.setManualDiscount(transaction.getManualDiscount());
			transactionDto.setEmployeeDiscount(transaction.getEmployeeDiscount());
			transactionDto.setBalanceAmount(transaction.getBalanceAmount());
			transactionDto.setTaxAmount(transaction.getTaxAmount());
		}
		return transactionDto;
	}
	
	@Override
	public Transaction reverseTransform(TransactionDto transactionDto) {
		Transaction transaction = null;
		if (transactionDto != null) {
			transaction = new Transaction();
			transaction.setId(transactionDto.getId());
			transaction.setDateTime(transactionDto.getDateTime());
			transaction.setTotalAmount(transactionDto.getTotalAmount());
			transaction.setStatus(transactionDto.getStatus());
			if (transactionDto.getBranchDto() != null) {
				transaction.setBranch(
						branchTransformer.reverseTransform(transactionDto.getBranchDto()));
			}
			if (transactionDto.getShopDetailsDto() != null) {
				transaction.setShopDetails(
						shopDetailsTransformer.reverseTransform(transactionDto.getShopDetailsDto()));
			}
			if (transactionDto.getUserDto() != null) {
				transaction.setUser(
						userTransformer.reverseTransform(transactionDto.getUserDto()));
			}
			if (transactionDto.getCustomerDto() != null) {
				transaction.setCustomer(
						customerTransformer.reverseTransform(transactionDto.getCustomerDto()));
			}			
			transaction.setIsActive(transactionDto.getIsActive());
			transaction.setGenerateDateTime(transactionDto.getGenerateDateTime());
			transaction.setManualDiscount(transactionDto.getManualDiscount());
			transaction.setEmployeeDiscount(transactionDto.getEmployeeDiscount());
			transaction.setBalanceAmount(transactionDto.getBalanceAmount());
			transaction.setTaxAmount(transactionDto.getTaxAmount());
		}
		return transaction;
	}
	
	
}
