package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.ProductDiscount;
import com.pos_main.Dto.ProductDiscountDto;

@Component
public class ProductDiscountTransformer implements BaseTransformer<ProductDiscount, ProductDiscountDto> {

	@Autowired
	ProductDiscountTypeTransformer productDiscountTypeTransformer;

	@Autowired
	ProductTransformer productTransformer;

	@Override
	public ProductDiscountDto transform(ProductDiscount productDiscount) {
		ProductDiscountDto productDiscountDto = null;
		if (productDiscount != null) {
			productDiscountDto = new ProductDiscountDto();
			productDiscountDto.setId(productDiscount.getId());
			productDiscountDto.setDiscount(productDiscount.getDiscount());
			productDiscountDto.setQuantity(productDiscount.getQuantity());
			productDiscountDto.setEndDate(productDiscount.getEndDate());
			productDiscountDto.setIsActive(productDiscount.getIsActive());
			if (productDiscount.getProductDiscountType() != null) {
				productDiscountDto.setProductDiscountTypeDto(
						productDiscountTypeTransformer.transform(productDiscount.getProductDiscountType()));
			}
			if (productDiscount.getProduct() != null) {
				productDiscountDto.setProductDto(productTransformer.transform(productDiscount.getProduct()));
			}
		}
		return productDiscountDto;

	}

	@Override
	public ProductDiscount reverseTransform(ProductDiscountDto productDiscountDto) {
		ProductDiscount productDiscount = null;
		if (productDiscountDto != null) {
			productDiscount = new ProductDiscount();
			productDiscount.setId(productDiscountDto.getId());
			productDiscount.setDiscount(productDiscountDto.getDiscount());
			productDiscount.setQuantity(productDiscountDto.getQuantity());
			productDiscount.setEndDate(productDiscountDto.getEndDate());
			productDiscount.setIsActive(productDiscountDto.getIsActive());
			if (productDiscountDto.getProductDiscountTypeDto() != null) {
				productDiscount.setProductDiscountType(productDiscountTypeTransformer
						.reverseTransform(productDiscountDto.getProductDiscountTypeDto()));
			}
			if (productDiscountDto.getProductDto() != null) {
				productDiscount.setProduct(productTransformer.reverseTransform(productDiscountDto.getProductDto()));
			}
		}
		return productDiscount;
	}
}
