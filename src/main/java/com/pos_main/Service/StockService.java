package com.pos_main.Service;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.StockDto;

/**
 * Title: StockService.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:37:51
 * @version 1.0
 **/

public interface StockService {
	public ResponseDto save(StockDto stockDto);

	public ResponseDto getAllStock();

	public ResponseDto updateStock(StockDto stockDto);

	public ResponseDto updateStockStatus(Integer stockId, Boolean status);
	
	public ResponseDto getStockById(Integer id);
	
	public ResponseDto getStockByProductCategoryId(Integer productCategoryId);
	
	public ResponseDto getStockByProductId(Integer productId);

	ResponseDto getStockByQuantityRange(Integer minQuantity, Integer maxQuantity); 
}
