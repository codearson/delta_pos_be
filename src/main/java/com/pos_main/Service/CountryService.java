package com.pos_main.Service;

import com.pos_main.Dto.CountryDto;
import com.pos_main.Dto.ResponseDto;

/**
 * Mar 7, 2025 01:30:00 PM
 * 
 * @author Nivethanan Sivagnanasuntharam
 */

public interface CountryService {

    ResponseDto save(CountryDto countryDto);
    ResponseDto getAll();
    ResponseDto getAllPage(int pageNumber, int pageSize);
    
    
}