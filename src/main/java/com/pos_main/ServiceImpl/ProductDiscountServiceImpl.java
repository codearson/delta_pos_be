package com.pos_main.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
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
			ProductDiscountDto  saveProductDiscountDto = productDiscountServiceBL.save(productDiscountDto);
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
}
