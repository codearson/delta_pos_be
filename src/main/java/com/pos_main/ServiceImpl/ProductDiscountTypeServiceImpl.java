package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ProductDiscountTypeDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductDiscountTypeService;
import com.pos_main.Service.BL.ProductDiscountTypeServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductDiscountTypeServiceImpl.java. Company: www.codearson.com |
 * Copyright: Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Apr 10, 2025.
 * @version 1.0
 **/

@Slf4j
@Service
public class ProductDiscountTypeServiceImpl implements ProductDiscountTypeService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	ProductDiscountTypeServiceBL productDiscountTypeServiceBL;

	@Override
	public ResponseDto save(ProductDiscountTypeDto productDiscountTypeDto) {
		log.info("ProductDiscountTypeServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			ProductDiscountTypeDto savedDto = productDiscountTypeServiceBL.save(productDiscountTypeDto);
			if (savedDto != null) {
				log.info("Product Discount type saved successfully");
				responseDto = serviceUtil.getServiceResponse(savedDto);
			} else {
				log.info("Failed to save Product Discount type");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_DISCOUNT_TYPE);
			}
		} catch (Exception e) {
			log.error("Exception while saving Product Discount Type", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_DISCOUNT_TYPE);
		}
		return responseDto;
	}

	@Override
	public ResponseDto update(ProductDiscountTypeDto productDiscountTypeDto) {
		log.info("ProductDiscountTypeServiceImpl.update() invoked");
		ResponseDto responseDto = null;
		try {
			ProductDiscountTypeDto updatedDto = productDiscountTypeServiceBL.update(productDiscountTypeDto);
			if (updatedDto != null) {
				log.info("Product Discount type updated successfully");
				responseDto = serviceUtil.getServiceResponse(updatedDto);
			} else {
				log.info("Failed to update Product Discount type");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DISCOUNT_TYPE);
			}
		} catch (Exception e) {
			log.error("Exception while updating Product Discount type", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_DISCOUNT_TYPE);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateStatus(Integer id, Boolean status) {
		log.info("ProductDiscountTypeServiceImpl.updateStatus() invoked");
		ResponseDto responseDto = null;
		try {
			ProductDiscountTypeDto updatedDto = productDiscountTypeServiceBL.updateStatus(id, status);
			if (updatedDto != null) {
				log.info("Product Discount type status updated successfully");
				responseDto = serviceUtil.getServiceResponse(updatedDto);
			} else {
				log.info("Failed to update Product Discount type status");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DISCOUNT_TYPE_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception while updating Product Discount type status", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_DISCOUNT_TYPE_STATUS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAll() {
		log.info("ProductDiscountTypeServiceImpl.getAllPayoutCategory() invoked");
		ResponseDto responseDto = null;
		try {
			List<ProductDiscountTypeDto> dtoList = productDiscountTypeServiceBL.getAll();
			if (dtoList != null && !dtoList.isEmpty()) {
				log.info("Product Discount type retrieved successfully");
				responseDto = serviceUtil.getServiceResponse(dtoList);
			} else {
				log.info("No Product Discount type found");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCT_DISCOUNT_TYPE);
			}
		} catch (Exception e) {
			log.error("Exception while retrieving Product type Discount", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCT_DISCOUNT_TYPE);
		}
		return responseDto;
	}

}
