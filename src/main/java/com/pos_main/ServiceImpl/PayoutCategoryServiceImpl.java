package com.pos_main.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.PayoutCategoryDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PayoutCategoryService;
import com.pos_main.Service.BL.PayoutCategoryServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PayoutCategoryServiceImpl implements PayoutCategoryService {
    
    @Autowired
    ServiceUtil serviceUtil;
    
    @Autowired
    PayoutCategoryServiceBL payoutCategoryServiceBL;

    @Override
    public ResponseDto save(PayoutCategoryDto payoutCategoryDto) {
        log.info("PayoutCategoryServiceImpl.save() invoked");
        ResponseDto responseDto = null;
        try {
            PayoutCategoryDto savedDto = payoutCategoryServiceBL.save(payoutCategoryDto);
            if (savedDto != null) {
                log.info("Payout Category saved successfully");
                responseDto = serviceUtil.getServiceResponse(savedDto);
            } else {
                log.info("Failed to save Payout Category");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PAYOUT_CATEGORY);
            }
        } catch (Exception e) {
            log.error("Exception while saving Payout Category", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PAYOUT_CATEGORY);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updatePayoutCategory(PayoutCategoryDto payoutCategoryDto) {
        log.info("PayoutCategoryServiceImpl.updatePayoutCategory() invoked");
        ResponseDto responseDto = null;
        try {
            PayoutCategoryDto updatedDto = payoutCategoryServiceBL.updatePayoutCategory(payoutCategoryDto);
            if (updatedDto != null) {
                log.info("Payout Category updated successfully");
                responseDto = serviceUtil.getServiceResponse(updatedDto);
            } else {
                log.info("Failed to update Payout Category");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PAYOUT_CATEGORY);
            }
        } catch (Exception e) {
            log.error("Exception while updating Payout Category", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PAYOUT_CATEGORY);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updatePayoutCategoryStatus(Integer id, Boolean status) {
        log.info("PayoutCategoryServiceImpl.updatePayoutCategoryStatus() invoked");
        ResponseDto responseDto = null;
        try {
            PayoutCategoryDto updatedDto = payoutCategoryServiceBL.updatePayoutCategoryStatus(id, status);
            if (updatedDto != null) {
                log.info("Payout Category status updated successfully");
                responseDto = serviceUtil.getServiceResponse(updatedDto);
            } else {
                log.info("Failed to update Payout Category status");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PAYOUT_CATEGORY_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception while updating Payout Category status", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PAYOUT_CATEGORY_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPayoutCategory() {
        log.info("PayoutCategoryServiceImpl.getAllPayoutCategory() invoked");
        ResponseDto responseDto = null;
        try {
            List<PayoutCategoryDto> dtoList = payoutCategoryServiceBL.getAllPayoutCategory();
            if (dtoList != null && !dtoList.isEmpty()) {
                log.info("Payout Categories retrieved successfully");
                responseDto = serviceUtil.getServiceResponse(dtoList);
            } else {
                log.info("No Payout Categories found");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAYOUT_CATEGORIES);
            }
        } catch (Exception e) {
            log.error("Exception while retrieving Payout Categories", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PAYOUT_CATEGORIES);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPagePayoutCategory(int pageNumber, int pageSize, Map<String, String> searchParams) {
        log.info("PayoutCategoryServiceImpl.getAllPagePayoutCategory() invoked");
        ResponseDto responseDto = null;
        try {
            PaginatedResponseDto paginatedDto = payoutCategoryServiceBL.getAllPagePayoutCategory(pageNumber, pageSize, searchParams);
            if (paginatedDto != null) {
                log.info("Paginated Payout Categories retrieved successfully");
                responseDto = serviceUtil.getServiceResponse(paginatedDto);
            } else {
                log.info("No paginated Payout Categories found");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAYOUT_CATEGORIES);
            }
        } catch (Exception e) {
            log.error("Exception while retrieving paginated Payout Categories", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PAYOUT_CATEGORIES);
        }
        return responseDto;
    }
}