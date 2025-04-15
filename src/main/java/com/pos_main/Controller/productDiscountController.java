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

import com.pos_main.Dto.ProductDiscountDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductDiscountService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("productDiscount")

public class productDiscountController {

	@Autowired
	ProductDiscountService productDiscountService;

	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountController.save() invoked");
		return productDiscountService.save(productDiscountDto);
	}

	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto update(@RequestBody ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountController.update() invoked");
		return productDiscountService.update(productDiscountDto);
	}

	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
		log.info("ProductDiscountController.updateStatus() invoked with id: {}, status: {}", id, status);
		return productDiscountService.updateStatus(id, status);
	}

	@GetMapping("/getAllPage")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAllPagePayoutCategory(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize, WebRequest webRequest) {
		log.info("ProductDiscountController.getAllPage() invoked");
		return productDiscountService.getAllPage(pageNumber, pageSize,
				HttpReqRespUtils.getSearchParameters(webRequest));
	}

}
