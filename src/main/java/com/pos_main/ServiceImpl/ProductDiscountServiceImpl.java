package com.pos_main.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDiscountDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductDiscountService;
import com.pos_main.Service.BL.ProductDiscountServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductDiscountServiceImpl implements ProductDiscountService {

	@Autowired
	ServiceUtil serviceUtil;
	@Autowired
	ProductDiscountServiceBL productDiscountServiceBL;

	@Override
	public ResponseDto save(ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			ProductDiscountDto saveProductDiscountDto = productDiscountServiceBL.save(productDiscountDto);
			if (saveProductDiscountDto != null) {
				log.info("Product Discount Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveProductDiscountDto);
			} else {
				log.info("Unable to save Product Discount details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_DISCOUNT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving DiscoUnt details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_DISCOUNT_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto update(ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountTypeServiceImpl.update() invoked");
		ResponseDto responseDto = null;
		try {
			ProductDiscountDto updatedDto = productDiscountServiceBL.update(productDiscountDto);
			if (updatedDto != null) {
				log.info("Product Discount updated successfully");
				responseDto = serviceUtil.getServiceResponse(updatedDto);
			} else {
				log.info("Failed to update Product Discount");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DISCOUNT);
			}
		} catch (Exception e) {
			log.error("Exception while updating Product Discount", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_DISCOUNT);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateStatus(Integer id, Boolean status) {
		log.info("ProductDiscountTypeServiceImpl.updateStatus() invoked");
		ResponseDto responseDto = null;
		try {
			ProductDiscountDto updatedDto = productDiscountServiceBL.updateStatus(id, status);
			if (updatedDto != null) {
				log.info("Product Discount status updated successfully");
				responseDto = serviceUtil.getServiceResponse(updatedDto);
			} else {
				log.info("Failed to update Product Discount status");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DISCOUNT_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception while updating Product Discount status", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_DISCOUNT_STATUS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("PayoutCategoryServiceImpl.getAllPage() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedDto = productDiscountServiceBL.getAllPage(pageNumber, pageSize,
					searchParams);
			if (paginatedDto != null) {
				log.info("Paginated Payout Categories retrieved successfully");
				responseDto = serviceUtil.getServiceResponse(paginatedDto);
			} else {
				log.info("No paginated Product Discount found");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAYOUT_CATEGORIES);
			}
		} catch (Exception e) {
			log.error("Exception while retrieving paginated product Discount", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PAYOUT_CATEGORIES);
		}
		return responseDto;
	}
}
