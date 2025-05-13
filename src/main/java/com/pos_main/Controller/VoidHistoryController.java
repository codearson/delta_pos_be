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

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.VoidHistoryDto;
import com.pos_main.Service.VoidHistoryService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

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
	
	@GetMapping("/getAllPage")
	public ResponseDto getAllPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, WebRequest webRequest) {
		log.info("VoidHistoryController.getAllPage() invoked.");
		return voidHistoryService.getAllPage(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
	}
	
	@GetMapping("/getAllPageByDate")
	public ResponseDto getAllPageByDate(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("date") String date,
			WebRequest webRequest) {
		log.info("VoidHistoryController.getAllPageByDate() invoked.");
		return voidHistoryService.getAllPageByDate(pageNumber, pageSize, date, HttpReqRespUtils.getSearchParameters(webRequest));
	}
	
	@GetMapping("/getAllPageByUserId")
	public ResponseDto getAllPageByUserId(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("userId") Integer userId, WebRequest webRequest) {
		log.info("VoidHistoryController.getAllPageByUserId() invoked.");
		return voidHistoryService.getAllPageByUserId(pageNumber, pageSize, userId, HttpReqRespUtils.getSearchParameters(webRequest));
	}
	
}
