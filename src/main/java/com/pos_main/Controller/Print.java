package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Service.PrinterService;

/**
 * Title: Print.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Mar 11, 2025.
 * @version 1.0
 **/

@RestController
@RequestMapping("/print")
@CrossOrigin(origins = "http://localhost:3000")
public class Print {
	
	@Autowired PrinterService printerService;
	
	@PostMapping("/print-receipt")
	public String printReceipt() {
		boolean success = printerService.printReceipt();
		return success ? "Receipt printed successfully" : "Failed to print receipt";
	}

}


