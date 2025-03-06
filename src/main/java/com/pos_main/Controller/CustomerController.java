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
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/getByName")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getCustomerBySearch(@RequestParam("name") String name) {
	    log.info("CustomerController.getCustomerBySearch() invoked with name: {}", name);
	    return customerService.getCustomerBySearch(name);
	}
	
	@GetMapping("/getByMobileNumber")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getCustomerByMobileNumber(@RequestParam("mobileNumber") String mobileNumber) {
	    log.info("CustomerController.getCustomerByMobileNumber() invoked with mobileNumber: {}", mobileNumber);
	    return customerService.getCustomerByMobileNumber(mobileNumber);
	}

	@GetMapping("/getAllPage")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getAllPageCustomer(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			WebRequest webRequest) {
		log.info("CustomerController.getAllPage() invoked.");
		return customerService.getAllPageCustomer(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
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
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto updateCustomer(@RequestBody CustomerDto customerDto) {
		log.info("CustomerController.updateCustomer() invoked");
		return customerService.updateCustomer(customerDto);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getAllCustomer() {
		log.info("CustomerController.getAll() invoked.");
		return customerService.getAllCustomer();
	}
	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto updateCustomerStatus(@RequestParam("customerId") Integer customerId, @RequestParam("status") Boolean status) {
		log.info("ProductController.updateCustomerStatus() invoked.");
		return customerService.updateCustomerStatus(customerId, status);
	}
	
}
