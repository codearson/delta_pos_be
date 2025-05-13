package com.pos_main.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.VoidHistoryDto;
import com.pos_main.Service.VoidHistoryService;
import com.pos_main.Service.BL.VoidHistoryServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: VoidHistoryServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 5, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class VoidHistoryServiceImpl implements VoidHistoryService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	VoidHistoryServiceBL voidHistoryServiceBL;
	
	public ResponseDto save(VoidHistoryDto voidHistoryDto) {
		log.info("VoidHistoryServiceImpl.save invoked");
		ResponseDto responseDto = null;
		try {
			VoidHistoryDto saveVoidHistoryDto = voidHistoryServiceBL.save(voidHistoryDto);
			if (saveVoidHistoryDto != null) {
				log.info("Void History Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveVoidHistoryDto);
			} else {
				log.info("Unable to save Void History details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_RE_VOID_HISTORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving void history details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_RE_VOID_HISTORY_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAll() {
		log.info("VoidHistoryServiceImpl.gellAll() invoked");
		ResponseDto responseDto = null;
		try {
			List<VoidHistoryDto> voidHistoryDtoList = voidHistoryServiceBL.gellAll();
			if (voidHistoryDtoList != null && !voidHistoryDtoList.isEmpty()) {
				log.info("Retrieve All void history Details.");
				responseDto = serviceUtil.getServiceResponse(voidHistoryDtoList);
			} else {
				log.info("Unable to retrieve All void history details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_VOID_HISTORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All void history details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_VOID_HISTORY_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("VoidHistoryServiceImpl.getAllPage() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = voidHistoryServiceBL.getAllPage(pageNumber, pageSize, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All void history Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All void history details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_VOID_HISTORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All void history details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_VOID_HISTORY_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageByDate(int pageNumber, int pageSize, String date, Map<String, String> searchParams) {
		log.info("VoidHistoryServiceImpl.getAllPageByDate() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = voidHistoryServiceBL.getAllPageByDate(pageNumber, pageSize, date, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve void history Details by date.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve void history details by date.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_VOID_HISTORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving void history details by date.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_VOID_HISTORY_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageByUserId(int pageNumber, int pageSize, Integer userId, Map<String, String> searchParams) {
		log.info("VoidHistoryServiceImpl.getAllPageByUserId() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = voidHistoryServiceBL.getAllPageByUserId(pageNumber, pageSize, userId, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve void history Details by user ID.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve void history details by user ID.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_VOID_HISTORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving void history details by user ID.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_VOID_HISTORY_DETAILS);
		}
		return responseDto;
	}
	
}
