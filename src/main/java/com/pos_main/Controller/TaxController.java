package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TaxDto;
import com.pos_main.Service.TaxService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("tax")
public class TaxController {
	
	@Autowired
	TaxService taxService;

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto save(@RequestBody TaxDto taxDto) {
		log.info("TaxController.save() invoked");
		return taxService.save(taxDto);
	}
}
