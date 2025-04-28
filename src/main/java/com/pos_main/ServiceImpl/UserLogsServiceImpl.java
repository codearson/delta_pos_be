package com.pos_main.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.UserLogsDto;
import com.pos_main.Service.UserLogsService;
import com.pos_main.Service.BL.UserLogsServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StockServiceImpl.java. Company: www.codearson.com Copyright: Copyright
 * (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 16:39:22
 * @version 1.0
 **/


@Slf4j
@Service
public class UserLogsServiceImpl implements UserLogsService {

	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	UserLogsServiceBL userLogsServiceBL;
	
	@Override
	public ResponseDto login(UserLogsDto userLogsDto) {
		log.info("UserLogsServiceImpl.login() invoked");
		ResponseDto responseDto = null;
		try {
			UserLogsDto saveUserLogsDto = userLogsServiceBL.login(userLogsDto);
			if (saveUserLogsDto != null) {
				log.info("UserLogs Details loged.");
				responseDto = serviceUtil.getServiceResponse(saveUserLogsDto);
			} else {
				log.info("Unable to login UserLogs details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_LOGIN_USER_LOGS_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while UserLogs details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_LOGIN_USER_LOGS_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto save(UserLogsDto userLogsDto) {
		log.info("UserLogsServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			UserLogsDto saveUserLogsDto = userLogsServiceBL.save(userLogsDto);
			if (saveUserLogsDto != null) {
				log.info("UserLogs Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveUserLogsDto);
			} else {
				log.info("Unable to save UserLogs details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_USER_LOGS_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving UserLogs details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_USER_LOGS_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageUserLogs(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("UserLogsServiceImpl.getAllPageUserLogs() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = userLogsServiceBL.getAllPageUserLogs(pageNumber, pageSize, status, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All UserLogs Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All UserLogs details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_USER_LOGS_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All UserLogs details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_USER_LOGS_DETAILS);
		}
		return responseDto;
	}
}
