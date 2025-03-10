package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.CountryDao;
import com.pos_main.Dto.CountryDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Mar 7, 2025 01:37:20 PM
 * 
 * @author Nivethanan Sivagnanasuntharam
 */

@Slf4j
@Service
public class CountryServiceBL {

	@Autowired
	CountryDao countryDao;
	
	public CountryDto save(CountryDto countryDto) {
		log.info("CountryServiceBL.save() invoked.");
		return countryDao.save(countryDto);
	}
	
	public List<CountryDto> getAll() {
		log.info("CountryServiceBL.getAll() invoked");
		return countryDao.getAll();
	}
}
