package com.pos_main.Service.BL;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ShopDetailsDao;
import com.pos_main.Dto.ShopDetailsDto;

import lombok.extern.slf4j.Slf4j;


/**
 * Title: ShopDetailsServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 17:26:20
 * @version 1.0
 **/

@Slf4j
@Service
public class ShopDetailsServiceBL {
	
	@Autowired
	ShopDetailsDao shopDetailsDao;
	
	public ShopDetailsDto save(ShopDetailsDto shopDetailsDto) {
		log.info("ShopDetailsServiceBL.save() invoked.");
		return shopDetailsDao.save(shopDetailsDto);
	}

}
