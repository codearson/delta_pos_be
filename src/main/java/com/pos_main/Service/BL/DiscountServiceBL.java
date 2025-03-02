package com.pos_main.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.DiscountDao;
import com.pos_main.Dto.DiscountDto;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiscountServiceBL {
	
	@Autowired
	DiscountDao discountDao;
	
	public DiscountDto save(DiscountDto discountDto) {
		log.info("DiscountServiceBL.save() invoked.");
		return discountDao.save(discountDto);
	}
}
