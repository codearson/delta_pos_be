package com.pos_main.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaymentMethodDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PaymentMethodService;
import com.pos_main.Service.BL.PaymentMethodServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	PaymentMethodServiceBL paymentMethodServiceBL;
	
	@Override
	public ResponseDto save(PaymentMethodDto paymentMethodDto) {
		log.info("PaymentMethodServiceImpl.save invoked");
		ResponseDto responseDto = null;
		try {
			PaymentMethodDto savePaymentMethodDto = paymentMethodServiceBL.save(paymentMethodDto);
			if (savePaymentMethodDto != null) {
				log.info("PaymentMethod Details saved.");
				responseDto = serviceUtil.getServiceResponse(savePaymentMethodDto);
			} else {
				log.info("Unable to save PaymentMethod details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PAYMENT_METHOD_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Payment details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PAYMENT_METHOD_DETAILS);
		}
		return responseDto;
	}
}
