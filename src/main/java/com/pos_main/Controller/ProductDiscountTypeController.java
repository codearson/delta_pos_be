package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ProductDiscountTypeDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductDiscountTypeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductDiscountTypeController.java. Company: www.codearson.com |
 * Copyright: Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Apr 10, 2025.
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("productDiscountType")
public class ProductDiscountTypeController {

	@Autowired
	ProductDiscountTypeService productDiscountTypeService;

	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ProductDiscountTypeDto productDiscountTypeDto) {
		log.info("ProductDiscountTypeController.save() invoked");
		return productDiscountTypeService.save(productDiscountTypeDto);
	}

	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto update(@RequestBody ProductDiscountTypeDto productDiscountTypeDto) {
		log.info("ProductDiscountTypeController.update() invoked");
		return productDiscountTypeService.update(productDiscountTypeDto);
	}

	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
		log.info("ProductDiscountTypeController.updateStatus() invoked with id: {}, status: {}", id, status);
		return productDiscountTypeService.updateStatus(id, status);
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAll() {
		log.info("ProductDiscountTypeController.getAll() invoked");
		return productDiscountTypeService.getAll();
	}

}
