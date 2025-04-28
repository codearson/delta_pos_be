package com.pos_main.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ShopDetailsDao;
import com.pos_main.Dto.PaginatedResponseDto;
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
	
	public List<ShopDetailsDto> getAll() {
		log.info("ShopDetailsServiceBL.getAll() invoked");
		return shopDetailsDao.getAll();
	}
	
	public PaginatedResponseDto getAllPageShopDetails(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("ShopDetailsServiceBL.getAllPageShopDetails()invoked");
		return shopDetailsDao.getAllPageShopDetails(pageNumber, pageSize, status, searchParams);
	}
	
	public List<ShopDetailsDto> getByName(String name) {
        log.info("ShopDetailsServiceBL.getAllByName() invoked.");
        return shopDetailsDao.getByName(name);
    }
	

}
