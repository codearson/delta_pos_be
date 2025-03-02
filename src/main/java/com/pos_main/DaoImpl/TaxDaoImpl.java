package com.pos_main.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.TaxDao;
import com.pos_main.Domain.Tax;
import com.pos_main.Dto.TaxDto;
import com.pos_main.Transformer.TaxTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TaxDaoImpl extends BaseDaoImpl<Tax> implements TaxDao{
	
	@Autowired
	TaxTransformer taxTransformer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public TaxDto save(TaxDto taxDto) {
		log.info("TaxDaoImpl.save() invoked.");
		Tax tax = taxTransformer.reverseTransform(taxDto);
		Tax saveTax = saveOrUpdate(tax);
		return taxTransformer.transform(saveTax);
	}
}
