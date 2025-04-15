package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ProductDiscountTypeDao;
import com.pos_main.Dto.ProductDiscountTypeDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductDiscountTypeServiceBL.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 10, 2025.
 * @version 1.0
 **/

@Slf4j
@Service
public class ProductDiscountTypeServiceBL {
	
	@Autowired
	ProductDiscountTypeDao productDiscountTypeDao;
	
	public ProductDiscountTypeDto save(ProductDiscountTypeDto productDiscountTypeDto) {
        log.info("ProductDiscountTypeServiceBL.save() invoked");
        return productDiscountTypeDao.save(productDiscountTypeDto);
    }

    public ProductDiscountTypeDto update(ProductDiscountTypeDto productDiscountTypeDto) {
        log.info("ProductDiscountTypeServiceBL.update() invoked");
        return productDiscountTypeDao.update(productDiscountTypeDto);
    }

    public ProductDiscountTypeDto updateStatus(Integer id, Boolean status) {
        log.info("ProductDiscountTypeServiceBL.updateStatus() invoked");
        ProductDiscountTypeDto productDiscountTypeDto = productDiscountTypeDao.checkPayoutCategoryAvailability(id);
        if (productDiscountTypeDto != null) {
        	productDiscountTypeDto.setIsActive(status);
            return productDiscountTypeDao.update(productDiscountTypeDto);
        }
        return null;
    }

    public List<ProductDiscountTypeDto> getAll() {
        log.info("ProductDiscountTypeServiceBL.getAll() invoked");
        return productDiscountTypeDao.getAll();
    }

}


