package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.EmployeeDiscountDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.EmployeeDiscountService;
import com.pos_main.Service.BL.EmployeeDiscountServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: EmployeeDiscountServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 22:56:32
 * @version 1.0
 **/

@Slf4j
@Service
public class EmployeeDiscountServiceImpl implements EmployeeDiscountService {
    
    @Autowired
    ServiceUtil serviceUtil;
    
    @Autowired
    EmployeeDiscountServiceBL employeeDiscountServiceBL;

    @Override
    public ResponseDto save(EmployeeDiscountDto employeeDiscountDto) {
        log.info("EmployeeDiscountServiceImpl.save invoked");
        ResponseDto responseDto = null;
        try {
            EmployeeDiscountDto savedDto = employeeDiscountServiceBL.save(employeeDiscountDto);
            if (savedDto != null) {
                log.info("Employee Discount Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedDto);
            } else {
                log.info("Unable to save Employee Discount details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_EMPLOYEE_DISCOUNT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Employee Discount details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_EMPLOYEE_DISCOUNT_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto update(EmployeeDiscountDto employeeDiscountDto) {
        log.info("EmployeeDiscountServiceImpl.update() invoked");
        ResponseDto responseDto = null;
        try {
            EmployeeDiscountDto updatedDto = employeeDiscountServiceBL.update(employeeDiscountDto);
            if (updatedDto != null) {
                log.info("Employee Discount Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedDto);
            } else {
                log.info("Unable to update Employee Discount details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_EMPLOYEE_DISCOUNT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Employee Discount details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_EMPLOYEE_DISCOUNT_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAll() {
        log.info("EmployeeDiscountServiceImpl.getAll() invoked");
        ResponseDto responseDto;
        try {
            List<EmployeeDiscountDto> discountList = employeeDiscountServiceBL.getAll();
            responseDto = (discountList != null && !discountList.isEmpty())
                ? serviceUtil.getServiceResponse(discountList)
                : serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_EMPLOYEE_DISCOUNT_DETAILS);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving All Employee Discount details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_EMPLOYEE_DISCOUNT_DETAILS);
        }
        return responseDto;
    }
}