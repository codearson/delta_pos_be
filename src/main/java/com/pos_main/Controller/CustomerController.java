package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.CustomerDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/getBySearch")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getCustomerBySearch(
	        @RequestParam(value = "firstName", required = false) String firstName,
	        @RequestParam(value = "lastName", required = false) String lastName) {
	    log.info("CustomerController.getCustomerBySearch() invoked with firstName: {}, lastName: {}", firstName, lastName);
	    return customerService.getCustomerBySearch(firstName, lastName);
	}

	@GetMapping("/getAllPage")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getAllCustomer(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			WebRequest webRequest) {
		log.info("CustomerController.getAll() invoked.");
		return customerService.getAllCustomer(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
	}

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto saveCustomer(@RequestBody CustomerDto customerDto) {
		log.info("CustomerController.saveCustomer() invoked");
		return customerService.saveCustomer(customerDto);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getCustomerById(@RequestParam("id") Integer id) {
	log.info("CustomerController.getCustomerById() invoked with id", id);
    return customerService.getCustomerById(id);
	}
	
	
}
