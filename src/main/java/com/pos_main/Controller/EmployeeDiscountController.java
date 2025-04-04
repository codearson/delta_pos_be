package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.EmployeeDiscountDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.EmployeeDiscountService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: EmployeeDiscountController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 22:56:02
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("employeeDiscount")
public class EmployeeDiscountController {
	
	@Autowired
    EmployeeDiscountService employeeDiscountService;
    
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto save(@RequestBody EmployeeDiscountDto employeeDiscountDto) {
        log.info("EmployeeDiscountController.save() invoked");
        return employeeDiscountService.save(employeeDiscountDto);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto update(@RequestBody EmployeeDiscountDto employeeDiscountDto) {
        log.info("EmployeeDiscountController.update() invoked");
        return employeeDiscountService.update(employeeDiscountDto);
    }
    
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAll() {
        log.info("EmployeeDiscountController.getAll() invoked");
        return employeeDiscountService.getAll();
    }

}
