package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TaxDto;
import com.pos_main.Service.TaxService;
import com.pos_main.Service.BL.TaxServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaxServiceImpl implements TaxService{
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	TaxServiceBL taxServiceBL;

	@Override
	public ResponseDto save(TaxDto taxDto) {
		log.info("TaxServiceImpl.save invoked");
		ResponseDto responseDto = null;
		try {
			TaxDto saveTaxDto = taxServiceBL.save(taxDto);
			if (saveTaxDto != null) {
				log.info("Tax Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveTaxDto);
			} else {
				log.info("Unable to save Tax details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_TAX_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Tax details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_TAX_DETAILS);
		}
		return responseDto;
	}
	
	@Override
    public ResponseDto update(TaxDto taxDto) {
        log.info("TaxServiceImpl.update() invoked");
        ResponseDto responseDto = null;
        try {
            TaxDto updatedTaxDto = taxServiceBL.update(taxDto);
            if (updatedTaxDto != null) {
                log.info("Tax Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedTaxDto);
            } else {
                log.info("Unable to update Tax details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_TAX_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Tax details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_TAX_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateTaxStatus(Integer id, Boolean status) {
        log.info("TaxServiceImpl.updateTaxStatus() invoked");
        ResponseDto responseDto = null;
        try {
            TaxDto updatedTaxStatusDto = taxServiceBL.updateTaxStatus(id, status);
            if (updatedTaxStatusDto != null) {
                log.info("Tax Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedTaxStatusDto);
            } else {
                log.info("Unable to update Tax status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_TAX_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Tax status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_TAX_STATUS);
        }
        return responseDto;
    }
	
	@Override
    public ResponseDto getTaxByName(Double taxPercentage) {
        log.info("TaxServiceImpl.getTaxByName() invoked");
        ResponseDto responseDto;
        try {
            List<TaxDto> taxDtoList = taxServiceBL.getTaxByName(taxPercentage);
            responseDto = (taxDtoList != null && !taxDtoList.isEmpty()) 
                ? serviceUtil.getServiceResponse(taxDtoList)
                : serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TAX_BY_NAME);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Tax by taxPercentage.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TAX_BY_NAME);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAll() {
        log.info("TaxServiceImpl.getAll() invoked");
        ResponseDto responseDto;
        try {
            List<TaxDto> taxDtoList = taxServiceBL.getAll();
            responseDto = (taxDtoList != null && !taxDtoList.isEmpty())
                ? serviceUtil.getServiceResponse(taxDtoList)
                : serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_TAX_DETAILS);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving All Tax details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_TAX_DETAILS);
        }
        return responseDto;
    }
}
