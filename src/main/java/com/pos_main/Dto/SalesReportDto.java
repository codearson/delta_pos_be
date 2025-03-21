package com.pos_main.Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Title: SalesReportDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 18:34:12
 * @version 1.0
 **/

@Data
public class SalesReportDto {
	
	private Integer id;
	private String reportGeneratedBy;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double fullyTotalSales;
	private List<SalesDateDetailsDto> salesDateDetails = new ArrayList<>();
	private String reportType;

}
