package com.pos_main.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.DiscountDao;
import com.pos_main.Domain.Discount;
import com.pos_main.Dto.DiscountDto;
import com.pos_main.Transformer.DiscountTransformer;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class DiscountDaoImpl extends BaseDaoImpl<Discount> implements DiscountDao  {

	@Autowired
	DiscountTransformer discountTransformer;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public DiscountDto save(DiscountDto discountDto) {
		log.info("DiscountDaoImpl.save() invoked.");
		Discount discount = discountTransformer.reverseTransform(discountDto);
		Discount saveDiscount = saveOrUpdate(discount);
		return discountTransformer.transform(saveDiscount);
	}
}
