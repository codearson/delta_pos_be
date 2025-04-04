package com.pos_main.Service;

import com.pos_main.Dto.EmployeeDiscountDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Title: EmployeeDiscountService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 22:56:15
 * @version 1.0
 **/

public interface EmployeeDiscountService {
	
	ResponseDto save(EmployeeDiscountDto employeeDiscountDto);
	
    ResponseDto update(EmployeeDiscountDto employeeDiscountDto);
    
    ResponseDto getAll();

}
