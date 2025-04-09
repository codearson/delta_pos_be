package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.NonScanProductDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.NonScanProductService;
import com.pos_main.Service.BL.NonScanProductServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: NonScanProductServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:24:15
 * @version 1.0
 **/

@Slf4j
@Service
public class NonScanProductServiceImpl implements NonScanProductService {

    @Autowired
    ServiceUtil serviceUtil;
    
    @Autowired
    NonScanProductServiceBL nonScanProductServiceBL;

    @Override
    public ResponseDto getAll() {
        log.info("NonScanProductServiceImpl.getAll() invoked");
        ResponseDto responseDto = null;
        try {
            List<NonScanProductDto> nonScanProductDtoList = nonScanProductServiceBL.getAll();
            if (nonScanProductDtoList != null && !nonScanProductDtoList.isEmpty()) {
                log.info("Retrieve All NonScanProduct Details.");
                responseDto = serviceUtil.getServiceResponse(nonScanProductDtoList);
            } else {
                log.info("Unable to retrieve All NonScanProduct details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CATRGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving All NonScanProduct details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_CATRGORY_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageNonScanProduct(int pageNumber, int pageSize) {
        log.info("NonScanProductServiceImpl.getAllPageNonScanProduct() invoked");
        ResponseDto responseDto = null;
        try {
            List<NonScanProductDto> nonScanProductDtoList = nonScanProductServiceBL.getAllPageNonScanProduct(pageNumber, pageSize);
            if (nonScanProductDtoList != null && !nonScanProductDtoList.isEmpty()) {
                log.info("Retrieve All NonScanProduct Details.");
                responseDto = serviceUtil.getServiceResponse(nonScanProductDtoList);
            } else {
                log.info("Unable to retrieve All NonScanProduct details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CATRGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving All NonScanProduct details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_CATRGORY_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto save(NonScanProductDto nonScanProductDto) {
        log.info("NonScanProductServiceImpl.save() invoked");
        ResponseDto responseDto = null;
        try {
            NonScanProductDto savedNonScanProductDto = nonScanProductServiceBL.save(nonScanProductDto);
            if (savedNonScanProductDto != null) {
                log.info("NonScanProduct Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedNonScanProductDto);
            } else {
                log.info("Unable to save NonScanProduct details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATRGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving NonScanProduct details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_CATRGORY_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto update(NonScanProductDto nonScanProductDto) {
        log.info("NonScanProductServiceImpl.update() invoked");
        ResponseDto responseDto = null;
        try {
            NonScanProductDto updatedNonScanProductDto = nonScanProductServiceBL.update(nonScanProductDto);
            if (updatedNonScanProductDto != null) {
                log.info("NonScanProduct Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedNonScanProductDto);
            } else {
                log.info("Unable to update NonScanProduct details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATRGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating NonScanProduct details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATRGORY_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllByName(String nonScanProduct) {
        log.info("NonScanProductServiceImpl.getAllByName() invoked");
        ResponseDto responseDto = null;
        try {
            List<NonScanProductDto> nonScanProductDtoList = nonScanProductServiceBL.getAllByName(nonScanProduct);
            if (nonScanProductDtoList != null && !nonScanProductDtoList.isEmpty()) {
                log.info("Retrieve NonScanProduct Details by Name.");
                responseDto = serviceUtil.getServiceResponse(nonScanProductDtoList);
            } else {
                log.info("Unable to retrieve NonScanProduct by name.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_CATEGORY_BY_NAME);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving NonScanProduct by name.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_CATEGORY_BY_NAME);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateNonScanProductStatus(Integer id, Boolean status) {
        log.info("NonScanProductServiceImpl.updateNonScanProductStatus() invoked");
        ResponseDto responseDto = null;
        try {
            NonScanProductDto updatedNonScanProductDto = nonScanProductServiceBL.updateNonScanProductStatus(id, status);
            if (updatedNonScanProductDto != null) {
                log.info("NonScanProduct Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedNonScanProductDto);
            } else {
                log.info("Unable to update NonScanProduct status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_CATEGORY_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating NonScanProduct status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_USER_STATUS);
        }
        return responseDto;
    }
}