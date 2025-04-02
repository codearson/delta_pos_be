package com.pos_main.Dto;

import lombok.Data;

@Data
public class TransactionDetailsListDto {
	
	private Integer id;
	
	private TransactionDto transactionDto;
	
	private ProductDto productDto;
	
	private Double unitPrice;
	
	private Integer quantity;
	
	private Double discount;
	
	
}
