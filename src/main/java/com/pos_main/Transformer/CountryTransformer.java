package com.pos_main.Transformer;

import org.springframework.stereotype.Component;
import com.pos_main.Domain.Country;
import com.pos_main.Dto.CountryDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Mar 7, 2025 12:44:15 PM
 * 
 * @author Nivethanan Sivagnanasuntharam
 */
@Slf4j
@Component
public class CountryTransformer {

    public CountryDto transform(Country country) {
        if (country == null) {
            log.warn("Country is null in transform");
            return null;
        }
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setCountryName(country.getCountryName());
        countryDto.setPriceSymbol(country.getPriceSymbol());
        log.info("Transformed Country to DTO: id={}, countryName={}, priceSymbol={}", 
                country.getId(), country.getCountryName(), country.getPriceSymbol());
        return countryDto; 
    }
    
    public Country reverseTransform(CountryDto countryDto) {
        if (countryDto == null) {
            log.warn("CountryDto is null in reverseTransform");
            return null;
        }
        Country country = new Country();
        country.setId(countryDto.getId());
        country.setCountryName(countryDto.getCountryName());
        country.setPriceSymbol(countryDto.getPriceSymbol()); 
        log.info("Transformed DTO to Country: id={}, countryName={}, priceSymbol={}", 
                country.getId(), country.getCountryName(), country.getPriceSymbol());
        return country;
    }
}