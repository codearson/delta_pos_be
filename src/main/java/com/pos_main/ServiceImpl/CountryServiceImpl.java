package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.CountryDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.CountryService;
import com.pos_main.Service.BL.CountryServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CountryServiceImpl implements CountryService {

	 @Autowired
	    private CountryServiceBL countryServiceBL;

	    @Autowired
	    private ServiceUtil serviceUtil;
	    
	    @Override
	    public ResponseDto save(CountryDto countryDto) {
	        log.info("CountryImpl.save invoked");
	        ResponseDto responseDto;
	        try {
	        	CountryDto savedDto = countryServiceBL.save(countryDto);
	            if (savedDto != null) {
	                log.info("Country Details saved.");
	                responseDto = serviceUtil.getServiceResponse(savedDto);
	            } else {
	                log.info("Unable to save Country details.");
	                responseDto = serviceUtil.getErrorServiceResponse(
	                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_COUNTRY_DETAILS);
	            }
	        } catch (Exception e) {
	            log.error("Exception while saving Country details.", e);
	            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_COUNTRY_DETAILS);
	        }
	        return responseDto;
	    }
	    
	    @Override
		public ResponseDto getAll() {
			log.info("CountryServiceImpl.getAll() invoked");
			ResponseDto responseDto = null;
			try {
				List<CountryDto> countryDtoList = countryServiceBL.getAll();
				if (countryDtoList != null && !countryDtoList.isEmpty()) {
					log.info("Retrieve All Country Details.");
					responseDto = serviceUtil.getServiceResponse(countryDtoList);
				} else {
					log.info("Unable to retrieve All Country details.");
					responseDto = serviceUtil.getErrorServiceResponse(
							ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_COUNTRY_DETAILS);
				}
			} catch (Exception e) {
				log.error("Exception occurs while retrieving All Country details.", e);
				responseDto = serviceUtil.getExceptionServiceResponseByProperties(
						ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_COUNTRY_DETAILS);
			}
			return responseDto;
		}
	    
}
