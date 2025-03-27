package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.StaffLeave;
import com.pos_main.Dto.StaffLeaveDto;

/**
 * Title: StaffLeaveTransformer.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Mar 26, 2025.
 * @version 1.0
 **/

@Component
public class StaffLeaveTransformer implements BaseTransformer<StaffLeave, StaffLeaveDto> {

	@Autowired
	UserTransfomer userTransfomer;

	@Override
	public StaffLeaveDto transform(StaffLeave staffLeave) {
		StaffLeaveDto staffLeaveDto = null;
		if (staffLeave != null) {
			staffLeaveDto = new StaffLeaveDto();
			staffLeaveDto.setId(staffLeave.getId());
			staffLeaveDto.setCreatedOn(staffLeave.getCreatedOn());
			staffLeaveDto.setStartDate(staffLeave.getStartDate());
			staffLeaveDto.setEndDate(staffLeave.getEndDate());
			staffLeaveDto.setDescription(staffLeave.getDescription());
			staffLeaveDto.setStatus(staffLeave.getStatus());
			staffLeaveDto.setIsActive(staffLeave.getIsActive());
			if (staffLeave.getUser() != null) {
				staffLeaveDto.setUserDto(userTransfomer.transform(staffLeave.getUser()));
			}
		}
		return staffLeaveDto;
	}

	@Override
	public StaffLeave reverseTransform(StaffLeaveDto staffLeaveDto) {
		StaffLeave staffLeave = null;
		if (staffLeaveDto != null) {
			staffLeave = new StaffLeave();
			staffLeave.setId(staffLeaveDto.getId());
			staffLeave.setCreatedOn(staffLeaveDto.getCreatedOn());
			staffLeave.setStartDate(staffLeaveDto.getStartDate());
			staffLeave.setEndDate(staffLeaveDto.getEndDate());
			staffLeave.setDescription(staffLeaveDto.getDescription());
			staffLeave.setStatus(staffLeaveDto.getStatus());
			staffLeave.setIsActive(staffLeaveDto.getIsActive());
			if (staffLeaveDto.getUserDto() != null) {
				staffLeave.setUser(userTransfomer.reverseTransform(staffLeaveDto.getUserDto()));
			}
		}
		return staffLeave;
	}

}
