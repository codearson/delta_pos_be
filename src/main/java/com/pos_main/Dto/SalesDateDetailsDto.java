package com.pos_main.Dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Title: SalesDateDetailsDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 19:01:17
 * @version 1.0
 **/

@Data
public class SalesDateDetailsDto {
	
	private Integer id;
    private SalesReportDto salesReportDto;
    private String salesDate;
    private Integer totalTransactions;
    private Double totalSales;
    private List<CategoryTotalsDto> categoryTotals = new ArrayList<>();
    private List<OverallPaymentTotalsDto> overallPaymentTotals = new ArrayList<>();
    private List<UserPaymentDetailsDto> userPaymentDetails = new ArrayList<>();

}
