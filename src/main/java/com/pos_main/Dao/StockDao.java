package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.Stock;
import com.pos_main.Dto.StockDto;


/**
 * Title: StockDao.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:39:32
 * @version 1.0
 **/

public interface StockDao extends BaseDao<Stock> {

	StockDto save(StockDto stockDto);

	List<StockDto> getAllStock();

	StockDto updateStock(StockDto stockDto);

	StockDto checkProductAvailability(Integer stockId);
	
	List<StockDto> getStockById(Integer id);
	
	List<StockDto> getStockByProductCategoryId(Integer productCategoryId);
	
	List<StockDto> getStockByProductId(Integer productId);
	
	List<StockDto> getStockByQuantityRange (Integer minQuantity, Integer maxQuantity);

}
