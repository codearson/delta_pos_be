package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: TransactionPaymentMethodDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Jan 29, 2025
 * @version 1.0
 **/

@Data
public class TransactionPaymentMethodDto {
	
	private Integer id;
	private TransactionDto transactionDto;
	private PaymentMethodDto paymentMethodDto;
	private Double amount;
	private Boolean isActive;
	
}
