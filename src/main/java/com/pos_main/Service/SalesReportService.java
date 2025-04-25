package com.pos_main.Service;

/**
 * Title: SalesReportService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:39:08
 * @version 1.0
 **/

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.SalesReportDto;

public interface SalesReportService {
    
	ResponseDto save(SalesReportDto salesReportDto);
	
	ResponseDto getXReports();
	
	ResponseDto getZReports();

	ResponseDto getZReportsWithPagination(int pageNumber, int pageSize);
	
	ResponseDto getTotalCount(String reportType);
}
