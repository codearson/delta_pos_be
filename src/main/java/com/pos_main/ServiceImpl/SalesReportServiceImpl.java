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
}
