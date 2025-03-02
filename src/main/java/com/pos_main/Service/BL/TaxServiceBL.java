package com.pos_main.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.TaxDao;
import com.pos_main.Dto.TaxDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaxServiceBL {
	
	@Autowired
	TaxDao taxDao;
	
	public TaxDto save(TaxDto taxDto) {
		log.info("TaxServiceBL.save() invoked.");
		return taxDao.save(taxDto);
	}
}
