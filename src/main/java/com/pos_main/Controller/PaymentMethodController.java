package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.PaymentMethodDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PaymentMethodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("paymentMethod")
public class PaymentMethodController {

	@Autowired
	PaymentMethodService paymentMethodService;

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto save(@RequestBody PaymentMethodDto paymentMethodDto) {
		log.info("PaymentMethodController.save() invoked");
		return paymentMethodService.save(paymentMethodDto);	
	}
}
