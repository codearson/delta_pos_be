package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
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

}
