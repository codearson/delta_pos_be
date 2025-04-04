package com.pos_main.Service;

import com.pos_main.Dto.ManagerToggleDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: ManagerToggleService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 19:52:20
 * @version 1.0
 **/

public interface ManagerToggleService {
	
	ResponseDto save(ManagerToggleDto managerToggleDto);
	
    ResponseDto update(ManagerToggleDto managerToggleDto);
    
    ResponseDto updateStatus(Integer id, Boolean status);
    
    ResponseDto getAll();
    
    ResponseDto getByName(String action);

}
