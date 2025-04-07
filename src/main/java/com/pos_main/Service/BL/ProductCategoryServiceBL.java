package com.pos_main.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ProductCategoryDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductCategoryDto;


import lombok.extern.slf4j.Slf4j;

/**
 * Feb 14, 2024 
 * 4:49:46 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class ProductCategoryServiceBL {
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	public List<ProductCategoryDto> gellAll() {
		log.info("ProductCategoryServiceBL.gellAll() invoked");
		return productCategoryDao.getAll();

	}
	
	public PaginatedResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("ProductCategoryServiceBL.getAllPageProductCategory()invoked");
		return productCategoryDao.getAllPageProductCategory(pageNumber, pageSize, searchParams);
	}
	
	public ProductCategoryDto save(ProductCategoryDto productCategoryDto) {
		log.info("ProductCategoryServiceBL.save() invoked.");
		return productCategoryDao.save(productCategoryDto);
	}
	
	public ProductCategoryDto update(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryServiceBL.update() invoked.");
        return productCategoryDao.update(productCategoryDto);
    }

    public List<ProductCategoryDto> getAllByName(String productCategoryName) {
        log.info("ProductCategoryServiceBL.getAllByName() invoked.");
        return productCategoryDao.getAllByName(productCategoryName);
    }
    
    public ProductCategoryDto updateProductCategoryStatus(Integer Id, Boolean status) {
    	ProductCategoryDto productCategoryDto = productCategoryDao.checkUserAvailability(Id);
		if (productCategoryDto != null) {
			productCategoryDto.setIsActive(status);
			return productCategoryDao.update(productCategoryDto);
		} else {
			return null;
		}
	}

}
