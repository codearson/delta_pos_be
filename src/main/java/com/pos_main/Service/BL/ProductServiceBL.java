package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ProductDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 17, 2024 
 * 10:34:15 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class ProductServiceBL {
	
	@Autowired
	ProductDao productDao;
	
	public ProductDto save(ProductDto productDto) {
		log.info("ProductServiceBL.save() invoked.");
		productDto.setCreatedDate(LocalDateTime.now());
		return productDao.save(productDto);
	}

	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams, Boolean status) {
		log.info("ProductServiceBL.getAll()invoked");
		return productDao.getAll(pageNumber, pageSize, searchParams, status	);
	}

	public List<ProductDto> getAllProducts() {
		log.info("ProductServiceBL.getAllProducts() invoked");
		return productDao.getAllProducts();
	}
	
	public ProductDto updateProduct(ProductDto productDto) {
	    log.info("ProductServiceBL.updateProduct() invoked.");
	    return productDao.updateProduct(productDto);
	}
	
	public List<ProductDto> getProductByName(String name) {
        log.info("ProductCategoryServiceBL.getAllByName() invoked.");
        return productDao.getProductByName(name);
    }
	
	public ProductDto updateProductStatus(Integer productId, Boolean status) {
		ProductDto productDto = productDao.checkProductAvailability(productId);
		if (productDto != null) {
			productDto.setIsActive(status);
			return productDao.updateProduct(productDto);
		} else {
			return null;
		}
	}
	
	public List<ProductDto> getProductByBarcode(String barcode) {
		log.info("ProductServiceBL.getProductByBarcode()invoked");
		return productDao.getProductByBarcode(barcode);
	}
	
	public List<ProductDto> getProductById(Integer id) {
		log.info("ProductServiceBL.getProductById()invoked");
		return productDao.getProductById(id);
	}
	
}
