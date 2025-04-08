package com.pos_main.Service;


import java.util.Map;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.ShiftsDto;

/**
 * Title: ShiftsService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:42:24
 * @version 1.0
 **/

public interface ShiftsService {
	
	public ResponseDto save(ShiftsDto shiftsDto);
	
	public ResponseDto getAllPageShifts(int pageNumber, int pageSize, Map<String, String> searchParameters);

}
