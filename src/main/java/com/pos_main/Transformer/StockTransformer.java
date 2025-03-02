package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.Stock;
import com.pos_main.Dto.StockDto;

/**
 * Title: StockTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:23:15
 * @version 1.0
 **/

@Component
public class StockTransformer implements BaseTransformer<Stock, StockDto>{
	
	@Autowired
	ProductTransformer productTransformer;
	
	@Autowired
	BranchTransformer branchTransformer;
	
	@Override
	public StockDto transform(Stock stock) {
		StockDto stockDto = null;
		if (stock != null) {
			stockDto = new StockDto();
			stockDto.setId(stock.getId());
			if (stock.getProduct() != null) {
				stockDto.setProductDto(productTransformer.transform(stock.getProduct()));
			}
			if (stock.getBranch() != null) {
				stockDto.setBranchDto(branchTransformer.transform(stock.getBranch()));
			}
			stockDto.setQuantity(stock.getQuantity());
			stockDto.setIsActive(stock.getIsActive());
		}
		return stockDto;
	}
	
	@Override
	public Stock reverseTransform(StockDto stockDto) {
		Stock stock = null;
		if (stockDto != null) {
			stock = new Stock();
			stock.setId(stockDto.getId());
			if (stockDto.getProductDto() != null) {
				stock.setProduct(
						productTransformer.reverseTransform(stockDto.getProductDto()));
			}
			if (stockDto.getBranchDto() != null) {
				stock.setBranch(
						branchTransformer.reverseTransform(stockDto.getBranchDto()));
			}
			stock.setQuantity(stockDto.getQuantity());
			stock.setIsActive(stockDto.getIsActive());

		}
		return stock;
	}

}
