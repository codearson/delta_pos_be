package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.SalesDateDetails;
import com.pos_main.Dto.SalesDateDetailsDto;

/**
 * Title: SalesDateDetailsTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 19:07:25
 * @version 1.0
 **/

@Component
public class SalesDateDetailsTransformer implements BaseTransformer<SalesDateDetails, SalesDateDetailsDto>{
	
	@Autowired
	SalesReportTransformer salesReportTransformer;
	
	@Override
	public SalesDateDetailsDto transform(SalesDateDetails salesDateDetails) {
		SalesDateDetailsDto salesDateDetailsDto = null;
		if (salesDateDetails != null) {
			salesDateDetailsDto = new SalesDateDetailsDto();
			salesDateDetailsDto.setId(salesDateDetails.getId());
			if (salesDateDetails.getSalesReport() != null) {
				salesDateDetailsDto.setSalesReportDto(salesReportTransformer.transform(salesDateDetails.getSalesReport()));
			}
			salesDateDetailsDto.setSalesDate(salesDateDetails.getSalesDate());
			salesDateDetailsDto.setTotalTransactions(salesDateDetails.getTotalTransactions());
			salesDateDetailsDto.setTotalSales(salesDateDetails.getTotalSales());
		}
		return salesDateDetailsDto;
	}
	
	@Override
	public SalesDateDetails reverseTransform(SalesDateDetailsDto salesDateDetailsDto) {
		SalesDateDetails salesDateDetails = null;
		if (salesDateDetailsDto != null) {
			salesDateDetails = new SalesDateDetails();
			salesDateDetails.setId(salesDateDetailsDto.getId());
			if (salesDateDetailsDto.getSalesReportDto() != null) {
				salesDateDetails.setSalesReport(
						salesReportTransformer.reverseTransform(salesDateDetailsDto.getSalesReportDto()));
			}
			salesDateDetails.setSalesDate(salesDateDetailsDto.getSalesDate());
			salesDateDetails.setTotalTransactions(salesDateDetailsDto.getTotalTransactions());
			salesDateDetails.setTotalSales(salesDateDetailsDto.getTotalSales());
		}
		return salesDateDetails;
	}

}
