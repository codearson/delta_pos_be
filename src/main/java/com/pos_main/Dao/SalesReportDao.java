package com.pos_main.Dao;

import com.pos_main.Dto.SalesReportDto;
import java.util.List;

/**
 * Title: SalesReportDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:40:11
 * @version 1.0
 **/

public interface SalesReportDao {
    
	SalesReportDto save(SalesReportDto salesReportDto);
	List<SalesReportDto> findByReportType(String reportType);
	List<SalesReportDto> findByReportTypeWithPagination(String reportType, int pageNumber, int pageSize);
	long getTotalCount(String reportType);
}
