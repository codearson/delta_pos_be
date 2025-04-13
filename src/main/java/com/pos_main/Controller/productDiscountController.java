package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ProductDiscountDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductDiscountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("productDiscount")

public class productDiscountController {
	
	@Autowired
	ProductDiscountService productDiscountService;
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountController.save() invoked");
		return productDiscountService.save(productDiscountDto);
	}

}
