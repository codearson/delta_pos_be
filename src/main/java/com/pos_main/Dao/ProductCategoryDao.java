package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.ProductCategory;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductCategoryDto;


/**
 * Feb 14, 2024 
 * 4:53:02 PM
 * @author Lathusan Thurairajah
 **/

public interface ProductCategoryDao extends BaseDao<ProductCategory>{

	List<ProductCategoryDto> getAll();
	
	PaginatedResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Map<String, String> searchParams);

	ProductCategoryDto save(ProductCategoryDto productCategoryDto);

	ProductCategoryDto update(ProductCategoryDto productCategoryDto);  
    
	List<ProductCategoryDto> getAllByName(String productCategoryName);  
	
	ProductCategoryDto checkUserAvailability(Integer Id);
}
