package com.pos_main.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
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
			log.error("Exception occurs while saving shift details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_SHIFTS_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto update(ShiftsDto shiftsDto) {
		log.info("ShiftsServiceImpl.update() invoked");
		ResponseDto responseDto = null;
		try {
			ShiftsDto  saveShiftsDto = shiftsServiceBL.update(shiftsDto);
			if (saveShiftsDto != null) {
				log.info("Shifts Details updated successfully.");
				responseDto = serviceUtil.getServiceResponse(saveShiftsDto);
			} else {
				log.info("Unable to update Shifts details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_SHIFTS_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating shift details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_SHIFTS_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto updateStatus(Integer shiftId, Boolean status) {
		log.info("ShiftsServiceImpl.updateStatus() invoked");
		ResponseDto responseDto = null;
		try {
			ShiftsDto  updateStatusShiftsDto = shiftsServiceBL.updateStatus(shiftId, status);
			if (updateStatusShiftsDto != null) {
				log.info("Shifts Details status updated successfully.");
				responseDto = serviceUtil.getServiceResponse(updateStatusShiftsDto);
			} else {
				log.info("Unable to update Shifts details status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_SHIFTS_DETAILS_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating shift details status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_SHIFTS_DETAILS_STATUS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageShifts(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("ShiftServiceImpl.getAllPageShift() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = shiftsServiceBL.getAllPageShifts(pageNumber, pageSize, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All Shifts Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All Shifts details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_SHIFTS_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All shifts details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_SHIFTS_DETAILS);
		}
		return responseDto;
	}

}
