package com.pos_main.Dao;

import java.util.List;

import com.pos_main.Dto.TaxDto;

public interface TaxDao {

	TaxDto save (TaxDto taxDto);
	
	TaxDto update(TaxDto taxDto);
	
    TaxDto checkTaxAvailability(Integer id);
	
	List<TaxDto> getTaxByName(Double taxPercentage);
	
    List<TaxDto> getAll();
	
}
