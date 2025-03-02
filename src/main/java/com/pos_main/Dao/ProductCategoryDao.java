package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.ProductCategory;
import com.pos_main.Dto.ProductCategoryDto;


/**
 * Feb 14, 2024 
 * 4:53:02 PM
 * @author Lathusan Thurairajah
 **/

public interface ProductCategoryDao extends BaseDao<ProductCategory>{

	List<ProductCategoryDto> getAll();

	ProductCategoryDto save(ProductCategoryDto productCategoryDto);

	ProductCategoryDto update(ProductCategoryDto productCategoryDto);  
    
	List<ProductCategoryDto> getAllByName(String productCategoryName);  
	
	ProductCategoryDto checkUserAvailability(Integer Id);
}
