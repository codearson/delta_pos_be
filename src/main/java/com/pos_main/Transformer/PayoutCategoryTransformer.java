package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.PayoutCategory;
import com.pos_main.Dto.PayoutCategoryDto;

@Component
public class PayoutCategoryTransformer implements BaseTransformer<PayoutCategory, PayoutCategoryDto>{
 
	@Override
	public PayoutCategoryDto transform(PayoutCategory payoutCategory) {
		PayoutCategoryDto payoutCategoryDto = null;
		if (payoutCategory != null) {
			payoutCategoryDto = new PayoutCategoryDto();
			payoutCategoryDto.setId(payoutCategory.getId());
			payoutCategoryDto.setPayoutCategory(payoutCategory.getPayoutCategory());
			payoutCategoryDto.setIsActive(payoutCategory.getIsActive());
			
		}
		return payoutCategoryDto;
	}

	@Override
	public PayoutCategory reverseTransform(PayoutCategoryDto payoutCategoryDto) {
		PayoutCategory payoutCategory = null;
		if (payoutCategoryDto != null) {
			payoutCategory = new PayoutCategory();
			payoutCategory.setId(payoutCategoryDto.getId());
			payoutCategory.setPayoutCategory(payoutCategoryDto.getPayoutCategory());
			payoutCategory.setIsActive(payoutCategoryDto.getIsActive());
			
		}
		return payoutCategory;
	}
}
