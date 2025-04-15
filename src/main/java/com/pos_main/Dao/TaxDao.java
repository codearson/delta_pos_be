package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.TaxDto;

public interface TaxDao {

	TaxDto save (TaxDto taxDto);
	
	TaxDto update(TaxDto taxDto);
	
    TaxDto checkTaxAvailability(Integer id);
	
	List<TaxDto> getTaxByName(Double taxPercentage);
	
    List<TaxDto> getAll();
    
	PaginatedResponseDto getAllPageTax(int pageNumber, int pageSize, Map<String, String> searchParams);
	
}
