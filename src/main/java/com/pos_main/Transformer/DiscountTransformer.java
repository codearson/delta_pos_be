package com.pos_main.Transformer;

import org.springframework.stereotype.Component;
import com.pos_main.Domain.Discount;
import com.pos_main.Dto.DiscountDto;

@Component
public class DiscountTransformer implements BaseTransformer<Discount, DiscountDto> {

	@Override
	public DiscountDto transform(Discount discount) {
		DiscountDto discountDto = null;
		if (discount != null) {
			discountDto = new DiscountDto();
			discountDto.setId(discount.getId());
			discountDto.setDiscountPercentage(discount.getDiscountPercentage());
			discountDto.setIsActive(discount.getIsActive());
		}
		return discountDto;

	}

	
	
	@Override
	public Discount reverseTransform(DiscountDto discountDto) {
		Discount discount = null;
		if (discountDto != null) {
			discount = new Discount();
			discount.setId(discountDto.getId());
			discount.setDiscountPercentage(discountDto.getDiscountPercentage());
			discount.setIsActive(discountDto.getIsActive());
		}
		return discount;
		
	}
}
