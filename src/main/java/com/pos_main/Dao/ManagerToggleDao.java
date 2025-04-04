package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Dto.ManagerToggleDto;

/**
 * Title: ManagerToggleDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 19:54:39
 * @version 1.0
 **/

public interface ManagerToggleDao {
	
	ManagerToggleDto save(ManagerToggleDto managerToggleDto);
	
    ManagerToggleDto update(ManagerToggleDto managerToggleDto);
    
    ManagerToggleDto checkToggleAvailability(Integer id);
    
    List<ManagerToggleDto> getAll();
    
    List<ManagerToggleDto> getByName(String action);

}
