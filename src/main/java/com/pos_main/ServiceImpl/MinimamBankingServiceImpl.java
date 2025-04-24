package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.MinimamBankingDto;
import com.pos_main.Service.MinimamBankingService;
import com.pos_main.Service.BL.MinimamBankingServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: MinimamBankingServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 24 Apr 2025
 * @time 23:03:09
 * @version 1.0
 **/

@Slf4j
@Service
public class MinimamBankingServiceImpl implements MinimamBankingService {
    
    @Autowired
    ServiceUtil serviceUtil;
    
    @Autowired
    MinimamBankingServiceBL minimamBankingServiceBL;
    
    @Override
    public ResponseDto save(MinimamBankingDto minimamBankingDto) {
        log.info("MinimamBankingServiceImpl.save invoked");
        ResponseDto responseDto = null;
        try {
            MinimamBankingDto savedMinimamBankingDto = minimamBankingServiceBL.save(minimamBankingDto);
            if (savedMinimamBankingDto != null) {
                log.info("MinimamBanking Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedMinimamBankingDto);
            } else {
                log.info("Unable to save MinimamBanking details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_MINIMAM_BANKING_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving MinimamBanking details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_MINIMAM_BANKING_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto update(MinimamBankingDto minimamBankingDto) {
        log.info("MinimamBankingServiceImpl.update() invoked");
        ResponseDto responseDto = null;
        try {
            MinimamBankingDto updatedMinimamBankingDto = minimamBankingServiceBL.update(minimamBankingDto);
            if (updatedMinimamBankingDto != null) {
                log.info("MinimamBanking Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedMinimamBankingDto);
            } else {
                log.info("Unable to update MinimamBanking details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_MINIMAM_BANKING_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating MinimamBanking details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_MINIMAM_BANKING_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAll() {
        log.info("MinimamBankingServiceImpl.getAll() invoked");
        ResponseDto responseDto;
        try {
            List<MinimamBankingDto> minimamBankingDtoList = minimamBankingServiceBL.getAll();
            responseDto = (minimamBankingDtoList != null && !minimamBankingDtoList.isEmpty())
                ? serviceUtil.getServiceResponse(minimamBankingDtoList)
                : serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_MINIMAM_BANKING_DETAILS);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving All MinimamBanking details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_MINIMAM_BANKING_DETAILS);
        }
        return responseDto;
    }
}
