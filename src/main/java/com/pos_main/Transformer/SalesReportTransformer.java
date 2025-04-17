package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.SalesReport;
import com.pos_main.Dto.SalesReportDto;

/**
 * Title: SalesReportTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 18:36:05
 * @version 1.0
 **/

@Component
public class SalesReportTransformer implements BaseTransformer<SalesReport, SalesReportDto>{
	
	@Autowired
	BranchTransformer branchTransformer;
	
	@Override
	public SalesReportDto transform(SalesReport salesReport) {
		SalesReportDto salesReportDto = null;
		if (salesReport != null) {
			salesReportDto = new SalesReportDto();
			salesReportDto.setId(salesReport.getId());
			salesReportDto.setReportGeneratedBy(salesReport.getReportGeneratedBy());
			salesReportDto.setStartDate(salesReport.getStartDate());
			salesReportDto.setEndDate(salesReport.getEndDate());
			salesReportDto.setFullyTotalSales(salesReport.getFullyTotalSales());
			salesReportDto.setReportType(salesReport.getReportType());
			salesReportDto.setBanking(salesReport.getBanking());
			salesReportDto.setPayout(salesReport.getPayout());
			salesReportDto.setBankingCount(salesReport.getBankingCount());
			salesReportDto.setPayoutCount(salesReport.getPayoutCount());
			salesReportDto.setDifference(salesReport.getDifference());
		}
		return salesReportDto;
	}
	
	@Override
	public SalesReport reverseTransform(SalesReportDto salesReportDto) {
		SalesReport salesReport = null;
		if (salesReportDto != null) {
			salesReport = new SalesReport();
			salesReport.setId(salesReportDto.getId());
			salesReport.setReportGeneratedBy(salesReportDto.getReportGeneratedBy());
			salesReport.setStartDate(salesReportDto.getStartDate());
			salesReport.setEndDate(salesReportDto.getEndDate());
			salesReport.setFullyTotalSales(salesReportDto.getFullyTotalSales());
			salesReport.setReportType(salesReportDto.getReportType());
			salesReport.setBanking(salesReportDto.getBanking());
			salesReport.setPayout(salesReportDto.getPayout());
			salesReport.setBankingCount(salesReportDto.getBankingCount());
			salesReport.setPayoutCount(salesReportDto.getPayoutCount());
			salesReport.setDifference(salesReportDto.getDifference());
		}
		return salesReport;
	}

}
