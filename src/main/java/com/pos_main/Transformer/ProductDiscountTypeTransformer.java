package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.ProductDiscountType;
import com.pos_main.Dto.ProductDiscountTypeDto;

/**
 * Title: ProductDiscountTypeTransformer.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Apr 10, 2025.
 * @version 1.0
 **/

@Component
public class ProductDiscountTypeTransformer implements BaseTransformer<ProductDiscountType, ProductDiscountTypeDto>{

	@Override
	public ProductDiscountTypeDto transform(ProductDiscountType productDiscountType) {
		ProductDiscountTypeDto productDiscountTypeDto = null;
		if (productDiscountType != null) {
			productDiscountTypeDto = new ProductDiscountTypeDto();
			productDiscountTypeDto.setId(productDiscountType.getId());
			productDiscountTypeDto.setType(productDiscountType.getType());
			productDiscountTypeDto.setIsActive(productDiscountType.getIsActive());
		}
		return productDiscountTypeDto;
	}

	@Override
	public ProductDiscountType reverseTransform(ProductDiscountTypeDto productDiscountTypeDto) {
		ProductDiscountType productDiscountType = null;
		if (productDiscountTypeDto != null) {
			productDiscountType = new ProductDiscountType();
			productDiscountType.setId(productDiscountTypeDto.getId());
			productDiscountType.setType(productDiscountTypeDto.getType());
			productDiscountType.setIsActive(productDiscountTypeDto.getIsActive());
		}
		return productDiscountType;
	}
	
	

}


