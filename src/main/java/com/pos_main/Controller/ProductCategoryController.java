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

import com.pos_main.Dto.ProductCategoryDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductCategoryService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 14, 2024 4:35:44 PM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("productCategory")
public class ProductCategoryController {

	@Autowired
	ProductCategoryService productCategoryService;

	@GetMapping("/getAll")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAll() {
		log.info("ProductCategoryController.getAll() invoked");
		return productCategoryService.getAll();
	}
	
	@GetMapping("/getAllPage")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAllPageProductCategory(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			WebRequest webRequest) {
		log.info("ProductCategoryController.getAllPage() invoked.");
		return productCategoryService.getAllPageProductCategory(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
	}


	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ProductCategoryDto productCategoryDto) {
		log.info("ProductCategoryController.save() invoked");
		return productCategoryService.save(productCategoryDto);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto update(@RequestBody ProductCategoryDto productCategoryDto) {
		log.info("ProductCategoryController.update() invoked");
		return productCategoryService.update(productCategoryDto);
	}
    
	@GetMapping("/getByName")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllByName(@RequestParam("productCategoryName") String productCategoryName) {
        log.info("ProductCategoryController.getAllProductCategoryByName() invoked" );
        return productCategoryService.getAllByName(productCategoryName);
     
     
	}
    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateInvoiceStatus(@RequestParam("id") Integer Id, @RequestParam("status") Boolean status) {
    log.info("ProductCategoryController.updateInvoiceStatus() invoked.");
    return productCategoryService.updateProductCategoryStatus(Id, status);
    
    }
}
