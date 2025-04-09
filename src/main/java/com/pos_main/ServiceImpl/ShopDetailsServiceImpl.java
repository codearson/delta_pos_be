package com.pos_main.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.ShopDetailsDto;
import com.pos_main.Service.ShopDetailsService;
import com.pos_main.Service.BL.ShopDetailsServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShopDetailsServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 17:20:54
 * @version 1.0
 **/

@Slf4j
@Service
public class ShopDetailsServiceImpl implements ShopDetailsService{
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	ShopDetailsServiceBL shopDetailsServiceBL;
	
	@Override
	public ResponseDto save(ShopDetailsDto shopDetailsDto) {
		log.info("ShopDetailsServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			ShopDetailsDto  saveShopDetailsDto = shopDetailsServiceBL.save(shopDetailsDto);
			if (saveShopDetailsDto != null) {
				log.info("ShopDetails Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveShopDetailsDto);
			} else {
				log.info("Unable to save ShopDetails details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_SHOP_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving ShopDetails details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_SHOP_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAll() {
		log.info("ShopDetailsServiceImpl.getAll() invoked");
		ResponseDto responseDto = null;
		try {
			List<ShopDetailsDto> shopDetailsDtoList = shopDetailsServiceBL.getAll();
			if (shopDetailsDtoList != null && !shopDetailsDtoList.isEmpty()) {
				log.info("Retrieve All ShopDetails Details.");
				responseDto = serviceUtil.getServiceResponse(shopDetailsDtoList);
			} else {
				log.info("Unable to retrieve All ShopDetails details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_SHOP_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Shop details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_SHOP_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageShopDetails(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("ShopDetailsServiceImpl.getAllPageShopDetails() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = shopDetailsServiceBL.getAllPageShopDetails(pageNumber, pageSize, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All ShopDetails Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All ShopDetails details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_SHOPDETAIL_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All ShopDetails details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_SHOPDETAIL_DETAILS);
		}
		return responseDto;
	}
	
	@Transactional	
	@Override
    public ResponseDto getByName (String name) {
        log.info("ShopDetailsServiceImpl.getByName () invoked");
        ResponseDto responseDto = null;
        try {
            List<ShopDetailsDto> shopDetailsDtoList = shopDetailsServiceBL.getByName(name);
            if (shopDetailsDtoList != null && !shopDetailsDtoList.isEmpty()) {
                log.info("Retrieve ShopDetails by Name.");
                responseDto = serviceUtil.getServiceResponse(shopDetailsDtoList);
            } else {
                log.info("Unable to retrieve ShopDetails by name.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_SHOPDETAILS_BY_NAME);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving ShopDetails by ShopDetailsName.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_SHOPDETAILS_BY_NAME);
        }
        return responseDto;
    }

}
