package com.pos_main.Service.BL;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ProductDiscountDao;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDiscountDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductDiscountServiceBL {
	
	@Autowired
	ProductDiscountDao productDiscountDao;
	
	public ProductDiscountDto save(ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountServiceBL.save() invoked.");
		return productDiscountDao.save(productDiscountDto);
	}
	
	public ProductDiscountDto update(ProductDiscountDto productDiscountDto) {
        log.info("ProductDiscountServiceBL.update() invoked");
        return productDiscountDao.update(productDiscountDto);
    }

    public ProductDiscountDto updateStatus(Integer id, Boolean status) {
        log.info("ProductDiscountServiceBL.updateStatus() invoked");
        ProductDiscountDto productDiscountDtoDto = productDiscountDao.checkPayoutCategoryAvailability(id);
        if (productDiscountDtoDto != null) {
        	productDiscountDtoDto.setIsActive(status);
            return productDiscountDao.update(productDiscountDtoDto);
        }
        return null;
    }
    
    public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
        log.info("ProductDiscountServiceBL.getAllPage() invoked");
        return productDiscountDao.getAllPage(pageNumber, pageSize, searchParams);
    }
}
