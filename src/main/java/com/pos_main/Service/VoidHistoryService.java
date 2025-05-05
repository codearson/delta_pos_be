package com.pos_main.Service;

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
	
}
