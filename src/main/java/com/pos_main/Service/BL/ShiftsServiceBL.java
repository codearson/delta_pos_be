package com.pos_main.Service.BL;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ShiftsDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ShiftsDto;

import lombok.extern.slf4j.Slf4j;



/**
 * Title: ShiftsServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:48:55
 * @version 1.0
 **/

@Slf4j
@Service
public class ShiftsServiceBL {
	
	@Autowired
	ShiftsDao shiftsDao;
	
	public ShiftsDto save(ShiftsDto shiftsDto) {
		log.info("ShiftsServiceBL.save() invoked.");
		return shiftsDao.save(shiftsDto);
	}
	
	public ShiftsDto update(ShiftsDto shiftsDto) {
		log.info("ShiftsServiceBL.update() invoked.");
		return shiftsDao.update(shiftsDto);
	}
	
	public ShiftsDto updateStatus(Integer shiftId, Boolean status) {
		ShiftsDto shiftsDto = shiftsDao.checkShiftAvailability(shiftId);
		if (shiftsDto != null) {
			shiftsDto.setIsActive(status);
			return shiftsDao.update(shiftsDto);
		} else {
			return null;
		}
	}
	
	public PaginatedResponseDto getAllPageShifts(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("ShiftsServiceBL.getAllPageShifts()invoked");
		return shiftsDao.getAllPageShifts(pageNumber, pageSize, status, searchParams);
	}
	
	public List<ShiftsDto> getAllByDateRange(LocalDate startDate, LocalDate endDate) {
		log.info("ShiftsServiceBL.getAllByDateRange()invoked");
		return shiftsDao.getAllByDateRange(startDate, endDate);
	}

}
