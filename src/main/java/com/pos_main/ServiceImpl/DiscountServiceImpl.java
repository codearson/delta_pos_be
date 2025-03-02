package com.pos_main.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.DiscountDto;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.DiscountService;
import com.pos_main.Service.BL.DiscountServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	ServiceUtil serviceUtil;
	@Autowired
	DiscountServiceBL discountServiceBL;
	
	@Override
	public ResponseDto save(DiscountDto discountDto) {
		log.info("DiscountServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			DiscountDto  saveDiscountDto = discountServiceBL.save(discountDto);
			if (saveDiscountDto != null) {
				log.info("Discount Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveDiscountDto);
			} else {
				log.info("Unable to save Discount details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_DISCOUNT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving DiscoUnt details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_DISCOUNT_DETAILS);
		}
		return responseDto;
	}
}
