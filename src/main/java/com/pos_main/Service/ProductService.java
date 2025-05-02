package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Feb 17, 2024 10:33:56 PM
 * 
 * @author Lathusan Thurairajah
 **/

public interface ProductService {

	public ResponseDto save(ProductDto productDto);

	ResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams, Boolean status);

	ResponseDto getAllProducts();
	
	public ResponseDto getProductByBarcode(String barcode);
	
	ResponseDto getProductByName(String name); 
	
	public ResponseDto updateProduct(ProductDto productDto);
	
	public ResponseDto updateProductStatus(Integer productId, Boolean status);
	
	public ResponseDto getProductById(Integer id);

	ResponseDto getByProductCategoryName(int pageNumber, int pageSize, Map<String, String> searchParams, String categoryName, Boolean status);

	ResponseDto getByTaxPercentage(int pageNumber, int pageSize, Map<String, String> searchParams, Double taxPercentage, Boolean status);

}
