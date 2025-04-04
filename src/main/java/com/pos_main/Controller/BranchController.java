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
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.BranchDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.BranchService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Branch.java. Company: www.codearson.com Copyright: Copyright (c) 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("branch")
public class BranchController {
	
	@Autowired
	BranchService  branchService;
	
	@GetMapping("/getAllPage")
	//@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			WebRequest webRequest) {
		log.info("BranchController.getAll() invoked.");
		return branchService.getAll(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
	}
	
	@GetMapping("/getAll")
	// @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto gellAllBranches() {
		log.info("BranchController.gellAllBranches() invoked");
		return branchService.getAllBranches();
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody BranchDto branchDto) {
		log.info("BranchController.save() invoked");
		return branchService.save(branchDto);
	}
	
	@GetMapping("/getAllBySBUid")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto gellAllBranchesBySBU(@RequestParam("sbuId") Integer sbuId) {
		log.info("BranchController.gellAllBranchesBySBU() invoked");
		return branchService.getAllBranchesBySbuId(sbuId);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateBranch(@RequestBody BranchDto branchDto) {
	    log.info("BranchController.updateBranch() invoked");
	    return branchService.updateBranch(branchDto);
	}
	
	@GetMapping("/getByName")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getBranchByName(@RequestParam("branchName") String branchName) {
	    log.info("BranchController.getBranchByName() invoked");
	    return branchService.getBranchByName(branchName);
	}
	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateInvoiceStatus(@RequestParam("branchId") Integer branchId, @RequestParam("status") Boolean status) {
		log.info("BranchController.updateInvoiceStatus() invoked.");
		return branchService.updateBranchStatus(branchId, status);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getBranchById(@RequestParam("id") Integer id) {
	log.info("BranchController.getBranchById() invoked with id", id);
    return branchService.getBranchById(id);
	}

}
