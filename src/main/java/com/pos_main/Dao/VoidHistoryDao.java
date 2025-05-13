package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.VoidHistory;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.VoidHistoryDto;

/**
 * Title: VoidHistoryDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 5, 2025
 * @version 1.0
 **/

public interface VoidHistoryDao extends BaseDao<VoidHistory> {

	VoidHistoryDto save (VoidHistoryDto voidHistoryDto);
	
	List<VoidHistoryDto> getAll();
	
	PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	PaginatedResponseDto getAllPageByDate(int pageNumber, int pageSize, String date, Map<String, String> searchParams);
	
	PaginatedResponseDto getAllPageByUserId(int pageNumber, int pageSize, Integer userId, Map<String, String> searchParams);

}
