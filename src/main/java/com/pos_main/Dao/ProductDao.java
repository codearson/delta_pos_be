package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.Product;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;

/**
 * Feb 17, 2024 10:33:16 PM
 * 
 * @author Lathusan Thurairajah
 **/

public interface ProductDao extends BaseDao<Product> {

	ProductDto save(ProductDto productDto);

	PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams, Boolean status);

	List<ProductDto> getAllProducts();
	
	List<ProductDto> getProductByBarcode(String barcode);
	
	List<ProductDto> getProductByName (String name);
	
	ProductDto updateProduct(ProductDto productDto);
	
	ProductDto checkProductAvailability(Integer productId);
	
	List<ProductDto> getProductById(Integer id);

}
