package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.EmployeeDiscount;
import com.pos_main.Dto.EmployeeDiscountDto;

/**
 * Title: EmployeeDiscountTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 22:55:04
 * @version 1.0
 **/

@Component
public class EmployeeDiscountTransformer implements BaseTransformer<EmployeeDiscount, EmployeeDiscountDto> {
	
	@Autowired
	UserTransfomer userTransfomer;
	
	@Override
	public EmployeeDiscountDto transform(EmployeeDiscount employeeDiscount) {
		EmployeeDiscountDto employeeDiscountDto = null;
		if (employeeDiscount != null) {
			employeeDiscountDto = new EmployeeDiscountDto();
			employeeDiscountDto.setId(employeeDiscount.getId());
			if (employeeDiscount.getUser() != null) {
				employeeDiscountDto.setUserDto(userTransfomer.transform(employeeDiscount.getUser()));
			}
			employeeDiscountDto.setDiscount(employeeDiscount.getDiscount());
			employeeDiscountDto.setIsActive(employeeDiscount.getIsActive());
		}
		return employeeDiscountDto;
	}

	@Override
	public EmployeeDiscount reverseTransform(EmployeeDiscountDto employeeDiscountDto) {
		EmployeeDiscount employeeDiscount = null;
		if (employeeDiscountDto != null) {
			employeeDiscount = new EmployeeDiscount();
			employeeDiscount.setId(employeeDiscountDto.getId());
			if (employeeDiscountDto.getUserDto() != null) {
				employeeDiscount.setUser(
						userTransfomer.reverseTransform(employeeDiscountDto.getUserDto()));
			}
			employeeDiscount.setDiscount(employeeDiscountDto.getDiscount());
			employeeDiscount.setIsActive(employeeDiscountDto.getIsActive());
			
		}
		return employeeDiscount;
	}

}
