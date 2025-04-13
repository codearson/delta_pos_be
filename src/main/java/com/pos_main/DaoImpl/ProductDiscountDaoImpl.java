package com.pos_main.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ProductDiscountDao;
import com.pos_main.Domain.ProductDiscount;
import com.pos_main.Dto.ProductDiscountDto;
import com.pos_main.Transformer.ProductDiscountTransformer;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class ProductDiscountDaoImpl extends BaseDaoImpl<ProductDiscount> implements ProductDiscountDao  {

	@Autowired
	ProductDiscountTransformer productDiscountTransformer;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public ProductDiscountDto save(ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountDaoImpl.save() invoked.");
		ProductDiscount productDiscount = productDiscountTransformer.reverseTransform(productDiscountDto);
		ProductDiscount saveProductDiscount = saveOrUpdate(productDiscount);
		return productDiscountTransformer.transform(saveProductDiscount);
	}
}
