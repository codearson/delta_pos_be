package com.pos_main.ServiceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PayoutDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PayoutService;
import com.pos_main.Service.BL.PayoutServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PayoutServiceImpl.java. Company: www.codearson.com Copyright: Copyright
 * (c) 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@Service
public class PayoutServiceImpl implements PayoutService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	PayoutServiceBL payoutServiceBL;


	@Override
	public ResponseDto getAllPayout() {
		log.info("PayoutServiceImpl.getAllPayout() invoked");
		ResponseDto responseDto = null;
		try {
			List<PayoutDto> payoutDtoList = payoutServiceBL.getAllPayout();
			if (payoutDtoList != null && !payoutDtoList.isEmpty()) {
				log.info("Retrieve All payout Details.");
				responseDto = serviceUtil.getServiceResponse(payoutDtoList);
			} else {
				log.info("Unable to retrieve All Payout details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PAYOUT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Payout details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PAYOUT_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(PayoutDto payoutDto) {
		log.info("PayoutServiceImpl.save(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			PayoutDto savePayoutDto = payoutServiceBL.save(payoutDto);
			if (savePayoutDto != null) {
				log.info("Payout Details saved.");
				responseDto = serviceUtil.getServiceResponse(savePayoutDto);
			} else {
				log.info("Unable to save Payout details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_RE_PAYOUT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Payout details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_RE_PAYOUT_DETAILS);
		}
		return responseDto;
	}

	
	@Override
	public ResponseDto updatePayout(PayoutDto payoutDto) {
	    log.info("PayoutServiceImpl.updatePayout() invoked");
	    ResponseDto responseDto = null;
	    try {
	        PayoutDto updatedPayout = payoutServiceBL.updatePayout(payoutDto);
	        if (updatedPayout != null) {
	            log.info("Payout details updated successfully.");
	            responseDto = serviceUtil.getServiceResponse(updatedPayout);
	        } else {
	            log.info("Failed to update Payout details.");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_RE_PAYOUT_DETAILS);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while updating Payout details.", e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_RE_PAYOUT_DETAILS);
	    }
	    return responseDto;
	}


	@Override
	public ResponseDto updatePayoutStatus(Integer id, Boolean status) {
		log.info("InvoiceServiceImpl.updatePayoutStatus(PayoutDto payoutDto) invoked");
		ResponseDto responseDto = null;
		try {
			PayoutDto updatedPayoutStatusDto = payoutServiceBL.updatePayoutStatus(id, status);
			if (updatedPayoutStatusDto != null) {
				log.info("payout Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedPayoutStatusDto);
			} else {
				log.info("Unable to update Payout status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PAYOUT_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Payout status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PAYOUT_STATUS);
		}
		return responseDto;
	}
	

}
