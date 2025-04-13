package com.pos_main.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ProductDiscountDao;
import com.pos_main.Dto.ProductDiscountDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductDiscountServiceBL {
	
	@Autowired
	ProductDiscountDao productDiscountDao;
	
	public ProductDiscountDto save(ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountServiceBL.save() invoked.");
		return productDiscountDao.save(productDiscountDto);
	}
}
