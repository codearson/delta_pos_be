package com.pos_main.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.PaymentMethodDao;
import com.pos_main.Dto.PaymentMethodDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentMethodServiceBL {

	@Autowired
	PaymentMethodDao paymentMethodDao;
	
	public PaymentMethodDto save(PaymentMethodDto paymentMethodDto) {
		log.info("PaymentMethodServiceBL.save() invoked.");
		return paymentMethodDao.save(paymentMethodDto);
	}
}
