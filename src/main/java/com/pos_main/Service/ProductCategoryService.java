package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.ProductCategoryDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Feb 14, 2024 
 * 4:42:38 PM
 * @author Lathusan Thurairajah
 **/

public interface ProductCategoryService {
	
	ResponseDto getAll();
	
	public ResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
	
	public ResponseDto save(ProductCategoryDto productCategoryDto);
	
	 ResponseDto update(ProductCategoryDto productCategoryDto); 
	 
	 ResponseDto getAllByName(String productCategoryName); 
	 
	 public ResponseDto updateProductCategoryStatus(Integer Id, Boolean status);
}
