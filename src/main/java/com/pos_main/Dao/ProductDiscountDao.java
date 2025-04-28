package com.pos_main.Dao;

import java.util.Map;

import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDiscountDto;

public interface ProductDiscountDao {

	ProductDiscountDto save(ProductDiscountDto productDiscountDto);

	ProductDiscountDto update(ProductDiscountDto productDiscountDto);

	ProductDiscountDto checkPayoutCategoryAvailability(Integer id);
	
	PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);
}
