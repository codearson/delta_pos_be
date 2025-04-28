package com.pos_main.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.StaffLeaveDto;
import com.pos_main.Service.StaffLeaveService;
import com.pos_main.Service.BL.StaffLeaveServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StaffLeaveServiceImpl.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Mar 26, 2025.
 * @version 1.0
 **/

@Slf4j
@Service
public class StaffLeaveServiceImpl implements StaffLeaveService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	StaffLeaveServiceBL staffLeaveServiceBL;

	@Override
	public ResponseDto save(StaffLeaveDto staffLeaveDto) {
		log.info("StaffLeaveServiceImpl.save invoked");
		ResponseDto responseDto = null;
		try {
			StaffLeaveDto saveStaffLeaveDto = staffLeaveServiceBL.save(staffLeaveDto);
			if (saveStaffLeaveDto != null) {
				log.info("Staff Leave Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveStaffLeaveDto);
			} else {
				log.info("Unable to save Staff Leave details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_STAFF_LEAVE_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Staff Leave details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_STAFF_LEAVE_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto update(StaffLeaveDto staffLeaveDto) {
		log.info("StaffLeaveServiceImpl.update() invoked");
		ResponseDto responseDto = null;
		try {
			StaffLeaveDto updatedStaffLeaveDto = staffLeaveServiceBL.update(staffLeaveDto);
			if (updatedStaffLeaveDto != null) {
				log.info("Staff Leave Details updated.");
				responseDto = serviceUtil.getServiceResponse(updatedStaffLeaveDto);
			} else {
				log.info("Unable to update Staff Leave details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_STAFF_LEAVE_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Staff Leave details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_STAFF_LEAVE_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateStatus(Integer id, Boolean status) {
		log.info("StaffLeaveServiceImpl.updateStatus() invoked");
		ResponseDto responseDto = null;
		try {
			StaffLeaveDto updatedStaffLeaveDto = staffLeaveServiceBL.updateStatus(id, status);
			if (updatedStaffLeaveDto != null) {
				log.info("Staff Leave Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedStaffLeaveDto);
			} else {
				log.info("Unable to update Staff Leave status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_STAFF_LEAVE_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Staff Leave status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_STAFF_LEAVE_STATUS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAll() {
		log.info("StaffLeaveServiceImpl.getAll() invoked");
		ResponseDto responseDto;
		try {
			List<StaffLeaveDto> staffLeaveDtoList = staffLeaveServiceBL.getAll();
			responseDto = (staffLeaveDtoList != null && !staffLeaveDtoList.isEmpty())
					? serviceUtil.getServiceResponse(staffLeaveDtoList)
					: serviceUtil.getErrorServiceResponse(
							ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_STAFF_LEAVE_DETAILS);
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Staff Leave details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_STAFF_LEAVE_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageStaffLeave(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("StaffLeaveServiceImpl.getAllPageStaffLeave() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = staffLeaveServiceBL.getAllPageStaffLeave(pageNumber, pageSize, status, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All StaffLeave Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All StaffLeave details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_STAFFLEAVE_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All staffLeave details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_STAFFLEAVE_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto sendEmail(String to, String subject, String body) {
	    log.info("StaffLeaveServiceImpl.sendEmail() invoked");
	    ResponseDto responseDto = null;
	    try {
	        staffLeaveServiceBL.sendEmail(to, subject, body);
	        log.info("Email sent successfully to {}", to);
	        responseDto = serviceUtil.getServiceResponse("Email sent successfully");
	    } catch (Exception e) {
	        log.error("Exception occurs while sending email to {}", to, e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_SEND_EMAIL);
	    }
	    return responseDto;
	}

}
