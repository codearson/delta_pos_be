package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.ShopDetailsDto;
import com.pos_main.Service.ShopDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShopDetailsController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 17:15:59
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("shopDetails")
public class ShopDetailsController {
	
	@Autowired
	ShopDetailsService shopDetailsService;
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ShopDetailsDto shopDetailsDto) {
		log.info("ShopDetailsController.save() invoked");
		return shopDetailsService.save(shopDetailsDto);
	}

}
