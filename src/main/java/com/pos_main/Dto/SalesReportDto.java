package com.pos_main.Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Title: SalesReportDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 18:34:12
 * @version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesReportDto {
	
	private Integer id;
	private String reportGeneratedBy;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double fullyTotalSales;
	private String reportType;
	private List<SalesDateDetailsDto> salesDateDetails = new ArrayList<>();
	private Integer bankingCount;
	private Integer payoutCount;
	private Double banking;
	private Double payout;
	private Double difference;

}
