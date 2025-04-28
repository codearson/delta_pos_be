package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.BankingDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.BankingService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BankingController.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 4, 2025.
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("banking")
public class BankingController {
	
	@Autowired
	BankingService bankingService;
	
	@PostMapping("/save")
	public ResponseDto save(@RequestBody BankingDto bankingDto) {
		log.info("BankingController.save() invoked");
		return bankingService.save(bankingDto);
	}
	
	@GetMapping("/getAllPage")
	public ResponseDto getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			@RequestParam("status") Boolean status, WebRequest webRequest) {
		log.info("BankingController.getAll() invoked.");
		return bankingService.getAllPage(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
	}
	
	@GetMapping("/getTotalBanking")
	public ResponseDto getTotalBanking() {
		log.info("BankingController.getTotalBanking() invoked");
		return bankingService.getTotalBanking();
	}
	
}


