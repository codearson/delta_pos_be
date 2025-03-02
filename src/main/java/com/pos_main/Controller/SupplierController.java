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

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.SupplierDto;
import com.pos_main.Service.SupplierService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("supplier")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto saveSupplier(@RequestBody SupplierDto supplierDto) {
		log.info("SupplierController.saveSupplier() invoked");
		return supplierService.saveSupplier(supplierDto);
	}
	
	@GetMapping("/getByName")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getSupplierByName(@RequestParam("name") String name) {
	    log.info("SupplierController.getSupplierByName() invoked with name", name);
	    return supplierService.getSupplierByName(name);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto updateSupplier(@RequestBody SupplierDto supplierDto) {
		log.info("SupplierController.updateSuppplier() invoked");
		return supplierService.updateSupplier(supplierDto);
	}
	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto updateSupplierStatus(@RequestParam("supplierId") Integer supplierId, @RequestParam("status") Boolean status) {
		log.info("SupplierController.updateSupplierStatus() invoked.");
		return supplierService.updateSupplierStatus(supplierId, status);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getSupplierById(@RequestParam("id") Integer id) {
		log.info("SupplierController.getSupplierById() invoked with id", id);
		return supplierService.getSupplierById(id);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getAllSupplier() {
		log.info("SupplierController.getAllSupplier() invoked");
		return supplierService.getAllSupplier();
	}


}
