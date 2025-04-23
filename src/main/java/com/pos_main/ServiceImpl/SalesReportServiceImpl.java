package com.pos_main.ServiceImpl;

/**
 * Title: SalesReportServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:39:25
 * @version 1.0
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.SalesReportDto;
import com.pos_main.Service.SalesReportService;
import com.pos_main.Service.BL.SalesReportServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SalesReportServiceImpl implements SalesReportService {

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private SalesReportServiceBL salesReportServiceBL;

    @Override
    public ResponseDto save(SalesReportDto salesReportDto) {
        log.info("SalesReportServiceImpl.save() invoked");
        ResponseDto responseDto = null;
        try {
            SalesReportDto savedReport = salesReportServiceBL.save(salesReportDto);
            if (savedReport != null) {
                log.info("Sales report saved successfully");
                responseDto = serviceUtil.getServiceResponse(savedReport);
            } else {
                log.info("Unable to save sales report");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_SALES_REPORT);
            }
        } catch (Exception e) {
            log.error("Exception occurred while saving sales report", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_SALES_REPORT);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getXReports() {
        log.info("SalesReportServiceImpl.getXReports() invoked");
        ResponseDto responseDto = null;
        try {
            List<SalesReportDto> xReports = salesReportServiceBL.findByReportType("xReport");
            if (xReports != null && !xReports.isEmpty()) {
                log.info("X Reports retrieved successfully, count: {}", xReports.size());
                responseDto = serviceUtil.getServiceResponse(xReports);
            } else {
                log.info("No X Reports found");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_NO_X_REPORTS_FOUND);
            }
        } catch (Exception e) {
            log.error("Exception occurred while retrieving X Reports", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_GET_X_REPORTS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getZReports() {
        log.info("SalesReportServiceImpl.getZReports() invoked");
        ResponseDto responseDto = null;
        try {
            List<SalesReportDto> zReports = salesReportServiceBL.findByReportType("zReport");
            if (zReports != null && !zReports.isEmpty()) {
                log.info("Z Reports retrieved successfully, count: {}", zReports.size());
                responseDto = serviceUtil.getServiceResponse(zReports);
            } else {
                log.info("No Z Reports found");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_NO_Z_REPORTS_FOUND);
            }
        } catch (Exception e) {
            log.error("Exception occurred while retrieving Z Reports", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_GET_Z_REPORTS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getZReportsWithPagination(int pageNumber, int pageSize) {
        log.info("SalesReportServiceImpl.getZReportsWithPagination() invoked for page: {}, size: {}", 
                pageNumber, pageSize);
        ResponseDto responseDto = null;
        try {
            List<SalesReportDto> zReports = salesReportServiceBL.findByReportTypeWithPagination("zReport", pageNumber, pageSize);
            if (zReports != null && !zReports.isEmpty()) {
                log.info("Z Reports retrieved successfully, count: {}", zReports.size());
                responseDto = serviceUtil.getServiceResponse(zReports);
            } else {
                log.info("No Z Reports found");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_NO_Z_REPORTS_PAGE_FOUND);
            }
        } catch (Exception e) {
            log.error("Exception occurred while retrieving Z Reports", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_GET_Z_REPORTS_PAGE);
        }
        return responseDto;
    }
}
