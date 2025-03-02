package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.StockDao;
import com.pos_main.Dto.StockDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StockServiceBL.java. Company: www.codearson.com Copyright: Copyright
 * (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:38:27
 * @version 1.0
 **/

@Slf4j
@Service
public class StockServiceBL {

	@Autowired
	StockDao stockDao;

	public StockDto save(StockDto stockDto) {
		log.info("StockServiceBL.save() invoked.");
		return stockDao.save(stockDto);
	}

	public List<StockDto> getAllStock() {
		log.info("StockServiceBL.getAllStock() invoked");
		return stockDao.getAllStock();
	}

	public StockDto updateStock(StockDto stockDto) {
		log.info("StockServiceBL.updateStock() invoked.");
		return stockDao.updateStock(stockDto);
	}

	public StockDto updateStockStatus(Integer stockId, Boolean status) {
		StockDto stockDto = stockDao.checkProductAvailability(stockId);
		if (stockDto != null) {
			stockDto.setIsActive(status);
			return stockDao.updateStock(stockDto);
		} else {
			return null;
		}
	}
	
	public List<StockDto> getStockById(Integer id) {
		log.info("StockServiceBL.getStockById()invoked");
		return stockDao.getStockById(id);
	}
	
	public List<StockDto> getStockByProductCategoryId(Integer productCategoryId) {
	    log.info("StockServiceBL.getStockByProductCategoryId() invoked with productCategoryId: {}", productCategoryId);
	    return stockDao.getStockByProductCategoryId(productCategoryId);
	}
	
	public List<StockDto> getStockByProductId(Integer productId) {
	    log.info("StockServiceBL.getStockByProductId() invoked with productId: {}", productId);
	    return stockDao.getStockByProductId(productId);
	}
	
	public List<StockDto> getStockByQuantityRange(Integer minQuantity, Integer maxQuantity) {
        log.info("StockServiceBL.getStockByQuantityRange() invoked.");
        return stockDao.getStockByQuantityRange(minQuantity, maxQuantity);
    }

}
