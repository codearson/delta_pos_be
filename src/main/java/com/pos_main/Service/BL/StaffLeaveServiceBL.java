package com.pos_main.Service.BL;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.StaffLeaveServiceDao;
import com.pos_main.Dto.StaffLeaveDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StaffLeaveServiceBL.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Mar 26, 2025.
 * @version 1.0
 **/

@Slf4j
@Service
public class StaffLeaveServiceBL {

	@Autowired
	StaffLeaveServiceDao staffLeaveServiceDao;

	public StaffLeaveDto save(StaffLeaveDto staffLeaveDto) {
		log.info("StaffLeaveServiceBL.save() invoked.");
		staffLeaveDto.setStatus("Pending");
		staffLeaveDto.setCreatedOn(LocalDate.now());
		return staffLeaveServiceDao.save(staffLeaveDto);
	}

	public StaffLeaveDto update(StaffLeaveDto staffLeaveDto) {
		log.info("StaffLeaveServiceBL.update() invoked");
		return staffLeaveServiceDao.update(staffLeaveDto);
	}

	public StaffLeaveDto updateStatus(Integer id, Boolean status) {
		log.info("StaffLeaveServiceBL.updateStatus() invoked");
		StaffLeaveDto staffLeaveDto = staffLeaveServiceDao.checkAvailability(id);
		if (staffLeaveDto != null) {
			staffLeaveDto.setIsActive(status);
			return staffLeaveServiceDao.update(staffLeaveDto);
		} else {
			return null;
		}
	}

	public List<StaffLeaveDto> getAll() {
		log.info("StaffLeaveServiceBL.getAll() invoked");
		return staffLeaveServiceDao.getAll();
	}

}
