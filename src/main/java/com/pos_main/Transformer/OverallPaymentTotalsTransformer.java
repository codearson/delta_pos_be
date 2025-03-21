package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.OverallPaymentTotals;
import com.pos_main.Dto.OverallPaymentTotalsDto;

/**
 * Title: OverallPaymentTotalsTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 20:01:44
 * @version 1.0
 **/

@Component
public class OverallPaymentTotalsTransformer implements BaseTransformer<OverallPaymentTotals, OverallPaymentTotalsDto>{

	@Autowired
	SalesDateDetailsTransformer salesDateDetailsTransformer;
	
	@Override
	public OverallPaymentTotalsDto transform(OverallPaymentTotals overallPaymentTotals) {
		OverallPaymentTotalsDto overallPaymentTotalsDto = null;
		if (overallPaymentTotals != null) {
			overallPaymentTotalsDto = new OverallPaymentTotalsDto();
			overallPaymentTotalsDto.setId(overallPaymentTotals.getId());
			if (overallPaymentTotals.getSalesDateDetails() != null) {
				overallPaymentTotalsDto.setSalesDateDetailsDto(salesDateDetailsTransformer.transform(overallPaymentTotals.getSalesDateDetails()));
			}
			overallPaymentTotalsDto.setPaymentMethod(overallPaymentTotals.getPaymentMethod());
			overallPaymentTotalsDto.setPaymentTotal(overallPaymentTotals.getPaymentTotal());
		}
		return overallPaymentTotalsDto;
	}
	
	@Override
	public OverallPaymentTotals reverseTransform(OverallPaymentTotalsDto overallPaymentTotalsDto) {
		OverallPaymentTotals overallPaymentTotals = null;
		if (overallPaymentTotalsDto != null) {
			overallPaymentTotals = new OverallPaymentTotals();
			overallPaymentTotals.setId(overallPaymentTotalsDto.getId());
			if (overallPaymentTotalsDto.getSalesDateDetailsDto() != null) {
				overallPaymentTotals.setSalesDateDetails(
						salesDateDetailsTransformer.reverseTransform(overallPaymentTotalsDto.getSalesDateDetailsDto()));
			}
			overallPaymentTotals.setPaymentMethod(overallPaymentTotalsDto.getPaymentMethod());
			overallPaymentTotals.setPaymentTotal(overallPaymentTotalsDto.getPaymentTotal());
		}
		return overallPaymentTotals;
	}
}
