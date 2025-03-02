package com.pos_main.Service.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.MessageConfig;
import com.pos_main.Dto.ResponseDto;

@Service
public class ServiceUtil {

	@Autowired
	MessageConfig messageConfig;

	public ResponseDto getExceptionServiceResponse(Exception e) {
		ResponseDto serviceResponseDto;
		serviceResponseDto = new ResponseDto();
		serviceResponseDto.setStatus(Boolean.FALSE);
		serviceResponseDto.setResponseDto(e.getLocalizedMessage());
		serviceResponseDto.setErrorDescription(e.getLocalizedMessage());
		return serviceResponseDto;
	}

	public ResponseDto getServiceResponse(Object responseObject) {
		ResponseDto serviceResponceDto;
		serviceResponceDto = new ResponseDto();
		serviceResponceDto.setStatus(Boolean.TRUE);
		serviceResponceDto.setResponseDto(responseObject);
		return serviceResponceDto;
	}

	public ResponseDto getErrorServiceResponse(Object responseObject) {
		ResponseDto serviceResponceDto;
		serviceResponceDto = new ResponseDto();
		serviceResponceDto.setStatus(Boolean.FALSE);
		serviceResponceDto.setResponseDto(responseObject);
		return serviceResponceDto;
	}

	public ResponseDto getErrorServiceResponse(String errorCode) {
		ResponseDto serviceResponseDto = new ResponseDto();
		serviceResponseDto.setStatus(Boolean.FALSE);
		serviceResponseDto.setErrorCode(Integer.parseInt(messageConfig.getErrorCode(errorCode)));
		serviceResponseDto.setErrorDescription(messageConfig.getMessage(errorCode));
		return serviceResponseDto;
	}


	public ResponseDto getExceptionServiceResponseByProperties(String errorCode) {
		ResponseDto serviceResponseDto;
		serviceResponseDto = new ResponseDto();
		serviceResponseDto.setStatus(Boolean.FALSE);
		serviceResponseDto.setErrorCode(Integer.parseInt(messageConfig.getErrorCode(errorCode)));
		serviceResponseDto.setErrorDescription(messageConfig.getMessage(errorCode));
		return serviceResponseDto;
	}
}
