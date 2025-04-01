package com.pos_main.Service.BL;

import java.util.List;

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
	
	public TaxDto update(TaxDto taxDto) {
        log.info("TaxServiceBL.update() invoked");
        return taxDao.update(taxDto);
    }

    public TaxDto updateTaxStatus(Integer id, Boolean status) {
        log.info("TaxServiceBL.updateTaxStatus() invoked");
        TaxDto taxDto = taxDao.checkTaxAvailability(id);
        if (taxDto != null) {
            taxDto.setIsActive(status);
            return taxDao.update(taxDto);
        } else {
            return null;
        }
    }
	
	public List<TaxDto> getTaxByName(Double taxPercentage) {
        log.info("TaxServiceBL.getTaxByName() invoked");
        return taxDao.getTaxByName(taxPercentage);
    }
    
    public List<TaxDto> getAll() {
        log.info("TaxServiceBL.getAll() invoked");
        return taxDao.getAll();
    }
}
