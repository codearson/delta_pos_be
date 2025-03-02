package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.ProductCategory;
import com.pos_main.Dto.ProductCategoryDto;

/**
 * Feb 13, 2024 
 * 10:51:23 PM
 * @author Lathusan Thurairajah
 **/

@Component
public class ProductCategoryTransfomer implements BaseTransformer<ProductCategory, ProductCategoryDto>{

	@Override
	public ProductCategoryDto transform(ProductCategory productCategory) {
		ProductCategoryDto productCategoryDto = null;
		if (productCategory != null) {
			productCategoryDto = new ProductCategoryDto();
			productCategoryDto.setId(productCategory.getId());
			productCategoryDto.setProductCategoryName(productCategory.getProductCategoryName());
			productCategoryDto.setIsActive(productCategory.getIsActive());
		}
		return productCategoryDto;
	}

	@Override
	public ProductCategory reverseTransform(ProductCategoryDto productCategoryDto) {
		ProductCategory productCategory = null;
		if (productCategoryDto != null) {
			productCategory = new ProductCategory();
			productCategory.setId(productCategoryDto.getId());
			productCategory.setProductCategoryName(productCategoryDto.getProductCategoryName());
			productCategory.setIsActive(productCategoryDto.getIsActive());
		}
		return productCategory;
	}

}
