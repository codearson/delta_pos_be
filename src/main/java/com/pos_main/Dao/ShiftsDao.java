package com.pos_main.Dao;

import java.util.Map;

import com.pos_main.Domain.Shifts;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ShiftsDto;


/**
 * Title: ShiftsDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:50:50
 * @version 1.0
 **/

public interface ShiftsDao extends BaseDao<Shifts>{
	
	ShiftsDto save(ShiftsDto shiftsDto);
	
	PaginatedResponseDto getAllPageShifts(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);

}
