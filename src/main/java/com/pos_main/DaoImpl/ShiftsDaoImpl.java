package com.pos_main.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ShiftsDao;
import com.pos_main.Domain.Product;
import com.pos_main.Domain.Shifts;
import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.ShiftsDto;
import com.pos_main.Transformer.ProductTransformer;
import com.pos_main.Transformer.ShiftsTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShiftsDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 19:52:10
 * @version 1.0
 **/

@Slf4j
@Repository
public class ShiftsDaoImpl extends BaseDaoImpl<Shifts> implements ShiftsDao {
	
	@Autowired
	ShiftsTransformer shiftsTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public ShiftsDto save(ShiftsDto shiftsDto) {
		log.info("ShiftsDaoImpl.save() invoked.");
		Shifts shifts = shiftsTransformer.reverseTransform(shiftsDto);
		Shifts saveShifts = saveOrUpdate(shifts);
		return shiftsTransformer.transform(saveShifts);
	}

}
