package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.VoidHistoryDto;
import com.pos_main.Service.VoidHistoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: VoidHistoryController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 5, 2025
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("voidHistory")
public class VoidHistoryController {

	@Autowired
	VoidHistoryService voidHistoryService;
	
	@PostMapping("/save")
	public ResponseDto save(@RequestBody VoidHistoryDto voidHistoryDto) {
		log.info("VoidHistoryController.save() invoked");
		return voidHistoryService.save(voidHistoryDto);
	}
	
	@GetMapping("/getAll")
	public ResponseDto getAll() {
		log.info("VoidHistoryController.getAll() invoked");
		return voidHistoryService.getAll();
	}
	
}
