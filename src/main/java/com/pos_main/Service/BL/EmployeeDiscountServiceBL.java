package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.EmployeeDiscountDao;
import com.pos_main.Dto.EmployeeDiscountDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: EmployeeDiscountServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 22:56:49
 * @version 1.0
 **/

@Slf4j
@Service
public class EmployeeDiscountServiceBL {
	
	@Autowired
    EmployeeDiscountDao employeeDiscountDao;
    
    public EmployeeDiscountDto save(EmployeeDiscountDto employeeDiscountDto) {
        log.info("EmployeeDiscountServiceBL.save() invoked.");
        return employeeDiscountDao.save(employeeDiscountDto);
    }
    
    public EmployeeDiscountDto update(EmployeeDiscountDto employeeDiscountDto) {
        log.info("EmployeeDiscountServiceBL.update() invoked");
        return employeeDiscountDao.update(employeeDiscountDto);
    }
    
    public List<EmployeeDiscountDto> getAll() {
        log.info("EmployeeDiscountServiceBL.getAll() invoked");
        return employeeDiscountDao.getAll();
    }

}
