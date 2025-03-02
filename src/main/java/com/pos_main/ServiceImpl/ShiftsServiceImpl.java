package com.pos_main.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.ShiftsDto;
import com.pos_main.Service.ShiftsService;
import com.pos_main.Service.BL.ShiftsServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShiftsServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:45:11
 * @version 1.0
 **/

@Slf4j
@Service
public class ShiftsServiceImpl implements ShiftsService{
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	ShiftsServiceBL shiftsServiceBL;
	
	@Override
	public ResponseDto save(ShiftsDto shiftsDto) {
		log.info("ShiftsServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			ShiftsDto  saveShiftsDto = shiftsServiceBL.save(shiftsDto);
			if (saveShiftsDto != null) {
				log.info("Shifts Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveShiftsDto);
			} else {
				log.info("Unable to save Shifts details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_SHIFTS_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Product details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_SHIFTS_DETAILS);
		}
		return responseDto;
	}

}
