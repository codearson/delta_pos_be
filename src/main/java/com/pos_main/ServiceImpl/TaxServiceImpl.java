package com.pos_main.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TaxDto;
import com.pos_main.Service.TaxService;
import com.pos_main.Service.BL.TaxServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaxServiceImpl implements TaxService{
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	TaxServiceBL taxServiceBL;

	@Override
	public ResponseDto save(TaxDto taxDto) {
		log.info("TaxServiceImpl.save invoked");
		ResponseDto responseDto = null;
		try {
			TaxDto saveTaxDto = taxServiceBL.save(taxDto);
			if (saveTaxDto != null) {
				log.info("Tax Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveTaxDto);
			} else {
				log.info("Unable to save Tax details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_TAX_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Tax details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_TAX_DETAILS);
		}
		return responseDto;
	}
}
