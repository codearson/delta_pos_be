package com.pos_main.Service.BL;

/**
 * Title: SalesReportServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:39:52
 * @version 1.0
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.SalesReportDao;
import com.pos_main.Dto.SalesReportDto;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class SalesReportServiceBL {

    @Autowired
    private SalesReportDao salesReportDao;

    public SalesReportDto save(SalesReportDto salesReportDto) {
        log.info("SalesReportServiceBL.save() invoked");
        return salesReportDao.save(salesReportDto);
    }
    
    public List<SalesReportDto> findByReportType(String reportType) {
        log.info("SalesReportServiceBL.findByReportType() invoked for: {}", reportType);
        return salesReportDao.findByReportType(reportType);
    }

    public List<SalesReportDto> findByReportTypeWithPagination(String reportType, int pageNumber, int pageSize) {
        log.info("SalesReportServiceBL.findByReportTypeWithPagination() invoked for: {}, page: {}, size: {}", 
                reportType, pageNumber, pageSize);
        return salesReportDao.findByReportTypeWithPagination(reportType, pageNumber, pageSize);
    }

    public long getTotalCount(String reportType) {
        log.info("SalesReportServiceBL.getTotalCount() invoked for reportType: {}", reportType);
        return salesReportDao.getTotalCount(reportType);
    }
}
