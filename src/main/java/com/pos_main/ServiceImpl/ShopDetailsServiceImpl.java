package com.pos_main.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.ProductDto;
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

}
