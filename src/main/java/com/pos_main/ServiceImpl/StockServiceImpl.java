package com.pos_main.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.StockDto;
import com.pos_main.Service.StockService;
import com.pos_main.Service.BL.StockServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StockServiceImpl.java. Company: www.codearson.com Copyright: Copyright
 * (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:37:22
 * @version 1.0
 **/

@Slf4j
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	StockServiceBL stockServiceBL;

	@Override
	public ResponseDto save(StockDto stockDto) {
		log.info("StockServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			StockDto saveStockDto = stockServiceBL.save(stockDto);
			if (saveStockDto != null) {
				log.info("Stock Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveStockDto);
			} else {
				log.info("Unable to save Stock details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_STOCK_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Stock details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_STOCK_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAllStock() {
		log.info("StockServiceImpl.getAllStock() invoked");
		ResponseDto responseDto = null;
		try {
			List<StockDto> stockDtoList = stockServiceBL.getAllStock();
			if (stockDtoList != null && !stockDtoList.isEmpty()) {
				log.info("Retrieve All Stock Details.");
				responseDto = serviceUtil.getServiceResponse(stockDtoList);
			} else {
				log.info("Unable to retrieve All Stock details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_STOCK_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Stock details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_STOCK_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageStock(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("StockServiceImpl.getAllPageStock() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = stockServiceBL.getAllPageStock(pageNumber, pageSize, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All Stock Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All Stock details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_STOCK_DETAILS_PAGE);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Stock details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_STOCK_DETAILS_PAGE);
		}
		return responseDto;
	}

	public ResponseDto updateStock(StockDto stockDto) {
		log.info("StockServiceImpl.updateStock invoked");
		ResponseDto responseDto = null;
		try {
			StockDto updateStockDto = stockServiceBL.updateStock(stockDto);
			if (updateStockDto != null) {
				log.info("Stock Details updated.");
				responseDto = serviceUtil.getServiceResponse(updateStockDto);
			} else {
				log.info("Unable to update Stock details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_STOCK_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Stock details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_STOCK_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateStockStatus(Integer stockId, Boolean status) {
		log.info("StockServiceImpl.updateStockStatus(StockDto stockDto) invoked");
		ResponseDto responseDto = null;
		try {
			StockDto updateStockStatusDto = stockServiceBL.updateStockStatus(stockId, status);
			if (updateStockStatusDto != null) {
				log.info("Stock Status updated.");
				responseDto = serviceUtil.getServiceResponse(updateStockStatusDto);
			} else {
				log.info("Unable to update Stock status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_STOCK_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Stock status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_STOCK_STATUS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getStockById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<StockDto> stockDtoList = stockServiceBL.getStockById(id);
			if (stockDtoList != null && !stockDtoList.isEmpty()) {
				log.info("Stock detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(stockDtoList);
			} else {
				log.info("Unable to Retrive stock detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_STOCK_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the stock detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_STOCK_BY_ID);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getStockByProductCategoryId(Integer productCategoryId) {
	    log.info("StockServiceImpl.getStockByProductCategoryId() invoked with productCategoryId: {}", productCategoryId);
	    ResponseDto responseDto = null;
	    try {
	        List<StockDto> stockDtoList = stockServiceBL.getStockByProductCategoryId(productCategoryId);
	        if (stockDtoList != null && !stockDtoList.isEmpty()) {
	            log.info("Stocks retrieved successfully for productCategoryId: {}", productCategoryId);
	            responseDto = serviceUtil.getServiceResponse(stockDtoList);
	        } else {
	            log.info("No stocks found for productCategoryId: {}", productCategoryId);
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_STOCK_BY_PRODUCT_CATEGORY_ID);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving stocks by productCategoryId: {}", productCategoryId, e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_STOCK_BY_PRODUCT_CATEGORY_ID);
	    }
	    return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getStockByProductId(Integer productId) {
	    log.info("StockServiceImpl.getStockByProductId() invoked with productId: {}", productId);
	    ResponseDto responseDto = null;
	    try {
	        List<StockDto> stockDtoList = stockServiceBL.getStockByProductId(productId);
	        if (stockDtoList != null && !stockDtoList.isEmpty()) {
	            log.info("Stocks retrieved successfully for productId: {}", productId);
	            responseDto = serviceUtil.getServiceResponse(stockDtoList);
	        } else {
	            log.info("No stocks found for productId: {}", productId);
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_STOCK_BY_PRODUCT_ID);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving stocks by productId: {}", productId, e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_STOCK_BY_PRODUCT_ID);
	    }
	    return responseDto;
	}
	
	@Transactional	
	@Override
    public ResponseDto getStockByQuantityRange (Integer minQuantity, Integer maxQuantity) {
        log.info("StockServiceImpl.getStockByQuantityRange () invoked");
        ResponseDto responseDto = null;
        try {
            List<StockDto> stockDtoList = stockServiceBL.getStockByQuantityRange(minQuantity, maxQuantity);
            if (stockDtoList != null && !stockDtoList.isEmpty()) {
                log.info("Retrieve Stock Details by QuantityRange.");
                responseDto = serviceUtil.getServiceResponse(stockDtoList);
            } else {
                log.info("Unable to retrieve Stock by QuantityRange.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_STOCK_BY_QUANTITYRANGE);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Stock by QuantityRange.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_STOCK_BY_QUANTITYRANGE);
        }
        return responseDto;
    }
}
