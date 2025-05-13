package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.VoidHistoryDto;

/**
 * Title: VoidHistoryService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 5, 2025
 * @version 1.0
 **/

public interface VoidHistoryService {

	public ResponseDto save(VoidHistoryDto voidHistoryDto);
	
	ResponseDto getAll();
	
	ResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	ResponseDto getAllPageByDate(int pageNumber, int pageSize, String date, Map<String, String> searchParams);
	
	ResponseDto getAllPageByUserId(int pageNumber, int pageSize, Integer userId, Map<String, String> searchParams);
	
}
