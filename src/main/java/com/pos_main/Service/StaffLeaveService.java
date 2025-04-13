package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.StaffLeaveDto;

/**
 * Title: StaffLeaveService.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Mar 26, 2025.
 * @version 1.0
 **/

public interface StaffLeaveService {

	public ResponseDto save(StaffLeaveDto staffLeaveDto);

	public ResponseDto getAll();

	public ResponseDto update(StaffLeaveDto staffLeaveDto);

	public ResponseDto updateStatus(Integer id, Boolean status);
	
	public ResponseDto getAllPageStaffLeave(int pageNumber, int pageSize, Map<String, String> searchParameters);
	
	public ResponseDto sendEmail(String to, String subject, String body);

}
