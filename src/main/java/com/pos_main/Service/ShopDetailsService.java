package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.ShopDetailsDto;

/**
 * Title: ShopDetailsService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 17:19:14
 * @version 1.0
 **/

public interface ShopDetailsService {
	
	public ResponseDto save(ShopDetailsDto shopDetailsDto);
	
	ResponseDto getAll();
	
	ResponseDto getByName(String name);
	
	public ResponseDto getAllPageShopDetails(int pageNumber, int pageSize, Map<String, String> searchParameters);

}
