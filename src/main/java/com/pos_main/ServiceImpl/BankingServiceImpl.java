package com.pos_main.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.BankingDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.BankingService;
import com.pos_main.Service.BL.BankingServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BankingServiceImpl.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 4, 2025.
 * @version 1.0
 **/

@Slf4j
@Service
public class BankingServiceImpl implements BankingService{
	
	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	BankingServiceBL bankingServiceBL;
	
	@Override
	public ResponseDto save(BankingDto bankingDto) {
		log.info("BankingServiceImpl.save(BankingDto bankingDto) invoked");
		ResponseDto responseDto = null;
		try {
			BankingDto saveBankingDto = bankingServiceBL.save(bankingDto);
			if (saveBankingDto != null) {
				log.info("Banking Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveBankingDto);
			} else {
				log.info("Unable to save Banking details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_RE_BANKING_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Branch details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_RE_BANKING_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("BankingServiceImpl.getAllPage() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = bankingServiceBL.getAllPage(pageNumber, pageSize, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All Banking Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All Banking details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BANKING_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Banking details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BANKING_DETAILS);
		}
		return responseDto;
	}

	

}


