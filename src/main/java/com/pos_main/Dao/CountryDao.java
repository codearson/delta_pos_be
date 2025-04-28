package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Domain.Country;
import com.pos_main.Dto.CountryDto;
import com.pos_main.Dto.PaginatedResponseDto;

/**
 * Mar 7, 2025 01:48:44 PM
 * 
 * @author Nivethanan Sivagnanasuntharam
 */

public interface CountryDao extends BaseDao<Country> {

	CountryDto save(CountryDto countryDto);
	
	List<CountryDto> getAll();
	
	PaginatedResponseDto getAllPage(int pageNumber, int pageSize);
	
}
