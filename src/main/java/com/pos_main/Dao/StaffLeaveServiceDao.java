package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.StaffLeaveDto;

/**
 * Title: StaffLeaveServiceDao.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Mar 26, 2025.
 * @version 1.0
 **/

public interface StaffLeaveServiceDao {

	StaffLeaveDto save(StaffLeaveDto staffLeaveDto);

	StaffLeaveDto update(StaffLeaveDto staffLeaveDto);

	StaffLeaveDto checkAvailability(Integer id);

	List<StaffLeaveDto> getAll();
	
	PaginatedResponseDto getAllPageStaffLeave(int pageNumber, int pageSize, Map<String, String> searchParams);

}
