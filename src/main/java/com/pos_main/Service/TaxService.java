package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TaxDto;

public interface TaxService {
	
	public ResponseDto save(TaxDto taxDto);
	
	public ResponseDto getTaxByName(Double taxPercentage);
	
    public ResponseDto getAll();
    
    public ResponseDto getAllPageTax(int pageNumber, int pageSize, Map<String, String> searchParameters);
    
    public ResponseDto update(TaxDto taxDto);
    
    public ResponseDto updateTaxStatus(Integer id, Boolean status);

}
