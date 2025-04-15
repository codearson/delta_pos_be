package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.ProductDiscountType;
import com.pos_main.Dto.ProductDiscountTypeDto;

/**
 * Title: ProductDiscountTypeDao.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 10, 2025.
 * @version 1.0
 **/

public interface ProductDiscountTypeDao extends BaseDao<ProductDiscountType>{
	
	ProductDiscountTypeDto save(ProductDiscountTypeDto productDiscountTypeDto);
	
	ProductDiscountTypeDto update(ProductDiscountTypeDto productDiscountTypeDto);
	
	ProductDiscountTypeDto checkPayoutCategoryAvailability(Integer id);
	
	List<ProductDiscountTypeDto> getAll();

}


