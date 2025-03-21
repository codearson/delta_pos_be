package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.CategoryTotals;
import com.pos_main.Dto.CategoryTotalsDto;


/**
 * Title: CategoryTotalsTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 19:30:03
 * @version 1.0
 **/

@Component
public class CategoryTotalsTransformer implements BaseTransformer<CategoryTotals, CategoryTotalsDto>{

	@Autowired
	SalesDateDetailsTransformer salesDateDetailsTransformer;
	
	@Override
	public CategoryTotalsDto transform(CategoryTotals categoryTotals) {
		CategoryTotalsDto categoryTotalsDto = null;
		if (categoryTotals != null) {
			categoryTotalsDto = new CategoryTotalsDto();
			categoryTotalsDto.setId(categoryTotals.getId());
			if (categoryTotals.getSalesDateDetails() != null) {
				categoryTotalsDto.setSalesDateDetailsDto(salesDateDetailsTransformer.transform(categoryTotals.getSalesDateDetails()));
			}
			categoryTotalsDto.setCategoryName(categoryTotals.getCategoryName());
			categoryTotalsDto.setCategoryTotal(categoryTotals.getCategoryTotal());
		}
		return categoryTotalsDto;
	}
	
	@Override
	public CategoryTotals reverseTransform(CategoryTotalsDto categoryTotalsDto) {
		CategoryTotals categoryTotals = null;
		if (categoryTotalsDto != null) {
			categoryTotals = new CategoryTotals();
			categoryTotals.setId(categoryTotalsDto.getId());
			if (categoryTotalsDto.getSalesDateDetailsDto() != null) {
				categoryTotals.setSalesDateDetails(
						salesDateDetailsTransformer.reverseTransform(categoryTotalsDto.getSalesDateDetailsDto()));
			}
			categoryTotals.setCategoryName(categoryTotalsDto.getCategoryName());
			categoryTotals.setCategoryTotal(categoryTotalsDto.getCategoryTotal());
		}
		return categoryTotals;
	}
	
}
