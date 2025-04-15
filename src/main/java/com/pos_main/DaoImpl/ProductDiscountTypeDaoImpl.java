package com.pos_main.DaoImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ProductDiscountTypeDao;
import com.pos_main.Domain.ProductDiscountType;
import com.pos_main.Dto.ProductDiscountTypeDto;
import com.pos_main.Transformer.ProductDiscountTypeTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductDiscountTypeDaoImpl.java. Company: www.codearson.com |
 * Copyright: Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Apr 10, 2025.
 * @version 1.0
 **/

@Slf4j
@Repository
public class ProductDiscountTypeDaoImpl extends BaseDaoImpl<ProductDiscountType> implements ProductDiscountTypeDao {
	
	@Autowired
	ProductDiscountTypeTransformer productDiscountTypeTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public ProductDiscountTypeDto save(ProductDiscountTypeDto productDiscountTypeDto) {
		log.info("ProductDiscountTypeDaoImpl.save() invoked");
		ProductDiscountType productDiscountType = productDiscountTypeTransformer.reverseTransform(productDiscountTypeDto);
		ProductDiscountType savedProductDiscountType = saveOrUpdate(productDiscountType);
        return productDiscountTypeTransformer.transform(savedProductDiscountType);
    }

	@Override
	@Transactional
	public ProductDiscountTypeDto update(ProductDiscountTypeDto productDiscountTypeDto) {
	    log.info("ProductDiscountTypeDaoImpl.update() invoked");
	    ProductDiscountType productDiscountType = productDiscountTypeTransformer.reverseTransform(productDiscountTypeDto);
	    ProductDiscountType updatedProductDiscountType = saveOrUpdate(productDiscountType);
        return productDiscountTypeTransformer.transform(updatedProductDiscountType);
    }

	@Override
	@Transactional
	public ProductDiscountTypeDto checkPayoutCategoryAvailability(Integer id) {
	    log.info("ProductDiscountTypeDaoImpl.checkPayoutCategoryAvailability() invoked with id: {}", id);
        Criteria criteria = getCurrentSession().createCriteria(ProductDiscountType.class);
        criteria.add(org.hibernate.criterion.Restrictions.eq("id", id));
        ProductDiscountType productDiscountType = (ProductDiscountType) criteria.uniqueResult();
        return productDiscountTypeTransformer.transform(productDiscountType);
    }

	@Override
	@Transactional
	public List<ProductDiscountTypeDto> getAll() {
	    log.info("ProductDiscountTypeDaoImpl.getAll() invoked");
        Criteria criteria = getCurrentSession().createCriteria(ProductDiscountType.class);
        List<ProductDiscountType> payoutCategories = criteria.list();
        return payoutCategories.stream()
                .map(productDiscountTypeTransformer::transform)
                .collect(Collectors.toList());
    }

}
