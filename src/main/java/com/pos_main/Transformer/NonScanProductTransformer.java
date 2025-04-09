package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.NonScanProduct;
import com.pos_main.Dto.NonScanProductDto;

/**
 * Title: NonScanProductTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:23:27
 * @version 1.0
 **/

@Component
public class NonScanProductTransformer implements BaseTransformer<NonScanProduct, NonScanProductDto>{
	
	@Override
	public NonScanProductDto transform(NonScanProduct nonScanProduct) {
		NonScanProductDto nonScanProductDto = null;
		if (nonScanProduct != null) {
			nonScanProductDto = new NonScanProductDto();
			nonScanProductDto.setId(nonScanProduct.getId());
			nonScanProductDto.setNonScanProduct(nonScanProduct.getNonScanProduct());
			nonScanProductDto.setIcon(nonScanProduct.getIcon());
			nonScanProductDto.setPrice(nonScanProduct.getPrice());
			nonScanProductDto.setIsActive(nonScanProduct.getIsActive());
		}
		return nonScanProductDto;
	}

	@Override
	public NonScanProduct reverseTransform(NonScanProductDto nonScanProductDto) {
		NonScanProduct nonScanProduct = null;
		if (nonScanProductDto != null) {
			nonScanProduct = new NonScanProduct();
			nonScanProduct.setId(nonScanProductDto.getId());
			nonScanProduct.setNonScanProduct(nonScanProductDto.getNonScanProduct());
			nonScanProduct.setIcon(nonScanProductDto.getIcon());
			nonScanProduct.setPrice(nonScanProductDto.getPrice());
			nonScanProduct.setIsActive(nonScanProductDto.getIsActive());
		}
		return nonScanProduct;
	}

}
