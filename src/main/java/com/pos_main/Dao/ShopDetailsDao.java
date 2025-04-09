package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.ShopDetails;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ShopDetailsDto;

/**
 * Title: ShopDetailsDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 17:32:40
 * @version 1.0
 **/

public interface ShopDetailsDao extends BaseDao<ShopDetails>{
	
	ShopDetailsDto save(ShopDetailsDto shopDetailsDto);
	
	List<ShopDetailsDto> getAll();
	
	PaginatedResponseDto getAllPageShopDetails(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	List<ShopDetailsDto> getByName (String name);

}
