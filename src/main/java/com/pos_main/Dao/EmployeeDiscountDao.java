package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Dto.EmployeeDiscountDto;

/**
 * Title: EmployeeDiscountDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 22:57:00
 * @version 1.0
 **/

public interface EmployeeDiscountDao {

	EmployeeDiscountDto save(EmployeeDiscountDto employeeDiscountDto);
	
    EmployeeDiscountDto update(EmployeeDiscountDto employeeDiscountDto);
    
    List<EmployeeDiscountDto> getAll();
}
